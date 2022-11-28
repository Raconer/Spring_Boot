# Spring Boot 2.x

## 개요

> Spring Boot의 기본 적인 셋팅 과 프로젝트 개발시 필요한 Dependency 사용법 작성

## 적용 기술

> Master Branch 및 각 브랜치 참조

* Server
  * Spring Boot 2.x
* viewer
  * tomcat-embed-jasper
* DB
  * MySql
  * Mybatis

## DB Test Sql

```sql
  CREATE DATABASE `basic`;

  -- basic.base_table definition

  CREATE TABLE `base_table` (
    `id` int NOT NULL AUTO_INCREMENT,
    `title` varchar(100) DEFAULT NULL,
    UNIQUE KEY `base_table_id_IDX` (`id`) USING BTREE
  ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

  INSERT INTO base_table(id, title)
  VALUES(1, 'TEST_Title');
```
