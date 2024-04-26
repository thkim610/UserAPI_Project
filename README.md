# 회원(USER) 기능 RESTful API
이 프로젝트는 window 환경에서 테스트 되었습니다.

## 🖥️ Versions

- IDE : IntelliJ IDEA Ultimate
- JDK : Java 1.8
- Spring Boot : 2.7.9
- DBMS : MySQL 8
- DB Library : JPA

## 📢 Dependancy

- Lombok
- Data JPA
- Validation
- SwaggerUI(springdoc)

## 🖨주요 기능

- 회원가입(POST) - 입력된 정보를 기반으로 회원 정보를 데이터 베이스에 저장
    - 회원 id / 비밀번호 / 닉네임 / 이름 / 전화번호 / 이메일주소
    - 정상 동작한 경우, 응답코드 201(Create) 반환.
- 회원 목록 조회(GET) - 입력된 회원들의 정보를 목록으로 조회
    - 페이징 기능
        - page : 페이지 번호
        - pageSize :  한 페이지에 표시될 수 있는 최대 회원의 수
        - sort : 가입일 순(DESC) / 이름 순(ASC)
    - 응답코드 200 반환.
- 회원 수정(PUT) - 해당하는 회원 아이디를 가진 회원의 정보가 수정됩니다.
    - 수정 가능한 정보 : 닉네임, 비밀번호, 전화번호, 이메일
    - 정상 동작한 경우, 응답코드 200(OK) 반환.

## 📄도메인 모델

### 회원 도메인

| 컬럼명 | 데이터 타입 | 설명 | 기타 |
| --- | --- | --- | --- |
| id | Long | 회원 번호 | PK , NOT NULL |
| user_id | String | 회원 ID | Unique, NOT NULL |
| password | String | 비밀번호 | 10자리 이상 20자리 이하, NOT NULL |
| nick_name | String | 닉네임 | NOT NULL |
| name | String | 이름 | NOT NULL |
| phone_number | String | 전화번호 | 전화번호 형식, NOT NULL <br> 예1) 01011112222 <br> 예2)  010-111(1)-2222 |
| email | String | 이메일 | 이메일 형식, NOT NULL <br> 예) aa@bb.cc |
| registered_at | DATETIME | 가입일 | NOT NULL |

### ERD

![image](https://github.com/thkim610/UserAPI_Project/assets/112153004/ecc880eb-bbb7-40e6-b0fe-6fdd41ee956c)


## ✒ 파일 디렉토리 구조

```
├── 📁user
│   └── 📄UserApplication.java
│
├── 📁common
│   ├── 📄Api.java
│   ├── 📄Pagination.java
│   │
│   └── 📁exception
│       ├── 📄ApiException.java
│       ├── 📄ApiExceptionHandler.java
│       ├── 📄ErrorResult.java
│       └── 📄UserErrorCode.java
│
├── 📁config
│   ├── 📁objectmapper
│   │   └── 📄ObjectMapperConfig.java
│   │
│   └── 📁swagger
│       └── 📄SwaggerConfig.java
│
├── 📁controller
│   └── 📄UserApiController.java
│
├── 📁db
│   ├── 📄UserEntity.java
│   └── 📄UserRepository.java
│
├── 📁filter
│   └── 📄LoggerFilter.java
│
├── 📁model
│   ├── 📄UserDto.java
│   ├── 📄UserEditRequest.java
│   └── 📄UserRequest.java
│
└── 📁service
├── 📄UserConverter.java
└── 📄UserService.java
```

## 프로젝트 환경

### build.gradle

```groovy
plugins {
	id 'java'
	id 'org.springframework.boot' version '2.7.9'
	id 'io.spring.dependency-management' version '1.0.15.RELEASE'
}

group = 'com.example'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '1.8'

configurations {
	compileOnly {
		extendsFrom annotationProcessor
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-validation'
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	//swagger UI 사용
	// https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-ui
	implementation group: 'org.springdoc', name: 'springdoc-openapi-ui', version: '1.7.0'
	compileOnly 'org.projectlombok:lombok'
	runtimeOnly 'com.mysql:mysql-connector-j'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

tasks.named('test') {
	useJUnitPlatform()
}

```

## 데이터베이스 설정
MySQL에서 `user` 데이터베이스를 생성하고, 제공된 [`user.sql`](https://github.com/thkim610/UserAPI_Project/blob/main/user.sql) 파일을 사용하여 스키마를 초기화하세요.

### application.yaml

MySQL 연동을 위해 다음과 같이 작성해주세요.

```yaml
spring:
  jpa:
    show-sql: true # SQL 로그를 콘솔에 출력
    properties:
      format_sql: true # SQL을 포맷팅
      dialect: org.hibernate.dialect.MySQL8Dialect # 사용하는 데이터베이스를 MySQL 8버전으로 설정
    hibernate:
      ddl-auto: validate # 애플리케이션 시작 시, Entity 클래스와 데이터베이스 테이블 구조를 비교 검증
  datasource:
    url: jdbc:mysql://localhost:3306/user?serverTimezone=Asia/Seoul # 데이터베이스 연결 URL(서버 시간대 : 'Asia/Seoul')
    driver-class-name: com.mysql.cj.jdbc.Driver # MySQL JDBC 드라이버의 클래스 이름
    username: # 데이터베이스 연결을 위한 사용자 이름
    password: # 데이터베이스 연결을 위한 비밀번호
```

## 기능 및 사용방법

### 회원가입
`POST /api/user/join`을 사용하여 회원 가입을 진행할 수 있습니다.

예제 요청
```json
{
  "user_id": "string",
  "password": "stringstri",
  "nick_name": "string",
  "name": "string",
  "phone_number": "010-6943361",
  "email": "string@aa.com"
}
```

### 회원목록 조회
`GET /api/user/list`를 사용하여 회원목록을 조회할 수 있습니다.
쿼리 파라미터로 `page`, `pageSize` 값을 넘기면 페이징 처리가 가능합니다.

### 회원 정보 수정
`PUT /api/user/{userId}`를 사용하여 회원 정보(비밀번호, 닉네임, 전화번호, 이메일)를 수정할 수 있습니다.

예제 요청
```json
{
  "password": "integerint",
  "nick_name": "integer",
  "phone_number": "011-365-1428",
  "email": "integer@aa.com"
}
