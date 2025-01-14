CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

insert into customers (first_name, last_name, age, country) 
	values
	('Alex', 'Bond', 30, 'USA'),
	('Inna', 'Guff', 27, 'UK'),
	('Nick', 'Aver', 50, 'USA');

SELECT * FROM customers 
WHERE age = (SELECT MIN(age) FROM customers);

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into orders (amount, customer_id) 
	values
	(10, 1),
	(15, 1),
	(20, 2),
	(5, 2);

select * from customers
where customers.id not in (select customer_id from orders);