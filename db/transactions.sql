create table cars (
	id serial primary key,
	name text,
	speed integer,
	price integer
);

insert into cars (name, speed, price) 
	values 
	('bmw', 240, 100000),
	('skoda', 210, 60000);
	
begin transaction;

insert into cars (name, speed, price) values ('Reno', 160, 30000);

select * from cars;

savepoint reno_added;

update cars set price = 70000 where name = 'skoda';

select * from cars;

rollback to reno_added;

select * from cars;

commit;