package com.chernykh.imageservice;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import io.findify.s3mock.S3Mock;


@SpringBootTest
class ImageServiceApplicationTests {

    @Test
    void contextLoads() {
        S3Mock api = S3Mock.create(8001, "/tmp/s3");

        api.start();

        BasicAWSCredentials creds = new BasicAWSCredentials("access_key", "secret_key");

        AmazonS3 client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(creds))
                .build();

        client.setEndpoint("http://127.0.0.1:8001");
        client.createBucket("testbucket");
        client.putObject("testbucket", "file/name", "contents");
    }

    // TODO: Write tests for controller and service

}
