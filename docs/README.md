#제네시스랩 백엔드 개발자 직무 과제
- 수행기간 : 2022-10-14 ~
##사용기술 

---
- Java 11
- Spring Boot
  - Spring Security
- JPA
- MySQL(개발시 H2사용)
##개발내용

---
### DB 설계
- 사용자 정보
    - ID(PK)
    - 이메일(Unique Key)
    - 이름
    - 전화번호
    - 패스워드
    - 권한(user, admin 2가지)
    

### 기능 구현
- 유저관리
    - 회원 가입
    - 회원 정보 수정
    - 회원 탈퇴
    
    
- 유저 로그인/로그아웃
    - 토큰 방식으로 구현
        - access token, refresh token 구현
        - OAuth2 + Spring Security 사용

    
- 비디오 파일 업로드
    - 권한 : user
    - 로컬 디스크에 저장
    - 최대 100MB 까지 허용
    

- 비디오 파일 재생
    - 권한 : 비디오 소유자, admin
    - 업로드 된 비디오 파일에 대해 스트림 방식으로 요청하여, 릴레이 하는 방식으로 Progressive Download(206 Partial Download) 구현
    - 재생 검증 : html video tag로 간단히 작성하여 플레이 되는지 확인 (디자인 불필요)
    
