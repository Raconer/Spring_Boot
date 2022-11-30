package com.project.basic.service;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@NoArgsConstructor
public class AwsService {
    

    @Value("${aws.bucket.name}")
    private String bucketName;
    @Value("${aws.bucket.access.key}")
    private String accessKey;
    @Value("${aws.bucket.secret.key}")
    private String secretKey;

    private AmazonS3 amazonS3;

    @PostConstruct
    public void setS3Client() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        amazonS3 = AmazonS3ClientBuilder
                    .standard()
                    .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                    .withRegion(Regions.AP_NORTHEAST_2).build();
    }

    public String upload(String path, String fileName, MultipartFile file)  {
        log.info("============= Upload file - Start!! ===============");
      
        try {
            ObjectMetadata omd = new ObjectMetadata();
            omd.setContentType(file.getContentType());
            omd.setContentLength(file.getSize());
            amazonS3.putObject(new PutObjectRequest( bucketName + path , fileName, file.getInputStream(), omd));
            log.info("============= Upload file - Done!! ===============");
        } catch (AmazonClientException e){
            throw new AmazonClientException(e);
        } catch (RuntimeException e){
            throw new RuntimeException();
        } catch (IOException e) {
            throw new NullPointerException();
        }
        return fileName;
    }

    public boolean remove(String path, String name)  {
        boolean isRemove = false;
        try {
            amazonS3.deleteObject(new DeleteObjectRequest( bucketName + path , name));
            isRemove = true;
            log.info("============= Remove file - Done!! ===============");
        } catch (AmazonClientException e) {
            throw new AmazonClientException(e);
        } catch (RuntimeException e){
            throw new RuntimeException();
        }
        return isRemove;
    }
}
