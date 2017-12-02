use bellevue_logger;

drop table if exists fees;

create table fees(
	feeID int auto_increment, feeName varchar(45) not null, feeTypeID int not null, feePrice double not null,
    primary key (feeID),
    foreign key (feeTypeID) references feetype(feeTypeID)
)