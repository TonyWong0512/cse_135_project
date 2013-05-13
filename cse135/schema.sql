create table users (
	ID serial primary key,
	name text unique not null,
	role text,
	age short,
	state text
);

create table categories (
	ID serial primary key,
	name text unique not null,
	description text
);

create table products (
	ID serial primary key,
	name text not null,
	sku text unique not null,
	price decimal(6,2) not null,
	category integer references categories(ID)
);
