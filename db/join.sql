create table departments (
	id serial primary key,
	name text
);

create table employees (
	id serial primary key,
	name text,
	department_id int references departments(id)
);

insert into departments(name) values('Technical');
insert into departments(name) values('Financial');
insert into departments(name) values('Useless');
insert into departments(name) values('Correspond');

insert into employees(name, department_id) values('Admin', 1);
insert into employees(name, department_id) values('Programmer', 1);
insert into employees(name, department_id) values('Tester', 1);
insert into employees(name, department_id) values('Economist', 2);
insert into employees(name, department_id) values('Finansist', 2);
insert into employees(name, department_id) values('Secretary', 3);
insert into employees(name, department_id) values('Secretary1', 3);
insert into employees(name, department_id) values('worker', null);
insert into employees(name, department_id) values('worker2', null);

select * from employees e left join departments d on e.department_id = d.id;
select * from departments d right join employees e on e.department_id = d.id;
select * from departments d full join employees e on e.department_id = d.id;
select * from departments d cross join employees e;

select * from departments d left join employees e on e.department_id = d.id 
where department_id is null;

SELECT d.id, d.name, e.id, e.name, e.department_id
FROM departments d
LEFT JOIN employees e ON d.id = e.department_id;

select d.id, d.name, e.id, e.name, e.department_id
from employees e
right JOIN departments d on d.id = e.department_id;

create table teens (
	id serial primary key,
	name text,
	gender text
);

insert into teens (name, gender) values ('Alex', 'male');
insert into teens (name, gender) values ('Inna', 'female');
insert into teens (name, gender) values ('Anton', 'male');
insert into teens (name, gender) values ('Zina', 'female');
insert into teens (name, gender) values ('Ihor', 'male');
insert into teens (name, gender) values ('Vika', 'female');

select t1.name as a, t2.name as b
from teens t1 cross join teens t2
where t1.gender < t2.gender;