create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('TV', 500);
insert into devices(name, price) values ('Monitor', 300);
insert into devices(name, price) values ('Phone', 200);

insert into people(name) values ('Alex');
insert into people(name) values ('Inna');
insert into people(name) values ('Anna');

insert into devices_people(device_id, people_id) values (1, 1);
insert into devices_people(device_id, people_id) values (1, 2);
insert into devices_people(device_id, people_id) values (2, 3);
insert into devices_people(device_id, people_id) values (2, 2);
insert into devices_people(device_id, people_id) values (3, 1);
insert into devices_people(device_id, people_id) values (3, 2);
insert into devices_people(device_id, people_id) values (3, 3);

select avg(price) from devices;

select p.name, avg(d.price)
from devices_people as dp
join people p on dp.people_id = p.id
join devices d on dp.device_id = d.id
group by p.name;

select p.name, avg(d.price)
from devices_people as dp
join people p on dp.people_id = p.id
join devices d on dp.device_id = d.id
group by p.name
having avg(d.price) > 340;