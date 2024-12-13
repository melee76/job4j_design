create table cars(
	id serial primary key,
	brand varchar(255),
	model text,
	price money 
);

insert into cars(brand, model, price) values('audi', 'a6', '40000');

select * from cars;

update cars set price = '38000';

select * from cars;

delete from cars;

select * from cars;