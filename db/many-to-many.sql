create table readers(
	id serial primary key,
	name varchar(255)
);

create table books(
	id serial primary key,
	title varchar(255)
);

create table readers_books(
	id serial primary key,
	readers_id int references readers(id),
	books_id int references books(id)
);

insert into readers(name) values('Alex');
insert into readers(name) values('Olga');

insert into books(title) VALUES('Fairy_tail');
insert into books(title) VALUES('Horror');

insert into readers_books(readers_id, books_id) values (1, 1);
insert into readers_books(readers_id, books_id) values (1, 2);
insert into readers_books(readers_id, books_id) values (2, 1);