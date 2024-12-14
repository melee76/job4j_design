create table membership_card_number(
	id serial primary key,
	uniquenumber varchar(255)
);

create table readers(
	id serial primary key,
	name varchar(255),
	membership_card_number_id int references membership_card_number(id) unique
);