use bellevue_logger;

drop table if exists billingInfo;

create table billingInfo(
	unitNo int not null, billedTo varchar(100) not null, tct varchar(100),
    primary key (unitNo),
    foreign key (unitNo) references unit(unitNo)
)