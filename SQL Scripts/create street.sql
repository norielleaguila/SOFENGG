use bellevue_logger;

drop table if exists street;

create table street(
	streetID int, streetName varchar(45) not null,
    primary key(streetID)
)