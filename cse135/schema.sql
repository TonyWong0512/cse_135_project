DROP TABLE users CASCADE;
DROP TABLE categories CASCADE;
DROP TABLE products CASCADE;
DROP TABLE orders CASCADE;
DROP TABLE ordered CASCADE;

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
	order_pk integer references orders(id)
);



INSERT INTO categories (name, description) VALUES ('pede','ultrices a, auctor non,');
INSERT INTO categories (name, description) VALUES ('lectus','malesuada fames ac turpis egestas. Aliquam fringilla cursus purus. Nullam');
INSERT INTO categories (name, description) VALUES ('placerat','mi');
INSERT INTO categories (name, description) VALUES ('massa','montes, nascetur ridiculus mus. Aenean eget');
INSERT INTO categories (name, description) VALUES ('dui','vestibulum. Mauris magna. Duis dignissim');
INSERT INTO categories (name, description) VALUES ('Cras','Etiam gravida molestie');
INSERT INTO categories (name, description) VALUES ('Nulla','egestas. Aliquam fringilla cursus purus. Nullam scelerisque neque sed');
INSERT INTO categories (name, description) VALUES ('Sed','arcu. Sed eu nibh vulputate mauris');
INSERT INTO categories (name, description) VALUES ('sed','sit amet orci. Ut sagittis lobortis mauris.');
INSERT INTO categories (name, description) VALUES ('eu','dui, in sodales elit erat');
INSERT INTO categories (name, description) VALUES ('nisi','orci, consectetuer euismod est');
INSERT INTO categories (name, description) VALUES ('augue','quam');
INSERT INTO categories (name, description) VALUES ('Suspendisse','luctus sit amet, faucibus ut,');
INSERT INTO categories (name, description) VALUES ('nulla','lectus ante dictum mi, ac');
INSERT INTO categories (name, description) VALUES ('bibendum','augue ac');
INSERT INTO categories (name, description) VALUES ('Duis','velit eget laoreet posuere,');
INSERT INTO categories (name, description) VALUES ('porta','nulla ante, iaculis nec, eleifend');
INSERT INTO categories (name, description) VALUES ('ullamcorper','nonummy. Fusce fermentum');
INSERT INTO categories (name, description) VALUES ('lacus','tempus non, lacinia at, iaculis quis, pede.');
INSERT INTO categories (name, description) VALUES ('id','commodo ipsum. Suspendisse non');
INSERT INTO categories (name, description) VALUES ('libero','magna. Cras convallis convallis dolor. Quisque tincidunt pede ac urna.');
INSERT INTO categories (name, description) VALUES ('sit','mi');
INSERT INTO categories (name, description) VALUES ('consequat','nec, euismod in, dolor. Fusce feugiat. Lorem ipsum dolor sit');
INSERT INTO categories (name, description) VALUES ('Cum','non, cursus non, egestas a,');
INSERT INTO categories (name, description) VALUES ('ultrices','massa. Suspendisse eleifend. Cras');
INSERT INTO categories (name, description) VALUES ('nulla','varius ultrices, mauris');
INSERT INTO categories (name, description) VALUES ('vitae','nec enim. Nunc ut erat. Sed');
INSERT INTO categories (name, description) VALUES ('Phasellus','nec metus facilisis lorem tristique');
INSERT INTO categories (name, description) VALUES ('torquent','vitae');
INSERT INTO categories (name, description) VALUES ('ut','habitant morbi tristique senectus');
INSERT INTO categories (name, description) VALUES ('ultrices','congue, elit sed consequat auctor,');
INSERT INTO categories (name, description) VALUES ('velit','tempor erat neque non quam. Pellentesque habitant morbi');
INSERT INTO categories (name, description) VALUES ('auctor','ut ipsum ac mi eleifend');
INSERT INTO categories (name, description) VALUES ('vel','egestas rhoncus.');
INSERT INTO categories (name, description) VALUES ('erat','faucibus');
INSERT INTO categories (name, description) VALUES ('aliquam','Nunc laoreet lectus');
INSERT INTO categories (name, description) VALUES ('dictum','ut aliquam iaculis, lacus pede sagittis augue, eu tempor erat');
INSERT INTO categories (name, description) VALUES ('quis','lacus, varius');
INSERT INTO categories (name, description) VALUES ('non','egestas. Duis ac arcu.');
INSERT INTO categories (name, description) VALUES ('luctus','quam dignissim pharetra. Nam ac nulla. In');
INSERT INTO categories (name, description) VALUES ('accumsan','odio a purus. Duis elementum, dui quis accumsan convallis,');
INSERT INTO categories (name, description) VALUES ('metus','non leo. Vivamus nibh dolor, nonummy ac,');
INSERT INTO categories (name, description) VALUES ('magna','Sed dictum. Proin eget');
INSERT INTO categories (name, description) VALUES ('imperdiet','a, enim.');
INSERT INTO categories (name, description) VALUES ('varius','semper rutrum. Fusce dolor quam,');
INSERT INTO categories (name, description) VALUES ('Nunc','mus. Proin vel arcu eu odio');
INSERT INTO categories (name, description) VALUES ('arcu','placerat, augue. Sed molestie. Sed id risus quis');
INSERT INTO categories (name, description) VALUES ('imperet','a, enim.');
INSERT INTO categories (name, description) VALUES ('varis','semper rutrum. Fusce dolor quam,');
INSERT INTO categories (name, description) VALUES ('Nunca','mus. Proin vel arcu eu odio');
INSERT INTO categories (name, description) VALUES ('ar','placerat, augue. Sed molestie. Sed id risus quis');

