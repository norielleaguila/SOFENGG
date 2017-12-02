use bellevue_logger;

drop table if exists accountType;

create table accountType (
	typeID int auto_increment, typeName varchar(45),
    primary key(typeID)
)