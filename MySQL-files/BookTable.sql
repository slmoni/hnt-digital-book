use digitalbook;

create table TBL_BOOK (
	BookId int primary key not null auto_increment unique,
    Title varchar(500),
    BookCode varchar(50),
    AuthorId varchar(50),
    Price decimal(10,2),
    Category varchar(50),
    Publisher varchar(50),
    IsActive bit,
    CreatedBy int,
    constraint fk_user foreign key(CreatedBy) references TBL_USER(UserId)
);

describe TBL_BOOK;