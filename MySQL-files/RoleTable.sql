show databases;
create database demo;
 
 use digitalbook;
 
 create table TBL_ROLE (
	 RoleId int not null auto_increment unique,
     Role_Code int not null unique,
     Is_Active int,
    PRIMARY KEY(RoleId)
 );
 
select * from TBL_ROLE;
describe TBL_ROLE;

ALTER TABLE TBL_ROLE RENAME TO roles;
describe roles;
select* from roles;

delete from roles where id=102;