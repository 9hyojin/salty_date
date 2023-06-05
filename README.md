
# salty_date 🐰
a website that offers free dating spots
높아진 물가에 짠테크가 유행인 요즘! 
전국의 무료 데이트 장소를 공유할 수 있는 웹사이트입니다.

# 개발환경 💻

Java 17

JDK 1.8.0

Framework : SpringBoot 3.0.6

IDE : IntelliJ

Server : Apache Tomcat 10.1.8

Database : MySQL


# 주요기능 💡

## 1. 회원가입 및 로그인

Spring Security를 이용한 회원가입(인증)과 jwt를 이용한 인가

아이디와 메일주소 중복검사, 유효성검사

가입한 아이디와 비밀번호로 로그인

로그인 성공 시 세션에 정보 저장 / 로그아웃 시 세션 삭제 후 


## 2. 메인페이지, 상세 페이지(게시글 CRUD) 

- 등록:

작성자는 로그인한 사용자의 아이디

게시글 등록시간

- 수정: 본인이 작성한 글의 제목, 본문 수정, 수정시간

- 삭제: 본인이 작성한 글 삭제


## 3. 댓글기능

등록: 작성자는 로그인한 사용자의 아이디

조회: 댓글과 답글 시간순 정렬

수정: 본인이 작성한 댓글의 내용 수정

삭제: 본인이 작성한 댓글 삭제


## 4. 첨부파일기능 

이미지파일 첨부 및 수정


## 5. 태그, 카테고리 페이지

게시글 등록시 카테고리(태그) 설정: 계절, 연령, 실내/야외, 지역
카테고리별 게시글 목록


## 6. 페이징

페이지당 글 20개씩 조회
맨앞으로, 앞으로, 뒤로, 맨뒤로 이동하는 버튼

## AWS

1. EC2 server
2. RDS (mysql)
3. S3
4. Route53 (domain)

