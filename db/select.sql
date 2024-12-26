create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);
insert into fauna (name, avg_age, discovery_date) values ('goldfish', 500, '1950-01-01');
insert into fauna (name, avg_age, discovery_date) values ('brownfish', 10000, '1600-01-01');
insert into fauna (name, avg_age, discovery_date) values ('shark', 500000, null);
insert into fauna (name, avg_age, discovery_date) values ('Elephant', 250000, '1300-01-01');
insert into fauna (name, avg_age, discovery_date) values ('squarle', 5000, '10-01-01');
insert into fauna (name, avg_age, discovery_date) values ('turtle', 600000, '720-01-01');
select * from fauna where name like '%fish%';
select * from fauna where avg_age > 10000 and avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < 1950;