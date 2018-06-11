create table prisoner(
   prinum varchar2(20) primary key,
   name varchar2(20) not null,      
   crime varchar2(20) not null,
   type varchar2(20) not null,
   penalty number not null,
   score number not null,
   work number not null,
   ill number(1) not null,
   punish number not null
);

drop table prisoner;

-- 유져생성 및 권한부여
-- cmd - sqlplus - 관리자계정접속 - create user username identified by password;
-- grant 주고싶은권한 to 권한을줄아이디;