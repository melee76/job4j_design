create table car_bodies (
	id serial primary key,
	name text
);

create table car_engines (
	id serial primary key,
	name text
);

create table car_transmissions (
	id serial primary key,
	name text
);

create table cars (
	id serial primary key,
	name text,
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values ('sedan'), ('hatchback'), ('pick-up');
insert into car_engines(name) values ('1.6'), ('1.8'), ('2.0');
insert into car_transmissions(name) values ('mech'), ('auto'), ('cvt');
insert into cars(name, body_id, engine_id, transmission_id)
	values
	('audi', 1, 1, 1),
	('audi', 2, 2, null),
	('bmw', 1, 1, 2),
	('bmw', null, 2, 1),
	('bmw', 2, null, 1);

select c.name as car_name, cb.name as body_name, ce.name as engine_name, ct.name as transmission_name
from cars as c
left join car_bodies cb on c.body_id = cb.id 
left join car_engines ce on c.engine_id = ce.id 
left join car_transmissions ct on c.transmission_id = ct.id;

select cb.*
from car_bodies cb
left join cars c on cb.id = c.body_id
where c.id is null;

select ce.*
from car_engines ce
left join cars c on ce.id = c.engine_id 
where c.engine_id is null;

select ct.*
from car_transmissions ct
left join cars c on ct.id = c.transmission_id
where c.transmission_id is null;