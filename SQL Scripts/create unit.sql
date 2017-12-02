use bellevue_logger;

drop table if exists unit;

create table unit(
	unitNo int auto_increment, lotArea int, categoryID int, phaseNo int,
    primary key (unitNo),
    foreign key (categoryID) references category(categoryID)
)