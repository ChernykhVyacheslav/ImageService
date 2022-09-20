package com.chernykh.imageservice.services;

import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.chernykh.imageservice.image.ImageType;
import com.chernykh.imageservice.repositories.AmazonImageRepository;
import com.chernykh.imageservice.repositories.ImageRepository;
import com.chernykh.imageservice.utils.ImageUtils;
import com.chernykh.imageservice.exceptions.SourceImageNotFoundException;
import com.chernykh.imageservice.exceptions.UrlDownException;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class AmazonS3ImageService extends AmazonClientService implements ImageService {

    // probably will need amazonImageRepository for work with AWS S3 bucket and imageRepository for downloading image from the source
    private final AmazonImageRepository amazonImageRepository;
    private final ImageRepository sourceImageRepository;

    public AmazonS3ImageService(AmazonImageRepository amazonImageRepository, ImageRepository imageRepository) {
        this.amazonImageRepository = amazonImageRepository;
        this.sourceImageRepository = imageRepository;
    }

    @Override
    public Object getImage(ImageType imageType, String reference) {
        String bucketName = ImageUtils.getBucketDirectoryName(getBucketName(), imageType, reference);
        if (checkIfImageExistsOnS3(bucketName, reference)) {
            return getImage(bucketName, reference);
        }
        String originalBucketName = ImageUtils.getBucketDirectoryName(getBucketName(), ImageType.ORIGINAL, reference);
        Object originalImage;
        if (checkIfImageExistsOnS3(originalBucketName, reference)) {
            originalImage = getImage(originalBucketName, reference);
        } else {
            if (!checkIfSourceImageExists()) {
                throw new SourceImageNotFoundException();
            }
            originalImage = downloadOriginalImage(imageType);
            storeImage(originalImage, originalBucketName, reference);
        }
        Object optimizedImage = optimizeImage(originalImage, imageType);
        storeImage(optimizedImage, bucketName, reference);
        return optimizedImage;
    }

    @Override
    public void flushImage(ImageType imageType, String reference) {
        if(imageType.equals(ImageType.ORIGINAL)) {
            for (ImageType type : ImageType.values()) {
                deleteImage(type, reference);
            }
        } else {
            deleteImage(imageType, reference);
        }
    }

    private void deleteImage(ImageType imageType, String reference) {
        String bucketName = ImageUtils.getBucketDirectoryName(getBucketName(), imageType, reference);
        getClient().deleteObject(bucketName, reference);
    }

    public Object optimizeImage(Object image, ImageType imageType) {
        ImageUtils.getImageConfigs(imageType);
        //TODO: resize and optimize original image according to image configs received for provided ImageType
        return image;
    }

    public Object downloadOriginalImage(ImageType imageType) {
        ImageUtils.getImageConfigs(imageType);
        Object image = null;
        //TODO: download original image from the source
        return image;
    }

    public void storeImage(Object image, String bucketName, String reference) {
        getClient().putObject(new PutObjectRequest(bucketName, reference, (File) image));
        //TODO: finish method and handle exception, log warn and retry
    }

    public Object getImage(String bucketName, String reference) {
        return getClient().getObject(new GetObjectRequest(bucketName, reference));
    }

    public boolean checkIfImageExistsOnS3(String bucketName, String reference) {
        return getClient().doesObjectExist(bucketName, reference);
    }

    private boolean checkIfSourceImageExists() {
        try {
            //TODO: check if source image exists
        } catch (Exception e) {
            throw new UrlDownException();
        }
        return true;
    }
}
