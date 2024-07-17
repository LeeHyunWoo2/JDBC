create tablespace board datafile 'c:\board.dbf' size 20m;
-- DB돌리는 컴퓨터에 파일 만들어짐 (10-3 컴퓨터)

create user boardtest identified by boardtest;
-- id boardtest / pw boardtest

grant connect, resource to boardtest;
-- boardtest 계정에게 접속 권한, 일반조원 권한 부여

alter user boardtest default tablespace board;
-- boardtest 계정에 기본 데이터베이스를 board 로 지정함

alter user boardtest temporary tablespace temp;
-- boardtest 계정에 임시 데이터베이스를 배정 (생략가능. 하면좋음)

-- 이제 할일 -> boardtest 계정으로 접속