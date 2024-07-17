create table board(
bno number(5) primary key, -- PK 지정
btitle nvarchar2(30) not null,
bcontent nvarchar2(1000) not null,
bwriter nvarchar2(10) not null,
bdate date not null
);


create sequence board_seq increment by 1 start with 1 nocycle nocache;
-- 시퀀스 생성
drop sequence board_seq; -- 자동번호 생성 제거

drop table board; --기존에 board 테이블 삭제


insert into board (bno, btitle, bcontent, bwriter, bdate)
values (board_seq.nextval, '비가오내요~', '비오는데 등교하시는냐고 고생 하셨습니다.', '김기원', sysdate);
insert into board (bno, btitle, bcontent, bwriter, bdate)
values (board_seq.nextval, '안녕하세요~', '비오는데 등교하시는냐고 고생 하셨습니다.', '김기원', sysdate);
insert into board (bno, btitle, bcontent, bwriter, bdate)
values (board_seq.nextval, '감사합니다.~', '비오는데 등교하시는냐고 고생 하셨습니다.', '김기원', sysdate);
insert into board (bno, btitle, bcontent, bwriter, bdate)
values (board_seq.nextval, '수고하셨내요~', '비오는데 등교하시는냐고 고생 하셨습니다.', '김기원', sysdate);
insert into board (bno, btitle, bcontent, bwriter, bdate)
values (board_seq.nextval, '화이팅하세요~', '비오는데 등교하시는냐고 고생 하셨습니다.', '김기원', sysdate);
insert into board (bno, btitle, bcontent, bwriter, bdate)
values (board_seq.nextval, '방갑습니다.~', '비오는데 등교하시는냐고 고생 하셨습니다.', '김기원', sysdate);
-- 더미데이터


select * from board;


-- member 테이블 용

-- 테이블이면 memberTBL 이런식으로 이름 달아주면 좋음
create table member(
mno number(5) primary key,
mid nvarchar2(10) not null,
mpw nvarchar2(10) not null,
mdate date not null
); -- 시퀀스는 위에거 돌려쓸예정

insert into member (mno, mid, mpw, mdate)
values (board_seq.nextval, '김기원', '1234', sysdate);
insert into member (mno, mid, mpw, mdate)
values (board_seq.nextval, '이현우', '1234', sysdate);
insert into member (mno, mid, mpw, mdate)
values (board_seq.nextval, '김정하', '1234', sysdate);
insert into member (mno, mid, mpw, mdate)
values (board_seq.nextval, '김우혁', '1234', sysdate);
insert into member (mno, mid, mpw, mdate)
values (board_seq.nextval, '김태희', '1234', sysdate);


select * from member;

drop table member;


-- 탈퇴 회원용 테이블
create table delMember(
mdno number(5) not null,
mdid nvarchar2(10) not null,
mdpw nvarchar2(10) not null,
mddate date not null
);

create or replace trigger delMember
after delete on member for each row
begin
insert into delMember values(:old.mno, :old.mid, :old.mpw, sysdate);
end;

select * from delMember;

drop table delMember;