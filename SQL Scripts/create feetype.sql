use bellevue_logger;

drop table if exists feetype;

create table feetype(
	feetypeID int auto_increment, feeType varchar(45),
    primary key (feetypeID)
)