create table type (
	id serial primary key,
	name text
);

create table product (
	id serial primary key,
	name text,
	type_id int references type(id),
	expired_date date,
	price float
);

insert into type(name) values('Сыр');
insert into type(name) values('молоко');

insert into product(name, type_id, expired_date, price) values ('Плавленный сыр', 1, '2023.09.28', 300);
insert into product(name, type_id, expired_date, price) values ('Шоколадный сыр', 1, '2023.09.01', 500);
insert into product(name, type_id, expired_date, price) values ('Молоко мороженое', 2, '2023.12.01', 100);
insert into product(name, type_id, expired_date, price) values ('Молоко 1.0', 2, '2023.12.01', 100);
insert into product(name, type_id, expired_date, price) values ('Молоко 2.5', 2, '2023.08.01', 120);
insert into product(name, type_id, expired_date, price) values ('Молоко 3.0', 2, '2023.11.01', 140);

select * from product where type_id = 1;

select * from product where name like '%мороженое%';

select * from product where expired_date < current_date;

select name, price from product
where price = (select max(price) from product);

select t.name, count(pd.price) 
from product as pd 
join type t on pd.type_id = t.id 
group by t.name;

select * from product 
where type_id = 1 or type_id = 2;

select t.name, count(pd.price) 
from product as pd 
join type t on pd.type_id = t.id 
group by t.name 
having count(pd.price) < 3;

select t.name, pd.name 
from product as pd 
join type t on pd.type_id = t.id;