DROP TYPE IF EXISTS season;
DROP TABLE IF EXISTS sales_by_customer;
DROP TABLE IF EXISTS sales_by_state;

CREATE TYPE season AS ENUM ('summer', 'fall', 'winter', 'spring');

CREATE TABLE sales_by_customer (
	customer int REFERENCES customers(id) NOT NULL,
	season season NOT NULL,
	sales bigint NOT NULL
);

CREATE TABLE sales_by_state (
	state character(2) NOT NULL,
	season season NOT NULL,
	sales bigint NOT NULL
);