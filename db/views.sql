create table ingredients(
	id serial primary key,
	name text
);
create table dishes(
	id serial primary key,
	name text
);
create table dishes_ingredients(
	id serial primary key,
	dish_id int references dishes(id),
    ingredient_id int references ingredients(id),
    unique (dish_id, ingredient_id)
);
create table menu(
	id serial primary key,
	name text
);
create table menu_dishes(
	id serial primary key,
	menu_id int references menu(id),
	dish_id int references dishes(id),
	unique (menu_id, dish_id)
);
create table restaraunts(
	id serial primary key,
	name text
);
create table restaraunts_menu (
	id serial primary key,
	restaraunts_id int references restaraunts(id),
	menu_id int references menu(id),
	unique (restaraunts_id, menu_id)
);

insert into ingredients(name) 
	values 
	('chicken'), 
	('fish'), 
	('meat'), 
	('vegetables'), 
	('species');
insert into dishes(name) 
	values
	('Chicken with species'),
	('Chicken'),
	('Fish with species'),
	('Fish'),
	('Meat with vegetables'),
	('Meat with species'),
	('Meat'),
	('Vegetables');
insert into dishes_ingredients(dish_id, ingredient_id) 
	values
	(1, 1), (1, 5),
	(2, 1),
	(3, 2), (3, 5),
	(4, 2),
	(5, 3), (5, 4),
	(6, 3), (6, 5),
	(7, 3),
	(8, 4);
insert into menu (name) values ('Meat'), ('Vegan'), ('Chicken'), ('Fish');
insert into menu_dishes(menu_id, dish_id)
	values 
	(1, 5), (1, 6), (1, 7),
	(2, 8),
	(3, 1), (3, 2),
	(4, 3), (4, 4);
insert into restaraunts(name) values ('Beef'), ('KFC'), ('FishRest');
insert into restaraunts_menu(restaraunts_id, menu_id)
	values 
	(1, 1), (1, 2),
	(2, 2), (2, 3),
	(3, 2), (3, 4);
select
    r.name as restaraunt_name,
    m.name as menu_name,
    count(distinct md.dish_id) as number_of_dishes,
    string_agg(i.name, ', ') as ingredients
from restaraunts r
join restaraunts_menu rm on r.id = rm.restaraunts_id
join menu m on rm.menu_id = m.id
join menu_dishes md on m.id = md.menu_id
join dishes_ingredients di on md.dish_id = di.dish_id
join ingredients i on di.ingredient_id = i.id
group by r.name, m.name
order by r.name, m.name;
create viev restaraunt_menu_ingredients as
select
    r.name as restaraunt_name,
    m.name as menu_name,
    count(distinct md.dish_id) as number_of_dishes,
    string_agg(i.name, ', ') as ingredients
from restaraunts r
join restaraunts_menu rm on r.id = rm.restaraunts_id
join menu m on rm.menu_id = m.id
join menu_dishes md on m.id = md.menu_id
join dishes_ingredients di on md.dish_id = di.dish_id
join ingredients i on di.ingredient_id = i.id
group by r.name, m.name
order by r.name, m.name;
select * from restaraunt_menu_ingredients order by restaraunt_name, menu_name;