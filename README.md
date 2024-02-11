# 📖 매장 테이블 예약 서비스

## 🔧 기술 스택
- Language : `java`
- Build : `gradle`
- DataBase : `MySQL`
- JDK : `JDK 11`
- Framework : `SpringBoot`
- library : `Spring Data JPA`, `Lombok`, `Jwt`

## 📆 개발 기간
- 2024.01.22 ~ 2024.02.12

## 1️⃣ ERD
![image](https://github.com/KongJihoon/table-reservation/assets/138794635/9f336fd4-f2dc-4741-bc8b-d5d0ab014e52)

## 2️⃣ 기능 사항
- 매장의 점장은 예약 서비스 앱에 상점을 등록한다.(매장 명, 상점위치, 상점 설명)
- 매장을 등록하기 위해서는 파트너 회원 가입이 되어야 한다.(따로, 승인 조건은 없으며 가입 후 바로 이용 가능)
- 매장 이용자는 앱을 통해서 매장을 검색하고 상세 정보를 확인한다.
- 매장의 상세 정보를 보고, 예약을 진행한다.(예약을 진행하기 위해서는 회원 가입이 필수적으로 이루어 져야 한다.)
- 서비스를 통해서 예약한 이후에, 예약 10분전에 도착하여 키오스크를 통해서 방문 확인을 진행한다.
- 예약 및 사용 이후에 리뷰를 작성할 수 있다.
- 리뷰의 경우, 수정은 리뷰 작성자만, 삭제 권한은 리뷰를 작성한 사람과 매장의 관리자(점장등)만 삭제할 수 있습니다.

## 3️⃣ API 명세서
### ✔️ 고객/매니저 인증 API

#### (1) 고객 회원가입
<details>
  <summary> 경로/ 요청/ 결과</summary>

  경로: [POST] http://localhost:8080/api/signup/customer

  요청
  ~~~
    {
      "email": "customer@gmail.com",
      "password": "1234",
      "phone": "010-1111-2222",
      "name": "고객"
    }
  ~~~

  결과
  ~~~
    {
      "name": "고객",
      "email": "customer@gmail.com",
      "password": "$2a$10$048ph91/XtE99II.447ul.8RYBCrggsSDa1GxF3xNh8F3zGpiqMFW",
      "phone": "010-1111-2222"
    }
  ~~~
</details>

#### (2) 고객 로그인
<details>
  <summary> 경로/ 요청/ 결과</summary>

  경로: [POST] http://localhost:8080/api/signin/customer

  요청
  ~~~
    {
      "email": "customer@gmail.com",
      "password": "1234"
    }
  ~~~


  결과
  - 성공
    ~~~
      {
        eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjdXN0b21lckBnbWFpbC5jb20iLCJqdGkiOiJtVGxzc2xVUUNsSnQyUThSUmNoNWZVcUVPMS9XWmxacWlYbnpDb0d2RVJrPSIsInJvbGVzIjoiQ1VTVE9NRVIiLCJpYXQiOjE3MDc2NjM3NzMsImV4cCI6MTcwNzY2NzM3M30.NV83_v0TXNA72xB-bQuCiy6lVToBN_vZEEPuYLULDpA
      }
    ~~~
  
  - 실패
    ~~~
      {
        "errorCode": "PASSWORD_NOT_MATCH",
        "errorMessage": "패스워드가 일치하지 않습니다."
      }
    ~~~
</details>

#### (3) 매니저 회원가입
<details>
  <summary> 경로/ 요청/ 결과</summary>

  경로: [POST] http://localhost:8080/api/signup/manager

  요청
  ~~~
    {
      "email": "manager@gmail.com",
      "password": "12345",
      "phone": "010-1111-2222",
      "name": "매니저"
    }
  ~~~

  결과
  ~~~
    {
      "name": 매니저,
      "email": "manager@gmail.com",
      "password": "$2a$10$1Beuybs/82RTUUep4J4qWeIz9vbYN7TUSy1BfZSKYOJSB.JOxC5CW",
      "phone": "010-1111-2222"
    }  
  ~~~
</details>

#### (4) 매니저 로그인
<details>
  <summary> 경로/ 요청/ 결과 </summary>

  경로: [POST] http://localhost:8080/api/signin/manager

  요청
  ~~~
    {
      "email": "manager@gmail.com",
      "password": "12345"
    }
  ~~~


  결과
  - 성공
    ~~~
      {
        eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYW5hZ2VyQGdtYWlsLmNvbSIsImp0aSI6IkZIeEdFck5rY3M2NjFEL3pFalVVOXc3SHd1alIzVDdQOG5RaDRuZld2NEk9Iiwicm9sZXMiOiJQQVJUTkVSIiwiaWF0IjoxNzA3NjY0MDEyLCJleHAiOjE3MDc2Njc2MTJ9.BreDbatpq6T6xWML8UxYRQH1ofB6xfs_-bIisZ6YrMk
      }
    ~~~
  
  - 실패
    ~~~
      {
        "errorCode": "PASSWORD_NOT_MATCH",
        "errorMessage": "패스워드가 일치하지 않습니다."
      }
    ~~~
</details>

### ✔️ 매장 API
#### (1) 매장 등록
<details>
  <summary> 경로/ 요청/ 결과 </summary>

  요청: [POST] http://localhost:8080/api/shop/partner/create
  ~~~
    {
      "location": "인천시 계양구",
      "managerId": 1,
      "shopName": "shop2",
      "phone": "032-1234-5670"
    }
  ~~~

  결과
  - 성공
    ~~~
      {
        "shopName": "shop2",
        "location": "인천시 계양구",
        "phone": "032-1234-5670"
      }
    ~~~
  
  - 실패
    ~~~
      {
        "errorCode": "MANAGER_NOT_FOUND",
        "errorMessage": "매니저를 찾을 수 없습니다."
      }
    ~~~
</details>

#### (2) 매장 수정
<details>
  <summary> 경로/ 요청/ 결과 </summary>

  경로: [PUT] http://localhost:8080/api/shop/partner/update/{shopId}

  요청
  ~~~
    {
      "managerId" : 1,
      "shopName": "shop1",
      "location": "서울시"
    }
  ~~~

  결과
  - 성공
  ~~~
     {
      "shopName": "shop1",
      "location": "서울시"
     }
  ~~~

  - 실패
    ~~~
      {
        "errorCode": "SHOP_NOT_FOUND",
        "errorMessage": "매장을 찾을 수 없습니다."
      }
    ~~~
</details>

#### (3) 매장 삭제
<details>
  <summary> 경로/ 요청/ 결과 </summary>

  경로: [DELETE] http://localhost:8080/api/shop/partner/delete?id=?&shop=?

  결과
  - 성공
    ~~~
      {
        매장 삭제 완료
      }
    ~~~

  - 실패
    ~~~
      {
        "errorCode": "SHOP_NOT_FOUND",
        "errorMessage": "매장을 찾을 수 없습니다."
      }
    ~~~
</details>

#### (4) 매장 상세정보 조회
<details>
  <summary> 경로/ 요청/ 결과 </summary>

  경로: [GET] http://localhost:8080/api/shop/detail/{shopName}

  결과
  ~~~
    {
      "manager": {
        "createdAt": "2024-02-12T00:05:23.146492",
        "updatedAt": "2024-02-12T00:05:23.146492",
        "id": 1,
        "name": "매니저",
        "userType": "PARTNER",
        "email": "manager@gmail.com",
        "password": "$2a$10$1Beuybs/82RTUUep4J4qWeIz9vbYN7TUSy1BfZSKYOJSB.JOxC5CW",
        "phone": "010-1111-2222",
        "enabled": false,
        "accountNonLocked": false,
        "accountNonExpired": false,
        "credentialsNonExpired": false,
        "authorities": [
          {
            "authority": "ROLE_PARTNER"
          }
        ],
        "username": null
      },
      "shopName": "shop",
      "location": "인천시 계양구",
      "phone": "032-1234-5670"
    }
  ~~~
</details>

### ✔️ 예약 API
#### (1) 예약 등록
<details>
  <summary> 경로/ 요청/ 결과 </summary>

  경로: [POST] http://localhost:8080/api/reservation/create

  요청
  ~~~
    {
      "userId": 1,
      "shopId": 2,
      "reservationDate": "2024-02-19",
      "reservationTime": "16:00:00"
    }
  ~~~

  결과
  - 성공
    ~~~
    {
      "userName": "고객",
      "userPhone": "010-1111-2222",
      "shopName": "shop",
      "reservationStatus": "STANDBY",
      "reservationDate": "2024-02-19",
      "reservationTime": "16:00:00"
    }
    ~~~

  - 실패
    ~~~
    {
      "errorCode": "SHOP_NOT_FOUND",
      "errorMessage": "매장을 찾을 수 없습니다."
    }
    ~~~
</details>

#### (2) 예약 삭제
<details>
  <summary> 경로/ 요청/ 결과 </summary>

  경로: [DELETE] http://localhost:8080/api/reservation/cancel?reservationId=?

  결과
  ~~~
    {
      "reservationId": 1,
      "userName": "고객",
      "userPhone": "010-1111-2222",
      "shopName": "shop",
      "reservationStatus": "CANCELED",
      "arrivalStatus": "READY",
      "reservationDate": "2024-02-19",
      "reservationTime": "16:00:00"
    }
  ~~~
</details>

#### (3) 예약 조회
<details>
  <summary> 경로/ 요청/ 결과 </summary>

  경로: [GET] http://localhost:8080/api/reservation/partner/reservation-list/{id}

  결과
  ~~~
    {
      "reservationList": [
        {
          "reservationId": 1,
          "userName": "고객",
          "userPhone": "010-1111-2222",
          "shopName": "shop",
          "reservationStatus": "CANCELED",
          "arrivalStatus": "READY",
          "reservationDate": "2024-02-19",
          "reservationTime": "16:00:00"
        },
        {
          "reservationId": 2,
          "userName": "고객",
          "userPhone": "010-1111-2222",
          "shopName": "shop2",
          "reservationStatus": "STANDBY",
          "arrivalStatus": "READY",
          "reservationDate": "2024-02-19",
          "reservationTime": "15:00:00"
        }
      ]
    }
  ~~~
</details>

#### (4) 예약 승인 여부 변경
<details>
  <summary> 경로/ 요청/ 결과 </summary>

  경로: [PUT] http://localhost:8080/api/reservation/partner/approval/{reservationId}

  요청
  ~~~
    {
      "reservationStatus": "APPROVAL"
    }
  ~~~

  결과
  ~~~
    {
      "reservationId": 2,
      "userName": "고객",
      "shopName": "shop2",
      "reservationStatus": "APPROVAL",
      "reservationDate": "2024-02-19",
      "reservationTime": "15:00:00"
    }
  ~~~
</details>

#### (5) 예약 도착 여부 변경
<details>
  <summary> 경로/ 요청/ 결과 </summary>

  경로: [POST] http://localhost:8080/api/reservation/kiosk/{reservationId}

  요청
  ~~~
    {
      "userName": "고객",
      "userPhone": "010-1111-2222",
      "arriveTime": "2024-02-19T14:50:00.000Z"
    }
  ~~~

  결과
  - 성공
    ~~~
    {
      "reservationId": 2,
      "userName": "고객",
      "shopName": "shop2",
      "reservationStatus": "USE_COMPLETED",
      "arrivalStatus": "ARRIVED"
    }
    ~~~

  - 실패
    ~~~
      {
        "errorCode": "RESERVATION_STATUS_ERROR",
        "errorMessage": "예약 상태 코드에 문제가 있습니다."
      }
    ~~~
</details>

### ✔️ 리뷰 API
#### (1) 리뷰 작성
<details>
  <summary> 경로/ 요청/ 결과 </summary>

  경로: [POST] http://localhost:8080/api/review/create?customerId=1&shopId=?&reservationId=?

  요청
  ~~~
    {
      "reviewContent": "맛있습니다.",
      "rating": 5.0
    }
  ~~~

  결과
  ~~~
    {
      "reviewId": 1,
      "reviewContent": "맛있습니다.",
      "rating": 5.0,
      "userName": "고객",
      "shopName": "shop2"
    }
  ~~~
</details>

#### (2) 리뷰 수정(고객)
<details>
  <summary> 경로/ 요청/ 결과 </summary>

  경로: [PUT] http://localhost:8080/api/review/update/{id}

  요청
  ~~~
    {
      "reviewContent": "다음에 또 방묺하겠습니다.",
      "rating": 2.5
    }
  ~~~

  결과
  ~~~
    {
      "reviewId": 1,
      "reviewContent": "다음에 또 방묺하겠습니다.",
      "rating": 2.5
    }
  ~~~
</details>

#### (3) 리뷰 삭제
<details>
  <summary> 경로/ 요청/ 결과 </summary>

  경로: [DELETE] http://localhost:8080/api/review/delete/{id}

  결과
  ~~~
    {
      리뷰 삭제 완료
    }
  ~~~
</details>
  
