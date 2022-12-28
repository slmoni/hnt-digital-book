create database testdb;

create table user_roles;

select * from information_schema.CHECK_CONSTRAINTS where CONSTRAINT_SCHEMA="digitalbook.user_roles";

drop table User;
drop table roles;

alter table User drop foreign key FKh8ciramu9cc9q3qcqiv4ue8a6;

drop tables user_roles;

drop database digitalbook;

create database digitalbook;
use digitalbook;
show tables;
desc books;
insert into roles(name) values ('ROLE_READER');
insert into roles(name) values ('ROLE_AUTHOR');
insert into roles(name) values ('ROLE_GUEST');
select * from roles;
select * from user;

use digitalbook;

show tables;
desc user;
desc roles;