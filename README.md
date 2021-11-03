# 오늘의 술상 (http://drinkoftoday.shop)
## 목차
* [프로젝트 소개](#프로젝트-소개)
* [주요 기능](#주요-기능)
* [프로젝트 구조](#프로젝트-구조)
* [문제 발생과 해결 과정](#문제-발생과-해결-과정)
  + [데이터베이스](#데이터베이스) 
  + [보안](#보안)
  + [실패한 경험](#실패한-경험)
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
<div style="page-break-after: always;"></div>

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

## 댓글 기능(NEW!)
### 댓글 등록
> 댓글 등록 후 AJAX를 이용하여 댓글 부분만 갱신을 합니다. 

![comment_add_test](https://user-images.githubusercontent.com/11247319/140060482-f8f576c4-5b23-4e5e-82a6-e096b0f73bcf.gif)
<br>
<br>

### 댓글 삭제
> 마찬가지로 댓글 삭제 후  AJAX를 이용하여 댓글 부분만 갱신을 합니다.
> 댓글 삭제는 댓글 작성자만 가능합니다.

![comment_delete_test](https://user-images.githubusercontent.com/11247319/140061199-0a27f82a-15f5-459d-b62c-fd01375b4454.gif)
<br>
<br>

# 프로젝트 구조
## 개발 환경
>1인 개발의 한계로 서버 위주로 개발하였으며 서버 사이드 렌더링을 사용하였습니다.
```
Spring boot : 2.5.3
Java : 11(jar)
Build tool : Gradle
Dependencies : SpringWeb, Lombok, thymeleaf, lucy-xss-servlet-filter
HTML,css library : bootstrap 5.0.2
RDB : maria DB
ORM : JPA
CD : Jenkins
Cloud : AWS EC2 (micro)
PaaS : heroku
```

에노테이션 기반으로 짧은 코드를 구현하게 해주는 lombok 라이브러리를 사용하였으며<br>
서버 사이드 렌더링을 위해 Natural Template을 지원하는 thymeleaf를 사용하였습니다.<br>
XSS에 취약한 것을 확인하고 네이버 사의 lucy-xss-servlet-filter라이브러리를 적용했습니다.<br>
html, css는 효율적이고 보기 좋은 결과물을 만들 자신이 없어 bootstrap을 사용하였습니다.<br>
LINE 메신저의 채팅 봇 활용을 위해 heroku PaaS를 사용했습니다.


## 서버 구조
> 로컬에서 작업 후 깃허브에 커밋 시 젠킨스가 자동으로 빌드 후 배포하는 형식이며
> 현재 빌드와 운영 모두 한 클라우드에서 진행하고 있습니다.<br>테스트 실패 시 디스코드로 알림이 전송되며 새로운 글 등록 시 LINE(채팅 봇)으로 알림이 전송됩니다.

![슬라이드1](https://user-images.githubusercontent.com/11247319/137383760-17fe6d02-51b1-4431-9b4d-0877316beb98.JPG)
<br>
## ER Diagram
> 데이터베이스 설계에 대해서 많이 약하다고 생각하고 있으며 최대한 불필요한 연관관계는 지양했습니다.(ex 인증 토큰과 멤버)

![image](https://user-images.githubusercontent.com/11247319/140069029-709ac11b-3aab-49ec-9961-868f884f79f4.png)

# 문제 발생과 해결 과정
## 데이터베이스
### maria db에서 key값으로 uuid 사용하기
#### 문제 발생
![mariadb1](https://user-images.githubusercontent.com/11247319/134466596-ae7ee238-fc76-4d65-87c2-b44d270e706f.png)
uuid 기본 키를 String으로 설정 시 Specified key was too long; max key length is 767 bytes라는 오류가 발생하며 테이블이 생성되지 않는다.

<br>

#### 문제 환경
- 이메일 인증 토큰 db에서 기본 키 값을 uuid로 설정하였다.
- ORM에서 객체는 String, DB는 varchar(255)로 저장하였다.
- 로컬 개발 환경(h2 사용)에서는 이상없이 동작한다.

<br>

#### 문제 접근
1. 오류 로그 확인 (키가 너무 길다. 최대 키 값은 767byte다.)
- utf8은 3바이트이기 때문에 varchar(255)는 최대 값을 넘지 않는다.(3 * 255 = 765)
2. 현재 maria db가 사용중인 character set이 utf8이 맞는지 확인
 ![image](https://user-images.githubusercontent.com/11247319/134467561-f44d31d5-cfe2-4f24-aece-078cf1960299.png)
 
 -> 현재 사용중인 character set은 utf8mb4였으며 이것은 개당 4바이트이다. (문제 발견) 
> utf8mb4 : uft8에서 이모티콘을 표현하기 위해 mysql에서 지원하는 character set

<br>

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

<br>

#### 결과와 느낀점
유니코드 바이트 할당 문제로 시작한 공부였지만 uuid 를 기본 키로 사용 할 수 있는 방법과 장단점, 그리고 String을 기본 키로 사용 시의 문제점을 알게 되었다.
특히, uuid를 기본 키로 사용하는 것은 조금 더 활용 방안을 고안하여 이후에 다른 테이블에서 적용 할 수 있을 것이라 생각된다.
<br>
<br>
<br>
## 보안
### 데이터베이스 노출 문제
#### 문제 발생
![image](https://user-images.githubusercontent.com/11247319/137350913-c2fd1739-d9c8-4ad8-b9da-63fa3b8b6748.png)
내 사이트를 이용하는 지인들에게 자주쓰는 암호는 피해달라고 당부했으나 다들 무시하고 사용하던 비밀번호를 사용했다. <br>최근 나의 실수로 데이터베이스를 몇 번 탈취당한 경험이 있어 이대로 놔두면 안된다고 판단하였다.

<br>

#### 문제 환경
- 현재 계정에 저장되는 개인정보는 이메일과 로그인ID, 패스워드이다.
- 그 중 패스워드 만큼은 외부로 노출되면 안된다고 판단했다.

<br>

#### 문제 접근
1. 암호화라는 개념에 무지했기 때문에 그에 대해 공부하였다.
2. 공부하는 중 해싱에 대하여 알게되었고 암호화와 해싱의 차이를 이해하였다.
3. 패스워드 저장에는 해싱이 적합하다 판단하여 해싱 처리를 하였고 부가적인 처리를 하였다.

<br>

#### 문제 해결 방법
1. Java security MessageDigest의 클래스를 이용하여 해싱하였다.
> 새로운 문제점 발견

- 동일한 메세지는 동일한 다이제스트를 갖는다.
- 그렇기 때문에 브루트포스 기법으로 쉽게 유추할 수 있다.
2. 브루트포스에 대한 내성을 위해 솔팅과 키스트레칭 처리를 하였다.(부가적인 처리)
![image](https://user-images.githubusercontent.com/11247319/137363603-cd4703ee-dc7f-4dc4-ad32-07e15d9ec9e2.png)
- 솔팅(Salting) : 특정 문자열을 추가하여 원형을 측정하기 어렵게 하는 것으로 추가되는 특정 문자열을 솔트(salt) 라고 한다.<br>
Message Digest의 update 메소드를 이용해 쉽게 구현하였다.
- 키 스트레칭(Key Stretching) :  입력 값으로 다이제스트를 생성하고, 생성된 다이제스트를 입력 값으로 하여 다이제스트를 생성하며 이를 반복하는 방법이다.<br>
for문을 이용하여 반복 해싱을 구현했다.

이번 프로젝트에서 솔트 문자열과 키 스트레칭 반복 횟수는 모두 스프링 프로퍼티에서 받아오며 <b>해당 프로퍼티는 아무도 접근할 수 없게 서버에 보관되어있다.<b>


#### 결과와 느낀점
![image](https://user-images.githubusercontent.com/11247319/137364715-99fd4a4d-693e-410e-b27f-6a23d1b4a1ff.png)
성공적으로 처리하였으며 해싱은 단방향 암호화이기 떄문에 위의 결과의 값이 노출되어도 상관없다.<br>
사실 이번에 자바 도큐먼트를 대충 읽고 개발에 들어가 상당히 많은 실수를 하였다. 다시 메세지 다이제스트를 다룰 때 같은 실수를 반복하지 않도록 블로그에 글로 남겨놓았다.<br>
링크 : [메세지 다이제스트의 메소드 설명과 테스트](https://paperfrog.tistory.com/15)

<br>
<br>
<br>

## 실패한 경험
### HEIC 파일을 라인 메신저를 통해 JPG로 변환하기
#### 문제 발생
![image](https://user-images.githubusercontent.com/11247319/137370402-ec68e288-6c8e-4d5f-8025-4bff57c9ae8d.png)
아이폰으로 촬영한 사진을 게시글에 올리면 이미지가 보이지 않는 오류 확인.
아이폰으로 촬영한 사진의 확장자는 HEIC 이라는 파일로 확인했다.

<br>

#### 문제 환경
- HEIC 의외의 jpg, gif는 문제없이 보인다.
- HEIC를 지원하는 웹브라우저는 현재 없다.

<br>

#### 고려했던 방법
1. 서버 내에서 JPG로 컨버팅을한다.
> 클라우드 성능을 줄일 예정이라 서버의 부담을 줄이고 싶어서 최대한 기피했다.
2. 외부의 API를 이용해서 HEIC를 JPG로 변환한다.
3. 아이폰의 라이브 포토는 메신저를 통해 전송할 경우 정지된 이미지로 전달 받은 경험이 있다.
> "그러면 라인 메신저로 사진을 전송 후 다시 받아오면 되지않을까?" 라는 생각에서 시작

<br>

#### 문제 해결 과정
1. 라인 메세지를 받아 줄 채팅 봇을 만들었다. 그 과정에서 heroku와 라인 Notify api를 학습하였다.<br>
텍스트와 이미지를 똑같이 응답하는 것과 보낸 사람의 프로필을 응답하는 채팅 봇의 기능을 확인하였다. 또한 응답하는 이미지는 JPG인 것을 확인하였다.
> heroku : 클라우드 기반의 서비스를 제공하는 PaaS이다.

![image](https://user-images.githubusercontent.com/11247319/137374053-79363110-0aa9-4067-b185-f849099570dc.png)

2. 라인 Message API 문서를 읽어보고 기본적인 사용법을 익혔으며 스프링 서버 내에서 채팅 봇으로 텍스트 메시지 전송을 확인하였다.
![image](https://user-images.githubusercontent.com/11247319/137374845-023e4fde-1302-4075-bf22-7a500e779bf9.png)

3. 스프링 서버내에서 채팅 봇으로 이미지 전송은 계속 400에러를 응답받았다.
> 새로운 문제 발생
#### 문제 검토
![image](https://user-images.githubusercontent.com/11247319/137375580-b5c9450c-e63b-4296-b903-70c6345b95da.png)
라인 메세징 api에서 이미지는 공개적으로 접근 가능한 서버에 먼저 저장한 후 그 서버의 url을 json으로 제공하는 방식이었다.<br>
 또한 <b>HTTPS<b>를 적용한 url만 허용하며 JPG와 PNG만 허용한다. 즉 설계부터 잘못되었기에 실패했다.
![image](https://user-images.githubusercontent.com/11247319/140066865-40f9ff25-381b-4fd8-b474-7546536b876b.png)

#### 추후 보완할 점
현재는 새로운 글 작성 시 채팅 봇을 친구 추가한 사용자에게 알림을 주는 용으로 사용하고있다. 해당 변환 과정에 대해서는 조금 더 조사를 해 볼 예정이다.
<br>
<br>
<br>
<br>
# 향후 추가 할 기능
## 현재 고민중인 안건
- 서버 성능 향상을 위해 어떻게 해야할까?
- Nginx를 적용했으나 현재는 리버스 프록시의 역할만 하고있다. 어떻게 활용해야 할까?
- JPA에서 성능을 향상 시키려면 어떻게 해야할까?

## 기능 관련
- 멤버와 게시물의 연관 관계 매핑과 그에 맞는 추가 기능 (멤버 별로 게시물 모아보기 등등)
- 검색 기능
