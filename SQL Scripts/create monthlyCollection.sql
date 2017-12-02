use bellevue_logger;

drop table if exists monthlycollection;

create table monthlycollection(
	transactionID int auto_increment, unitNo int not null, 
    dateBilled Date not null, dateDue Date not null, datePaid Date not null, 
    monthlyDue double not null, monthlyPaid double, monthlyOverdue double,
    primary key (transactionID),
    foreign key (unitNo) references unit(unitNo)
)