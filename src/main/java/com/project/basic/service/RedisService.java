package com.project.basic.service;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class RedisService {

    RedisTemplate<String, Object> redisTemplate;
    final String EVENT_STR = "Coupon";

    // Create
    public boolean insert() {
        final long now = System.currentTimeMillis();
        redisTemplate.opsForZSet().add(EVENT_STR, UUID.randomUUID().toString(), (int) now);
        return true;
    }

    // Read

    public void list() {

        Set<Object> queue = redisTemplate.opsForZSet().range(EVENT_STR, 0, 100);
        System.out.println(queue.toString());
        for (Object people : queue) {
            Long rank = redisTemplate.opsForZSet().rank(EVENT_STR, people);
            log.info("'{}'님의 현재 대기열은 {}명 남았습니다.", people, rank);
        }
    }

}
