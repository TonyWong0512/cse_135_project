create table users (
	ID serial primary key,
	name text unique not null,
	role text,
	age integer,
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
	category integer references categories(ID)
);