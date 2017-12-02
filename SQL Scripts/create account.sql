use bellevue_logger;

drop table if exists account;

create table account(
	accountID int auto_increment, typeID int, typeName varchar(45), username varchar(45), password varchar(45),
    primary key (accountID),
    foreign key (typeID) references accounttype(typeID)
)