package com.project.basic.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest()
@TestPropertySource(properties = {"spring.config.location = classpath:application-local.yml"})
public class AwsServiceTest {

    @Autowired
    AwsService awsService;
    
    @Test
    public void uploadTest() {
        File file = new File("src/test/resources/image/cat.jpg");

        try {
            String fileName = file.getName();
            MultipartFile multipartFile = new MockMultipartFile(fileName, new FileInputStream(file));
            awsService.upload("/test", fileName , multipartFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testRemove() {
        awsService.remove("/test", "cat.jpg");
    }

}
