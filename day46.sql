create table tbl_board10(
bno number(38) primary key,
bname varchar2(50) not null,
btitle varchar2(200) not null,
bcount varchar2(4000) not null,
bhit number(38) default 0,
bdate date
);

select * from tbl_board10 order by bno desc;

alter table tbl_board10 rename column bcount to bcont;

create sequence bno_seq10 
start with 1
increment by 1
nocache;

select bno_seq10.nextval from dual;