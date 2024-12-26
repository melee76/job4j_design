create table type(
	id serial primary key,
	name text
);
create table cars(
	id serial primary key,
	title text,
	type_id int references type(id)
);
insert into type (name) values ('truck');
insert into type (name) values ('passenger_car');
insert into cars(title, type_id) values ('kamaz', 1);
insert into cars(title, type_id) values ('mercedes', 2);
 
select cr.title, cr.type_id, tp.name
from  cars cr join type as tp on cr.type_id = tp.id;
select cr.title as Вид, cr.type_id as Номер, tp.name as Имя
from  cars cr join type as tp on cr.type_id = tp.id;
select cr.title Вид, cr.type_id Номер, tp.name Имя
from  cars cr join type as tp on cr.type_id = tp.id;