create table author(
	id serial primary key,
	name varchar(255)
);

create table books(
	id serial primary key,
	title varchar(255),
	author_id int references author(id)
);

insert into author(name) values('Alex');
insert into books(title, author_id) VALUES('Fairy_tail', 1);

select * from books;

select * from author where id in (select author_id from books);