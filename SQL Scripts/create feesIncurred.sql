use bellevue_logger;

drop table if exists feesIncurred;

create table feesIncurred(
	unitNo int not null, feeID int not null, dateIncurred Date not null, incurCount int not null,total double not null, payment double not null,
    primary key (unitNo, feeID, dateIncurred),
    foreign key (unitNo) references unit(unitNo),
    foreign key (feeID) references fees(feeID)
)