# 다루다 (다이어트와 루틴을 다루다)
* 운동과 식단 관리를 안 해 본 초보 사용자를 위한 서비스입니다. 
* 매일 운동량과 식단을 기록하여 권장 운동 루틴과 섭취 칼로리를 한눈에 확인할 수 있습니다.    
* [멀티캠퍼스 백엔드 개발자 취업캠프(Java) 2회차](https://event.multicampus.com/backend)의 최종 프로젝트를 위해 제작하였습니다. 

<br/>

## 주요 정보
* <b>주요 기능</b>: 이 [파일](./readme/core_function.pdf) 참고 바랍니다.
* <b>제작 기간</b>: 2022.12.13 ~ 2023.01.26 (6주)
* <b>역할 분배</b>: 이 [파일](./readme/role.png) 참고 바랍니다.

<br/>

## 기술 스택 
* Back-end
   * JAVA 8
   * Spring 4
   * Maven
   * MyBatis
   * Spring Data MongoDB
   * Jackson

<br/>

* Database
   * Oracle
   * MongoDB 

<br/>

* Front-end
   * Chart.js
   * Ajax
   * JSP
   * HTML/CSS/JavaScript

<br/>

* ETC
   * Apache Tomcat 9
   * AWS EC2
   * GitHub

<br/>

## ERD 
<details>
<summary>ERD 이미지</summary>
<img src = "./readme/erd.png">
</details>

* 빨간색: Oracle / 초록색: MongoDB 
* Cardio와 Fitness는 각각 유/무산소 운동을 저장하는 테이블로서, Routine 테이블의 cardioObj/fitnessObj 필드와 관계를 맺습니다.
<details>
<summary>cardioObj 예시</summary>
<img src = "./readme/cardio_obj.PNG">
</details>

<br/>

## 주요 소스 코드
* 회원 가입 시 Member, MemberBio, PersonalRoutine, Goal 테이블에 모두 정상적으로 데이터가 입력되어야 하므로 Transaction을 이 [코드](https://github.com/flyc4/drd/blob/34eab38c68c0bf0e3061cc0413efb39769085150/drd/src/main/java/com/multi/drd/member/MemberServiceImpl.java#L35)에 사용하였습니다. (작성자: 김창희)
* MongoDB에 저장된 기록을 토대로 사용자의 주(week)별 운동 시간을 이 [코드](https://github.com/flyc4/drd/blob/34eab38c68c0bf0e3061cc0413efb39769085150/drd/src/main/java/com/multi/drd/dashboard/DashboardDAOImpl.java#L118)를 통해 계산하였습니다. (작성자: 박수정)

<br/>

## 후기
이 파일 참고 바랍니다. (추후 작업 예정)

## 참고 자료
* 초보자의 운동 루틴: 2015 보건복지부 우수건강도서로 선정된 [헬스의 정석](http://www.hanmunhwa.com/?p=4811)
* 하루 권장 섭취 칼로리: [2015 한국인 영양소 섭취기준 활용 가이드북](https://www.dietitian.or.kr/assets/ver2/bogun_online_2016/html/main_popup/2015%ED%95%9C%EA%B5%AD%EC%9D%B8%EC%98%81%EC%96%91%EC%86%8C.pdf) (22쪽)
* 일주일 권장 섭취 단백질: [Tarnopolsky M. Protein requirements for endurance athletes. Nutrition. 2004 Jul-Aug;20(7-8):662-8. doi: 10.1016/j.nut.2004.04.008. PMID: 15212749.](https://pubmed.ncbi.nlm.nih.gov/15212749/)
