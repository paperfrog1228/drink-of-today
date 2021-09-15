# 프로젝트 소개
## 프로젝트 링크
http://drinkoftoday.shop

## 오늘의 술상?
>술자리 혹은 혼술(혼자 마시는 술) 관련 사진을 공유하고 소통하는 커뮤니티 웹사이트입니다.
- spring을 학습하고 숙달하기 위해 개발했습니다.
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
> 미로그인 시 글 쓰기 버튼을 화면에 노출하지 않지시 글 쓰로 페이지로 url을 통해 접근시 인터셉터를 이용해 제한하고 로그인 페이지로 이동시킵니다.

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

# 향후 추가 할 기능
- 멤버와 게시물의 연관 관계 매핑과 그에 맞는 추가 기능 (멤버 별로 게시물 모아보기 등등)
- 검색 기능
- 댓글 기능
