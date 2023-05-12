# 나만의 광고 관리 플랫폼

<p align="center">
    <img src = "https://user-images.githubusercontent.com/107471786/230712268-f93fae35-81a1-4747-af8c-3d6d35989814.jpeg" width="30%">
</p>

# 프로젝트 정보
프로젝트 목적: 11시 11분 인턴쉽 과제  
개발기간: 2023.03 ~ 2023.04

# 프로젝트 소개

광고 등록, 광고 관리 등의 기능을 가지는 간단한 광고 관리 플랫폼 만들기

# 기술 스택

- Frontend: TypeScript, ReactJS, AntDesign
- Backend: Java, SpringBoot, JPA, SpringSecurity, SpringBatch
- DB: H2

# ERD
![erd](https://user-images.githubusercontent.com/107471786/230759653-c1777ea6-5b96-470d-9509-15171cc2457b.png)

# 주요 기능

## 로그인

https://user-images.githubusercontent.com/107471786/230723392-08f85fa0-6833-4565-8f79-4fc1c0a7ba5b.mov

<img src="https://user-images.githubusercontent.com/58963042/232237180-8fd18578-5235-43f5-a745-5e2af26c7daf.gif" width="500" height="300"/><img src="https://user-images.githubusercontent.com/58963042/232237899-701ff57f-3535-43b1-b79c-a132bfed134a.gif" width="500" height="300"/>

1. 로그인 버튼 클릭시, 전달받은 아이디와 비밀번호로 UsernamePasswordAuthenticationToken 생성
2. UsernamePasswordAuthenticationToken이 유효하면, 권한을 담은 JWT Token을 생성하여 전달  
3. JWT Token안에 있는 권한정보를 통해 권한 획득(회원의 권한에 따라 보여지는 메뉴와 접근가능한 페이지가 다름)
4. 로그인 이후의 모든 API 호출시, Axios Interceptor를 활용하여 모든 요청의 인증 관련 헤더에 전달받은 JWT 토큰을 추가
5. 직접 구현한 JWT 관련 Security Filter를 활용하여 해당 요청에 담긴 토큰이 유효한지 검사

## 광고관리
<img src="https://user-images.githubusercontent.com/58963042/232238494-635d03f0-5c9e-4754-bd44-7904c6d6ab90.gif" width="500" height="300"/>

## 키워드 및 광고검수, 대량관리, 광고현황
<img src="https://user-images.githubusercontent.com/58963042/232238986-42113238-ab2b-4f1d-a2ab-df8d16e37d24.gif" width="500" height="300"/>

## 일괄 처리 작업

https://user-images.githubusercontent.com/107471786/230723397-25c4e045-e32b-4e28-ab3c-c707972e9183.mov

1. 처리할 여러 작업 등록  
(날짜,직접 광고 ID,노출수,클릭수,평균 노출 순위,평균 클릭 비용,광고비 형태의 CSV 파일 업로드)
2. Quartz 스케줄러를 활용하여, 처리할 작업이 존재하면 Batch Job 실행
3. 읽은 CSV 파일이 유효하면, 작업 상태 값을 완료로 설정하고, 해당 작업의 결과를 저장
4. 읽은 CSV 파일이 유효하지 않으면, 작업 상태 값을 에러로 설정하고, 해당 작업의 결과를 저장하지 않음


