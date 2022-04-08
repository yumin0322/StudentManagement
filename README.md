# StudentManagement(자바 프로젝트, DB와 연동)
데이터베이스 작업
create database PhoneBookDB;
use phoneBookdb; 
변수(테이블 생성)
create table studentprojectTBL(
    name varchar(10) not null,      // 이름
    gender varchar(4) not null,     //성별
    birthday varchar(10) not null,  //생일
    id int not null,                //학번
    korean int not null,            //국어점수
    math int not null,              //수학점수
    english int not null,           //영어점수
    total int not null,             //총점
    avr double not null,            //평균
    grade char not null,            //등급
    primary key(id)                  

);
insert into studentprojectdb.studentprojecttbl values('김준희','남자','1997-03-20',2022,100,100,100);    //삽입

select * from studentprojectdb.studentprojecttbl where id like 2022;                                    //조회

delete from studentprojectdb.studentprojecttbl where id like 1011;                                      //삭제

update studentprojectdb.studentprojecttbl set korean=80, math=80, english=80 where id=1564;             //수정

select * from studentprojectdb.studentprojecttbl order by total asc;                                    //오름차순(정렬)

select * from studentprojectdb.studentprojecttbl order by total desc;                                   //내림차순


자바 프로그램을 통해 데이터베이스를 연동하여 작업, 코드 레코드 불러오기, 삽입, 검색, 삭제, 수정, 정렬 등을 수행하도록 했다.

StudentModel - 변수,생성자,getset설정, 오버라이딩(hash,equals,comparable)
StudentMain - 실행
DBUtility - 데이터 베이스 연동
DBController - 데이터베이스와 이클립스가 연동하여 작동하도록 구성

<실행결과>


![1](https://user-images.githubusercontent.com/100817654/162378958-7dd72b09-8054-4101-9237-9276138b8f47.png)
![2](https://user-images.githubusercontent.com/100817654/162378965-5f09c349-9594-4bad-ae13-132616608eff.png)
![3](https://user-images.githubusercontent.com/100817654/162378978-30de8dd1-1b81-45e9-9f6a-11fc27a58083.png)
![4](https://user-images.githubusercontent.com/100817654/162378985-61ba07d6-2a95-4de7-a439-095dcf7e22ce.png)
![5](https://user-images.githubusercontent.com/100817654/162378996-f10e42fc-0565-4b04-8512-4f6da779c2ab.png)
![6](https://user-images.githubusercontent.com/100817654/162379002-713e6c24-6781-4056-a27b-78d81972c263.png)
![7](https://user-images.githubusercontent.com/100817654/162379012-0a0063dc-31a8-4547-97b6-28146667f49e.png)
![8](https://user-images.githubusercontent.com/100817654/162379024-ead186b3-1ef6-4d1d-995e-8498dfd3e199.png)
![9](https://user-images.githubusercontent.com/100817654/162379030-806a1fee-31ff-4130-8e99-4e1ec78ba5d3.png)
![10](https://user-images.githubusercontent.com/100817654/162379039-33a8a3c0-3280-4568-80f8-9dd94f39daa0.png)

