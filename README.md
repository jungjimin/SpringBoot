# 스프링부트 프로젝트 
> 이 프로젝트는 스프링부트를 이용하여 쿠폰발급 서비스를 구현했습니다.  

## 문제해결 전략
> 

### 1. 요구사항 분석
- 이메일을 입력받아 쿠폰 발급
- 쿠폰번호는 [0-9a-zA-Z]으로 구성 -> 쿠폰번호에 이메일 아이디 활용 가능.
- 중복된 이메일 입력에 따른 쿠폰 발행은 불가 -> 쿠폰 발급 시, 존재하는 이메일인지 체크 필요
- 쿠폰번호 생성은 라이브러리 사용없이 직접 구현 -> 등록일시, 시퀀스 사용으로 유니크하게 구현
### 2. 서버 REST API 구현
- 크롬 확장 앱 'Postman' 을 활용하여 통신 데이터 체크
### 3. 클라이언트 구현
- SPA (Single Page Application)로 개발 -> Jquery, Ajax를 활용하여 비동기 화면으로 구현
### 4. 클라이언트와 서버 통신 확인

## 프로토콜 정의서
> 서버와 클라이언트는 Json Object 통신한다.

### 1. 쿠폰발행 요청
* 요청 URL : /CouponIssue
* 요청 변수 : String email
* 요청 방식 : POST

 ```
{
   "res_cd" : "0000" ,
   "res_msg" : "쿠폰이 발급되었습니다."
}
```

### 2. 발급된 쿠폰정보 리스트 조회
* 요청 URL : /CouponList
* 요청 변수 : int pageNo , int rowCount
* 요청 방식 : POST

 ```
{
    "list": [
        {
            "regdate": "20180328013104",
            "tot_page": 4,
            "coupon_no": "jungj01310400026",
            "id": 4,
            "email": "springboot@gmail.com"
        },
        {
            "regdate": "20180328013115",
            "tot_page": 4,
            "coupon_no": "2344501311500027",
            "id": 5,
            "email": "234451@gmail.com"
        }
    ]
}
```
## 웹 화면

<div>
<img width="400" src="https://user-images.githubusercontent.com/12581525/38097311-03c2c4ac-33b0-11e8-9ecd-b87622d2c8b2.png">
<img width="400" src="https://user-images.githubusercontent.com/12581525/38097317-04b4a51a-33b0-11e8-8c3a-4ced5df8a7d6.png">
</div>


