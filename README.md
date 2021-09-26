# 오늘의 술상 (http://drinkoftoday.shop)
## 목차
- [프로젝트 소개](#프로젝트-소개)
- [주요 기능](#주요-기능)
- [프로젝트 구조](#프로젝트-구조)
- [문제 발생과 해결 과정](#문제-발생과-해결-과정)
# 프로젝트 소개
## 프로젝트 소개
http://drinkoftoday.shop

## 오늘의 술상?
>술자리 혹은 혼술(혼자 마시는 술) 관련 사진을 공유하고 소통하는 커뮤니티 웹사이트입니다.

![image](https://user-images.githubusercontent.com/11247319/133449902-f9cd93dc-5119-483e-8ba5-0642fd617d7f.png)


## 개발 기간
> 2021년 8월 9일 ~ 현재까지 새로운 기능 개발 중


# 주요 기능
## 회원 
### 회원가입
> 글자 수와 이미 사용되고 있는 id, 닉네님 이메일을 체크하며 이메일란과 이메일 확인란이 동일한지 체크합니다.

![join_valid](https://user-images.githubusercontent.com/11247319/133461074-058cca27-e3cc-49d6-9577-dd4dd80a8bb1.gif)

### 이메일 인증
> 회원가입 후 토큰을 포함한 링크를 통해 이메일을 인증합니다.

![join_email](https://user-images.githubusercontent.com/11247319/133473251-c1b4f9c0-de16-43d8-a29d-e6d40e8c2f49.gif)

### 로그인
>로그인 시 회원의 닉네임을 우측 상단에 표시하며 글쓰기 버튼을 화면에 노출시킵니다.

![login](https://user-images.githubusercontent.com/11247319/133474152-50c80b34-444c-4c0f-8028-6adaee978f9c.gif)

## 게시판
### 작성하기
> 글과 사진을 올릴 수 있습니다. 사진의 경우 서버내에서 이름을 uuid로 변경하여 저장합니다.

![글쓰기](https://user-images.githubusercontent.com/11247319/129548410-b39aa590-c485-46b5-9489-21cb7b8c2b62.gif)
![image](https://user-images.githubusercontent.com/11247319/129548855-9c6a53a4-1fce-487e-9832-8e17844c00e9.png)

### 게시물 열람
> 게시물에 사진이 있다면 첫번째 사진을 썸네일으로 사용하며 사진이 없을 경우 더미 이미지를 썸네일로 보여줍니다. 
> 원하는 게시물을 클릭하여 열람가능합니다. 또한 게시물에 대한 권리가 없다면 수정과 삭제 버튼이 화면에 노출되지 않습니다.

![board_read](https://user-images.githubusercontent.com/11247319/133475115-7d4470c5-b07a-4858-b2fe-749af51fa83b.gif)

### 작성 제한(인터셉터)
> 미로그인 시 글 쓰기 버튼을 화면에 노출하지 않 글 쓰기 페이지로 url을 통해 접근시 인터셉터를 이용해 제한하고 로그인 페이지로 이동시킵니다.

![intercept](https://user-images.githubusercontent.com/11247319/133476808-eaa84b15-d9e4-4707-b587-ab0c4cff24b5.gif)


# 프로젝트 구조
## 개발 환경
>1인 개발의 한계로 서버 위주로 개발하였으며 서버 사이드 렌더링을 사용하였습니다.
```
Spring boot : 2.5.3
Java : 11(jar)
Build tool : Gradle
Dependencies : SpringWeb, Lombok, thymeleaf
HTML,css library : bootstrap 5.0.2
RDB : maria DB
ORM : JPA
CD : Jenkins
Cloud : AWS EC2 (micro)
```

에노테이션 기반으로 짧은 코드를 구현하게 해주는 lombok 라이브러리를 사용하였으며
서버 사이드 렌더링을 위해 Natural Template을 지원하는 thymeleaf를 사용하였습니다.
html, css는 효율적이고 보기 좋은 결과물을 만들 자신이 없어 bootstrap을 사용하였습니다.

## 서버 구조
> 로컬에서 작업 후 깃허브에 커밋시 젠킨스가 자동으로 빌드 후 배포하는 형식이며
> 현재 빌드와 운영 모두 한 클라우드에서 진행하고 있습니다.

![image](https://user-images.githubusercontent.com/11247319/133485471-67c1e95f-d261-48f2-b005-6a56fb78fba6.png)

## ER Diagram
> 현재 게시물, 게시물의 이미지, 멤버, 인증 토큰 이렇게 4개의 테이블을 운용하고 있으며
> 인증토큰과 멤버 사이의 연관 관계는 필요없다고 판단하여 서로 연관 되있지 않습니다.

![image](https://user-images.githubusercontent.com/11247319/133489583-3febd08c-e481-4c4f-bbd0-1ba7a4acbd77.png)

# 문제 발생과 해결 과정
## 데이터베이스
### maria db에서 key값으로 uuid 사용하기
#### 문제 발생
![mariadb1](https://user-images.githubusercontent.com/11247319/134466596-ae7ee238-fc76-4d65-87c2-b44d270e706f.png)
uuid 기본 키를 String으로 설정 시 Specified key was too long; max key length is 767 bytes라는 오류가 발생하며 테이블이 생성되지 않는다.
#### 문제 환경
- 이메일 인증 토큰 db에서 기본 키 값을 uuid로 설정하였다.
- ORM에서 객체는 String, DB는 varchar(255)로 저장하였다.
- 로컬 개발 환경(h2 사용)에서는 이상없이 동작한다.
#### 문제 접근
1. 오류 로그 확인 (키가 너무 길다. 최대 키 값은 767byte다.)
- utf8은 3바이트이기 때문에 varchar(255)는 최대 값을 넘지 않는다.(3 * 255 = 765)
2. 현재 maria db가 사용중인 character set이 utf8이 맞는지 확인
 ![image](https://user-images.githubusercontent.com/11247319/134467561-f44d31d5-cfe2-4f24-aece-078cf1960299.png)
 
 -> 현재 사용중인 character set은 utf8mb4였으며 이것은 개당 4바이트이다. (문제 발견) 
> utf8mb4 : uft8에서 이모티콘을 표현하기 위해 mysql에서 지원하는 character set
#### 문제 해결 방법
##### 1. 기본 character set 설정을 utf8 혹은 3바이트 이하로 설정한다.
- db 스토리지에 따라 최대 키 값의 크기를 설정 할 수도 있다.
- 시스템 설정을 가급적이면 바꾸지 않기를 원하며 확장성을 위해 선택하지 않았다.
##### 2. @Column(length=필요한 크기)로 db 테이블 생성 시 조절한다.
> 새로운 문제점 발견
- uuid의 경우 32개의 문자와 4개의 하이픈이 필요하므로 최소 36개가 강제되므로 메모리 낭비가 심하다.
- 길이가 길기 때문에 정렬에 따른 퍼포먼스 이슈가 있을 수 있다.
- 잘못 된 크기 설정 시 db에 따라 RPAD , LPAD를 처리하므로 조회 시 문제가 생길 수 있다.
##### 3. uuid를 binary(16)으로 고정 변환하여 삽입, 조회한다. (최종 해결)
- uuid는 총 32개의 16진수 숫자이므로(하이픈 제외) 16 바이트로 표현 가능하다.
- ORM에서 객체의 클래스를 UUID로 지정했고 columnDefinition을 통해 binary(16)으로 테이블 정의를 하였다.
- 반대로 조회 시에는 String을 binary로 변환하는 과정이 필요하였기 때문에 따로 구현하였다.

#### 결과와 느낀점
유니코드 바이트 할당 문제로 시작한 공부였지만 uuid 를 기본 키로 사용 할 수 있는 방법과 장단점, 그리고 String을 기본 키로 사용 시의 문제점을 알게 되었다.
특히, uuid를 기본 키로 사용하는 것은 조금 더 활용 방안을 고안하여 이후에 다른 테이블에서 적용 할 수 있을 것이라 생각된다.
# 향후 추가 할 기능
- 멤버와 게시물의 연관 관계 매핑과 그에 맞는 추가 기능 (멤버 별로 게시물점모아보기 등등)
- 검색 기능
- 댓글 기능
