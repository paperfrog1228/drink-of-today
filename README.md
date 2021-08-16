# 프로젝트 소개

## 개발 목적

스프링의 기본기를 활용하며 익히고 나의 것으로 만들기 위하여 개발하였습니다.

## 개발 기간

2021년 8월 9일 ~ 아직 진행중입니다

배움에는 끝이 없다고 생각하며 새로운 지식을 습득 시 해당 프로젝트에 적용시키는 것을 목표하고있습니다.

## 오늘의 술상?
![image](https://user-images.githubusercontent.com/11247319/129549988-f9eb014b-a0cd-403a-9d34-56bb43fc5f55.png)

코로나로 인해 잦은 혼술 혹은 오랜만에 만난 동료와의 술자리를 보여주고 공유하며 소통하는 커뮤니티 웹사이트입니다.

## 개발 환경

```
Spring boot : 2.5.3
Java : 11(jar)
Build tool : Gradle
Dependencies : SpringWeb, Lombok, thymeleaf
HTML,css library : bootstrap 5.0.2
```

에노테이션 기반으로 짧은 코드를 구현하게 해주는 lombok 라이브러리를 사용하였으며

서버 사이드 렌더링을 위해 Natural Template을 지원하는 thymeleaf를 사용하였습니다.

html, css는 효율적이고 보기 좋은 결과물을 만들 자신이 없어 bootstrap을 사용하였습니다.

# 기능

## 게시판

### 작성하기

![글쓰기](https://user-images.githubusercontent.com/11247319/129548410-b39aa590-c485-46b5-9489-21cb7b8c2b62.gif)

글과 사진을 올릴 수 있습니다. 사진의 경우 서버내에서 이름을 uuid로 변경하여 저장합니다.

![image](https://user-images.githubusercontent.com/11247319/129548855-9c6a53a4-1fce-487e-9832-8e17844c00e9.png)

현재는 로컬 서버를 사용하고 있어 로컬에 저장되어있습니다.

### 게시물 열람
![Aug-16-2021 19-24-52](https://user-images.githubusercontent.com/11247319/129549618-3941262e-abf7-444d-a95b-33034cd8d950.gif)

게시물에 사진이 있다면 첫번째 사진을 썸네일으로 사용하며 사진이 없을 경우 더미 이미지를 썸네일로 보여줍니다.
원하는 게시물을 클릭하여 열람가능합니다.

## 회원가입과 로그인
### 검증
![join](https://user-images.githubusercontent.com/11247319/129550476-79a0365f-d48b-43ce-8f68-656ce98cca24.gif)
입력값이 적절하지 않을 경우 오류 문구를 출력해줍니다. 현재는 서버 사이드에서만 검증을 하고 있습니다. 

#### 커스텀 검증
기본 스프링이 제공하는 에노테이션 뿐 아니라 필요한 검증은 직접 에노테이션으로 구현하여 사용하였습니다.
![image](https://user-images.githubusercontent.com/11247319/129550889-d8547961-570d-4de4-88dd-6bb4e15c3f24.png)
![image](https://user-images.githubusercontent.com/11247319/129551067-f31ef498-1385-439d-959f-2af561ed2ba9.png)

### 로그인
로그인 유무에 따라 보여지는 html이 다르게 처리하였습니다.(thymeleaf template 사용)
- 로그인 하기 전
![image](https://user-images.githubusercontent.com/11247319/129551292-5c0a9d6b-2f9e-4731-a95e-4396836b3e5b.png)
로그인 하기 전에는 로그인 버튼이 보이며 글쓰기 버튼이 보이지 않습니다.
- 로그인
- ![Aug-16-2021 19-41-44](https://user-images.githubusercontent.com/11247319/129551639-8b5161df-a521-4bcd-9808-c77de2d13015.gif)
- 로그인 했을 경우 로그인 버튼 대신 사용자의 닉네임을 보여주며 글쓰기 버튼을 사용할 수 있습니다.

#개선점
짧은 시간 안에 스프링과 더불어 JPA를 숙달하기에는 많은 무리가 있었습니다. 
현재 모든 저장된 객체들은 서버의 메모리에 저장되어 있기에 서버를 재가동하면 모두 사라지게 됩니다.
그러나 객체지향적으로 설계를 해놨기 때문에 이후에 JPA를 적용시키는데애는 크게 무리되지 않을 것이라 생각됩니다.

#느낀점
늦게 시작했기 때문에 남들보다 배로 열심히 배우고 공부합니다.
아직까진 미숙한 코드, 적재적소하지 못한 프레임워크 사용 등이 보일지라도
빠르게 성장하는 모습을 보여드릴 수 있을 것이라 다짐합니다.
