use bellevue_logger;

drop table if exists category;

create table category(
	categoryID int auto_increment, minRange int, maxRange int,
    primary key (categoryID)
)