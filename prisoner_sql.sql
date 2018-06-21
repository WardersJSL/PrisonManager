-- 테이블 생성
create table prisoner(
   prinum varchar2(9) primary key,
   name varchar2(20) not null,      
   crime varchar2(20) not null,
   prisoner_type varchar2(20) not null,
   penalty number not null,
   score number not null,
   work number,
   ill number(1) not null,
   punish number not null
);

-- 데이터 삽입
-- 사형수의 형량은 0, 나머지는 전부 1 이상
-- 상벌점, 노동량, 질병유무의 초기값은 0
insert into prisoner values('01가43269', '김살인', '살인', '사형수', 0, 0, 0, 0, 0);
insert into prisoner values('01나23713', '임마약', '마약', '마약사범', 98, 0, 0, 0, 0);
insert into prisoner values('02가17842', '윤절도', '절도', '일반', 71, 0, 0, 0, 0);
insert into prisoner values('02가41010', '고살인', '살인', '사형수', 0, 0, 0, 0, 0);
insert into prisoner values('01가42478', '유영철', '살인', '사형수', 0, 0, 0, 0, 0);
insert into prisoner values('01나25689', '나세힘', '폭행', '요시찰', 19, 0, 0, 0, 0);
insert into prisoner values('01가43852', '사린마', '살인', '사형수', 0, 0, 0, 0, 0);
insert into prisoner values('02가54989', '사기군', '사기', '일반', 5, 0, 0, 0, 0);
insert into prisoner values('02다14732', '왕성해', '강간', '일반', 35, 0, 0, 0, 0);
insert into prisoner values('01나25959', '고린내', '마약', '마약사범', 18, 0, 0, 0, 0);

-- 테이블 삭제
drop table prisoner;

-- 전체 검색
select * from prisoner;

-- 데이터 삭제
delete from prisoner;

-- 유져생성 및 권한부여
-- cmd - sqlplus - 관리자계정접속 - create user username identified by password;
-- grant 주고싶은권한 to 권한을줄아이디;