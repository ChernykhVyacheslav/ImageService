package com.chernykh.imageservice.services;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class AmazonClientService {

    private AmazonS3 amazonS3;

    @Value("${aws-s3-endpoint}")
    private String url;

    @Value("/images")
    private String bucketName;

    @Value("${aws-accesskey}")
    private String accessKey;

    @Value("${aws-secretkey}")
    private String secretKey;

    protected AmazonS3 getClient() {
        return amazonS3;
    }

    protected String getUrl() {
        return url;
    }

    protected String getBucketName() {
        return bucketName;
    }

    @PostConstruct
    private void init() {

        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        this.amazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }
}
