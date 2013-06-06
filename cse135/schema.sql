DROP TABLE users CASCADE;
DROP TABLE categories CASCADE;
DROP TABLE products CASCADE;
DROP TABLE orders CASCADE;
DROP TABLE ordered CASCADE;

create table users (
	id serial primary key,
	name text unique not null,
	role text not null,
	age smallint,
	state text
);

create table categories (
	id serial primary key,
	name text unique not null,
	description text
);

create table products (
	id serial primary key,
	name text not null,
	sku text unique not null,
	price decimal(10,4) not null,
	category integer references categories(id)
);

create table orders (
	id serial primary key,
	user_pk integer references users(id)
);
	

create table ordered (
	id serial primary key,
	product integer references products(id),
	order_pk integer references orders(id)
);
