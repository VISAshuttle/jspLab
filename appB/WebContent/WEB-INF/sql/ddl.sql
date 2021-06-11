-- root@mysql 계정으로 
create database book_ex default character set utf8;

create user 'zerock'@'localhost' identified by 'zerock';
grant all privileges on book_ex.* to 'zerock'@'localhost';

create user 'zerock'@'%' identified by 'zerock';
grant all privileges on book_ex.* to 'zerock'@'%';


-- zerock 계정으로 
create table tbl_board (
     bno INT NOT NULL AUTO_INCREMENT,
     title VARCHAR(200) NOT NULL,
     content TEXT NULL,
     writer VARCHAR(50) NOT NULL,
     regdate TIMESTAMP NOT NULL DEFAULT now(),
     viewcnt INT DEFAULT 0,
     PRIMARY KEY (bno)
);
insert into tbl_board (title, content, writer)
values('제목입니다','내용입니다','user00');

-- 게시물 등록의 파일 업로드
create table tbl_attach (
	fullName varchar(150) not null,
	bno int not null,
	regdate timestamp default now(),
	primary key (fullName)
);

-- alter table tbl_attach add constraint fk_board_attach 
-- foreign key (bno) references tbl_board (bno);
