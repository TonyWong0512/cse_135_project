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
	category integer references categories(id)
);

create table orders (
	id serial primary key,
	user_pk integer references users(id)
);
	

create table ordered (
	id serial primary key,
	product integer references products(id),
	quantity smallint not null,
	order_pk integer references orders(id)
);



INSERT INTO categories (name, description) values ('Mary', 'Doe');
