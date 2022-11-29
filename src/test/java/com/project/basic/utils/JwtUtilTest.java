package com.project.basic.utils;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestPropertySource(properties = {"spring.config.location = classpath:application-local.yml"})
public class JwtUtilTest {

    @Test
    public void tokenTest(){
       
        log.info("Start Token Test");
          
        // Set Data
        String email = "test@test.com";
        log.info("Set Data : {}", email);

        // Token 및 Refresh Token 생성
        String token = JwtUtil.create(email);
        String refresh = JwtUtil.createRefresh();

        log.info("Create Token : {}", token);
        log.info("Create Refresh Token : {}", refresh);

        // Validate And Get Data
        Map<String, Object> payloads = JwtUtil.getData(token);
        log.info("Validate And Get Data Token");
        log.info(payloads.toString());

        // 테스트를 위한 재활용
        payloads = JwtUtil.getData(refresh);
        log.info("Validate And Get Data Refresh Token");
        log.info(payloads.toString());

    }

}
