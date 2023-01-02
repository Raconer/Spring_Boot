package com.project.basic.api;

import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.basic.model.api.common.response.DefDataResponse;
import com.project.basic.model.api.common.response.DefResponse;
import com.project.basic.service.RedisService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/redis")
public class RedisRestController {

    RedisService redisService;

    // Create
    @PostMapping
    public ResponseEntity<?> insert() {
        if (redisService.insert()) {
            return ResponseEntity.ok(new DefResponse(HttpStatus.OK));
        }

        return ResponseEntity.ok(new DefResponse(HttpStatus.BAD_REQUEST));
    }

    // Read
    @GetMapping
    public ResponseEntity<?> list() {
        redisService.list();

        return ResponseEntity.ok(new DefResponse(HttpStatus.OK));
    }
}
