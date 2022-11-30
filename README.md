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

> 테스트 용 DDL 입니다.

```sql
  CREATE DATABASE `basic`;

  -- basic.`base_table` definition
  CREATE TABLE `base_table` (
    `id` int NOT NULL AUTO_INCREMENT,
    `title` varchar(100) DEFAULT NULL,
    `content` varchar(100) DEFAULT NULL,
    UNIQUE KEY `base_table_id_IDX` (`id`) USING BTREE
  ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

  -- basic.`user` definition
  CREATE TABLE `user` (
    `id` int NOT NULL AUTO_INCREMENT,
    `email` varchar(100) NOT NULL,
    `password` varchar(100) NOT NULL,
    `username` varchar(100) NOT NULL,
    PRIMARY KEY (`id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```

## 제작 완료

### Test Case

> 기본적인 API는 .http에서 테스트를 하였습니다.
> Spring Boot Test에서는 Utils이나 Service위주의 테스트를 진행 하였습니다.

### 공통

* [x] **기본 서버 셋팅**
* [x] **환경 설정**
  * [x] application.properties -> application.yml 설정
  * [x] spring.profiles.active 따른 구성
* [x] **MySql 연결**
  * [x] Mybatis 설정
    * 기본적인 CRUD 생성
    * logBack.xml
  * [x] hikari 기본 설정
* [x] **테스트 케이스 설정**
  * [x] API : Server 실행 후 .http로 테스트
    * PostMan을 주로 사용하나 공개 된 프로젝트 이므로 .http로 대체
* [x] **다국어 설정**
  * messages.properties 설정
  * MessageUtil로 코드만으로 Message Read
  * messages.yml 변경 (branch > feature > i18n_yml 참고)
* [x] AWS S3 설정
  * File Upload
  * File Remove
  * Test Case에서 사용가능
  * !개인 AWS 정보 application-local.yml 입력 필요

### Security

* [x] Spring Security 추가 및 토큰 설정
  * addFilterBefore로 Request시 토큰 검사
    * Request시 Regex로 토큰 미허용 Path 설정
  * TODO 권한은 따로 구성하지 않아 추후 권한별 API접근 추가
* [x] Static Resource 접근 허용

### WEB

* [x] tomcat-embed-jasper 추가 하여 View Path설정
* [x] 환경 설정
  * 기본적인 설정은 application.yml에 설정
  * prefix, suffix 설정
* [x] 다국어 tagLib 및 Server에서 Read 가능

### API

* [x] 공통 Response Model 설정
  * src\main\java\com\project\basic\model\api\common\response\DefResponse.java, DefDataResponse.java
* [x] Request 요청시 Validate
  * @Valid
  * @Component > implements Validator 하여 Validate
* [x] ValidErrUtil 사용으로 에러 공통 Response
