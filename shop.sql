create table shop_user(
    id varchar2(50) not null,
    password varchar2(50) not null,
    name varchar2(50) not null,
    email varchar2(50) not null,
    address_1 varchar2(100) not null,
    address_2 varchar2(100) not null,
    regdate date default sysdate,
    authority varchar2(50) not null
    )