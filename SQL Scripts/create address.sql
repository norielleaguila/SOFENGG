use bellevue_logger;

drop table if exists address;

create table address(
	addressNo int, unitNo int not null, streetNo int not null,
    primary key (addressNo),
    foreign key (unitNo) references unit(unitNo),
    foreign key (streetNo) references street(streetID)
)