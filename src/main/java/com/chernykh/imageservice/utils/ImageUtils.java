package com.chernykh.imageservice.utils;

import com.chernykh.imageservice.image.ImageConfig;
import com.chernykh.imageservice.image.ImageType;

import java.util.HashMap;
import java.util.Map;

public class ImageUtils {

    private final static Map<ImageType, ImageConfig> imageConfigs = new HashMap<>();

    static {
        imageConfigs.put(ImageType.ORIGINAL, new ImageConfig());
        imageConfigs.put(ImageType.DETAIL_LARGE, new ImageConfig());
        imageConfigs.put(ImageType.THUMBNAIL, new ImageConfig());
    }

    public static String getBucketDirectoryName(String bucketName, ImageType imageType, String reference) {
        StringBuilder bucketDirectoryName = new StringBuilder(bucketName);
        bucketDirectoryName.append("/").append(imageType.getValue());
        String[] refArr = reference.replaceAll("/", "_").split("(?<=\\G.{4})");
        if(refArr.length > 1) {
            bucketDirectoryName.append("/").append(refArr[0]);
        }
        if(refArr.length > 2) {
            bucketDirectoryName.append("/").append(refArr[1]);
        }
        bucketDirectoryName.append("/");
        return bucketDirectoryName.toString();
    }

    public static ImageConfig getImageConfigs(ImageType imageType) {
        return imageConfigs.get(imageType);
    }
}
