create table cars(
	id serial primary key,
	name text,
	speed integer,
	price integer
);

insert into cars (name, speed, price) values ('bmw', 240, 100000);
insert into cars (name, speed, price) values ('skoda', 200, 60000);

begin transaction isolation level serializable;

update cars set price = 70000 where name ='skoda';

update cars set price = 50000 where name ='skoda';