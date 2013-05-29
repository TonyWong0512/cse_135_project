DROP TABLE users CASCADE;
DROP TABLE categories CASCADE;
DROP TABLE products CASCADE;
DROP TABLE sales CASCADE;

create table users (
	id serial primary key,
	name text unique not null,
	role text,
	age smallint,
	state character(2)
);

create table categories (
	id serial primary key,
	name text unique not null,
	description text
);

create table products (
	sku serial primary key,
	name text unique not null,
	price decimal(10,4) not null,
	category_id integer references categories(id)
);

create table sales (
	id serial primary key, 
	product_id integer references products (sku) not null, 
	customer_id integer references users (id) not null, 
	quarter integer not null,
	quantity integer not null,
	total_cost integer not null,
);
