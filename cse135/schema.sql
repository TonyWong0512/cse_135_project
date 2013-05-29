DROP TYPE IF EXISTS season CASCADE;
DROP TABLE IF EXISTS sales_by_customer;
DROP TABLE IF EXISTS sales_by_state;
DROP TRIGGER IF EXISTS update_sales on sales;
DROP TRIGGER IF EXISTS add_sales on sales;
DROP TRIGGER IF EXISTS remove_sales on sales;

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

CREATE OR REPLACE FUNCTION add_sales() RETURNS TRIGGER AS '
BEGIN
	INSERT INTO sales_by_state DEFAULT VALUES;
END' LANGUAGE 'plpgsql';

CREATE TRIGGER add_sales AFTER INSERT ON sales EXECUTE PROCEDURE add_sales()
