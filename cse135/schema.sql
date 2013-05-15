create table users (
	id serial primary key,
	name text unique not null,
	role text,
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
	category integer references categories(ID)
);

INSERT INTO categories (name, description) values ('Mary', 'Doe');