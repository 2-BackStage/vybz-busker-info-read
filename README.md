# VYBZ Busker Info Read Service

VYBZ 플랫폼의 버스커 정보 조회를 담당하는 마이크로서비스입니다.

## 📋 목차

-   [개요](#개요)
-   [기술 스택](#기술-스택)
-   [주요 기능](#주요-기능)
-   [프로젝트 구조](#프로젝트-구조)
-   [API 문서](#api-문서)
-   [설치 및 실행](#설치-및-실행)
-   [환경 설정](#환경-설정)
-   [아키텍처](#아키텍처)
-   [개발 가이드](#개발-가이드)

## 🎯 개요

VYBZ Busker Info Read Service는 다음과 같은 기능을 제공합니다:

-   **버스커 정보 조회**: 버스커 UUID를 통한 정보 조회
-   **마이페이지 정보 제공**: 버스커 마이페이지에 필요한 정보 제공
-   **API 제공**: 버스커 정보 조회 REST API 제공
-   **서비스 디스커버리**: Eureka Client를 통한 서비스 등록
-   **API 문서화**: Swagger를 통한 API 문서 제공
-   **이벤트 기반 통신**: Kafka를 통한 비동기 이벤트 처리

## 🛠 기술 스택

### Backend

![Spring Cloud](https://img.shields.io/badge/Spring_Cloud-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-47A248?style=for-the-badge&logo=mongodb&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)
![Kafka](https://img.shields.io/badge/Apache_Kafka-231F20?style=for-the-badge&logo=apache-kafka&logoColor=white)

### Infra

![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white)
![Amazon EC2](https://img.shields.io/badge/Amazon_EC2-FF9900?style=for-the-badge&logo=amazonaws&logoColor=white)
![Nginx](https://img.shields.io/badge/Nginx-009639?style=for-the-badge&logo=nginx&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

### 협업

![Discord](https://img.shields.io/badge/Discord-5865F2?style=for-the-badge&logo=discord&logoColor=white)
![Notion](https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)

### Database

-   **MySQL 8.0**: 버스커 정보 데이터 저장
-   **MongoDB**: 버스커 관련 추가 데이터 저장

### Message Queue

-   **Apache Kafka**: 비동기 이벤트 처리 및 서비스 간 통신

### Documentation

-   **Swagger/OpenAPI 3.0**: API 문서화

### Build & Deploy

-   **Gradle**: 빌드 도구
-   **Docker**: 컨테이너화

## 🚀 주요 기능

### 1. 버스커 정보 조회

-   **버스커 정보 조회**: UUID를 통한 버스커 정보 조회
-   **마이페이지 정보 제공**: 버스커 마이페이지에 필요한 정보 제공
-   **프로필 정보 조회**: 버스커 프로필 관련 정보 조회

### 2. 이벤트 기반 통신

-   **Kafka Consumer**: 버스커 정보 이벤트 수신
-   **비동기 처리**: 이벤트 기반 비동기 데이터 처리
-   **이벤트 타입**: BuskerInfoEvent, BuskerFollowerCountEvent

### 3. API 제공

-   **RESTful API**: 표준 REST API 제공
-   **응답 표준화**: 통일된 응답 형식 제공
-   **예외 처리**: 체계적인 예외 처리

### 4. 서비스 디스커버리

-   **Eureka Client**: 마이크로서비스 디스커버리에 등록
-   **서비스 등록**: 자동 서비스 등록 및 헬스체크

## 📁 프로젝트 구조

```
src/main/java/back/vybz/busker_info_read_service/
├── busker_info/              # 버스커 정보 도메인
│   ├── application/          # 버스커 정보 서비스 로직
│   │   ├── BuskerInfoReadService.java
│   │   └── BuskerInfoReadServiceImpl.java
│   ├── domain/               # 버스커 정보 도메인 모델
│   │   └── BuskerInfoRead.java
│   ├── dto/                  # 버스커 정보 DTO
│   │   └── ResponseBuskerInfoReadDto.java
│   ├── infrastructure/       # 버스커 정보 리포지토리
│   │   └── BuskerInfoReadRepository.java
│   ├── presentation/         # 버스커 정보 컨트롤러
│   │   └── BuskerInfoReadController.java
│   └── vo/                   # 버스커 정보 VO
│       └── ResponseBuskerInfoReadVo.java
├── common/                   # 공통 모듈
│   ├── config/               # 설정 클래스들
│   │   ├── MongoConfig.java
│   │   └── SwaggerConfig.java
│   ├── entity/               # 공통 엔티티
│   │   ├── BaseResponseEntity.java
│   │   └── BaseResponseStatus.java
│   └── exception/            # 예외 처리
│       ├── AsyncExceptionHandler.java
│       ├── BaseException.java
│       ├── BaseExceptionHandler.java
│       └── BaseExceptionHandlerFilter.java
├── kafka/                    # Kafka 관련 모듈
│   ├── config/               # Kafka 설정
│   │   ├── BuskerFollowerCountEventKafkaConfig.java
│   │   ├── BuskerInfoEventKafkaConfig.java
│   │   └── CommonKafkaConfig.java
│   ├── consumer/             # Kafka Consumer
│   │   ├── BuskerFollowerCountEventConsumer.java
│   │   ├── CreateBuskerInfoEventConsumer.java
│   │   ├── DeleteBuskerInfoEventConsumer.java
│   │   └── UpdateBuskerInfoEventConsumer.java
│   └── event/                # Kafka 이벤트
│       ├── BuskerFollowerCountEvent.java
│       └── BuskerInfoEvent.java
└── BuskerInfoReadServiceApplication.java
```

## 📚 API 문서

Swagger UI를 통해 API 문서를 확인할 수 있습니다:

-   **URL**: `http://localhost:8000/busker-info-read-service/swagger-ui.html`
-   **API 그룹**: Busker-Info-Read-Service

### 주요 API 엔드포인트

#### 버스커 정보 조회 API

-   `GET /api/v1/busker-info-read/{buskerUuid}` - UUID로 버스커 정보 조회

### API 요청/응답 예시

#### 버스커 정보 조회 요청

```
GET /api/v1/busker-info-read/busker-123
```

#### 버스커 정보 조회 응답

```json
{
    "status": "SUCCESS",
    "message": "요청이 성공적으로 처리되었습니다.",
    "data": {
        "buskerUuid": "busker-123",
        "nickname": "스트리트뮤지션",
        "profileImageUrl": "https://example.com/profile.jpg",
        "introduction": "거리에서 음악을 연주하는 버스커입니다.",
        "followerCount": 150,
        "createdAt": "2024-01-01T00:00:00",
        "updatedAt": "2024-01-01T00:00:00"
    }
}
```

## 🚀 설치 및 실행

### 1. 사전 요구사항

-   Java 17
-   Gradle 8.4+
-   Docker (선택사항)
-   MySQL 8.0
-   MongoDB
-   Apache Kafka

### 2. 로컬 실행

```bash
# 프로젝트 클론
git clone <repository-url>
cd vybz-busker-info-read

# Gradle 빌드
./gradlew clean build

# 애플리케이션 실행
./gradlew bootRun
```

### 3. Docker 실행

```bash
# Docker 이미지 빌드
docker build -t vybz-busker-info-read-service .

# Docker 컨테이너 실행
docker run -p 8080:8080 vybz-busker-info-read-service
```

## ⚙️ 환경 설정

### 주요 설정 파일

-   `application.yml`: 기본 설정
-   `application-dev.yml`: 개발 환경 설정

### 환경 변수

```yaml
# 데이터베이스 설정
spring:
  datasource:
    url: jdbc:mysql://${DB_HOST}:${DB_PORT}/busker_info_read?useSSL=true&allowPublicKeyRetrieval=true&serverTimezone=Asia/Seoul
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}

  data:
    mongodb:
      uri: mongodb://${MONGO_USERNAME}:${MONGO_PASSWORD}@${MONGO_HOST}:${MONGO_PORT}/vybz?authSource=admin&replicaSet=myReplicaSet

# Kafka 설정
spring:
  kafka:
    bootstrap-servers: ${KAFKA_BOOTSTRAP_SERVERS}
```

## 🏗️ 아키텍처

### Hexagonal Architecture (Clean Architecture)

-   **Domain Layer**: 버스커 정보 도메인 모델과 비즈니스 로직
-   **Application Layer**: 버스커 정보 서비스 로직과 유스케이스
-   **Infrastructure Layer**: 데이터베이스 접근과 외부 시스템 연동
-   **Presentation Layer**: REST API 엔드포인트

### 마이크로서비스 패턴

-   **Service Discovery**: Eureka Client를 통한 서비스 등록
-   **Stateless**: 상태 없는 서비스 설계
-   **API Gateway**: 통합 API 게이트웨이 연동

### 이벤트 기반 아키텍처

-   **Kafka Consumer**: 다른 서비스의 이벤트 수신 및 처리
-   **비동기 통신**: 서비스 간 느슨한 결합
-   **이벤트 타입**: BuskerInfoEvent, BuskerFollowerCountEvent

### 데이터베이스 설계

-   **MySQL**: 버스커 기본 정보 데이터 저장
-   **MongoDB**: 버스커 관련 추가 데이터 저장
-   **Auditing**: 생성/수정 시간 자동 관리
-   **인덱스**: 성능 최적화를 위한 인덱스 설정

### 공통 모듈

-   **BaseResponseEntity**: 통일된 응답 형식
-   **BaseException**: 체계적인 예외 처리
-   **SwaggerConfig**: API 문서화 설정

## 🔧 개발 가이드

### 코드 컨벤션

-   **패키지 구조**: 도메인별 계층 분리
-   **네이밍**: 명확하고 일관된 네이밍 규칙
-   **예외 처리**: BaseException을 통한 통일된 예외 처리
-   **로깅**: Slf4j를 통한 구조화된 로깅

### DTO/VO 패턴

-   **DTO**: 내부 서비스 간 데이터 전송
-   **VO**: 외부 API 요청/응답 데이터
-   **변환 메서드**: DTO ↔ VO 변환 메서드 제공

### Kafka 이벤트 처리

-   **Consumer**: 다른 서비스의 이벤트 수신 및 처리
-   **이벤트 타입**: BuskerInfoEvent, BuskerFollowerCountEvent
-   **비동기 처리**: 이벤트 기반 비동기 데이터 처리

### 테스트

```bash
# 단위 테스트 실행
./gradlew test

# 통합 테스트 실행
./gradlew integrationTest
```

### 빌드

```bash
# Gradle 빌드
./gradlew clean build

# JAR 파일 생성
./gradlew bootJar
```

빌드된 JAR 파일은 `build/libs/` 디렉토리에 생성됩니다.

## 📝 라이선스

이 프로젝트는 VYBZ 팀의 내부 프로젝트입니다.

## 👥 팀

-   **개발팀**: VYBZ Backend Team

---

**VYBZ Busker Info Read Service** - 효율적인 버스커 정보 조회 서비스
