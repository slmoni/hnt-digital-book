use digitalbook;

create table TBL_USER(
	UserId int not null unique auto_increment primary key,
    UName varchar(20),
    password varchar(500),
    EmailId varchar(50),
    PhoneNo varchar(50),
    RoleId int,
    IsActive int,
    constraint fk_role foreign key(RoleId) references TBL_ROLE(RoleId)
);

ALTER TABLE TBL_USER rename column IsActive to isActive;

ALTER TABLE TBL_USER DROP COLUMN roleId;
ALTER TABLE TBL_USER DROP FOREIGN KEY fk_role;


describe TBL_USER;

ALTER TABLE TBL_USER RENAME TO User;

describe User;

ALTER TABLE User ADD COLUMN RoleId varchar(500);
ALTER TABLE User MODIFY RoleId int;

insert into User(userId,uname,password,emailId) values(010,'Monisha','Moni@1122','moni@gmail.com');

ALTER TABLE User MODIFY userId int unique auto_increment;

SHOW CREATE TABLE user_roles;

select* from information_schema.tables where TABLE_NAME=User;

desc information_schema.tables ;

select * from User;
desc users;
ALTER TABLE User ADD FOREIGN KEY (RoleId) REFERENCES roles(id);