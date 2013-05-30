DROP TYPE IF EXISTS season CASCADE;
DROP TABLE IF EXISTS sales_by_customer;
DROP TABLE IF EXISTS sales_by_state;
DROP TABLE IF EXISTS sales_by_product;
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

CREATE TABLE sales_by_product (
	state character(2) NOT NULL,
	season season NOT NULL,
	sales bigint NOT NULL,
	customer int REFERENCES customers(id) NOT NULL,
	sku int REFERENCES products(sku) NOT NULL
);

CREATE OR REPLACE FUNCTION add_sales() RETURNS TRIGGER AS $$
BEGIN
	UPDATE sales_by_state SET state=(SELECT state FROM sales, customers WHERE customers.id=NEW.customer_id AND sales.id=NEW.id), season=season_of(NEW.month), sales=sales + NEW.total_cost WHERE state=(SELECT state FROM sales, customers WHERE customers.id=NEW.customer_id AND sales.id=NEW.id) AND season=season_of(NEW.month);
	INSERT INTO sales_by_state (state, season, sales)
		SELECT (SELECT state FROM sales, customers WHERE customers.id=NEW.customer_id AND sales.id=NEW.id),
		season_of(NEW.month),
		NEW.total_cost
		WHERE NOT EXISTS (SELECT 1 FROM sales_by_state WHERE state=(SELECT state FROM sales, customers WHERE customers.id=NEW.customer_id AND sales.id=NEW.id) AND season=season_of(NEW.month));
	UPDATE sales_by_customer SET customer=NEW.customer_id, season=season_of(NEW.month), sales=sales + NEW.total_cost WHERE customer=NEW.customer_id AND season=season_of(NEW.month);
	INSERT INTO sales_by_customer (customer, season, sales)
		SELECT NEW.customer_id, season_of(NEW.month), NEW.total_cost
		WHERE NOT EXISTS (SELECT 1 FROM sales_by_customer WHERE customer=NEW.customer_id AND season=season_of(NEW.month));
	UPDATE sales_by_product SET sku=NEW.product_id, customer=NEW.customer_id, season=season_of(NEW.month), sales=sales + NEW.total_cost WHERE sku=NEW.product_id AND season=season_of(NEW.month);
	INSERT INTO sales_by_product (state, season, sales, customer, sku)
		SELECT (SELECT state FROM sales, customers WHERE customers.id=NEW.customer_id AND sales.id=NEW.id), season_of(NEW.month), NEW.total_cost, NEW.customer_id, NEW.product_id 
		WHERE NOT EXISTS (SELECT 1 FROM sales_by_product WHERE sku=NEW.product_id AND season=season_of(NEW.month));
	RETURN NEW;
END$$ LANGUAGE 'plpgsql';

CREATE TRIGGER add_sales AFTER INSERT ON sales FOR EACH ROW EXECUTE PROCEDURE add_sales();


CREATE OR REPLACE FUNCTION season_of(month int) RETURNS season AS $$
BEGIN
	RETURN CASE
		WHEN month >= 3 AND month <= 5 THEN 'spring'
		WHEN month >= 6 AND month <= 8 THEN 'summer'
		WHEN month >= 9 AND month <= 11 THEN 'fall'
		ELSE 'winter' END;
END$$ LANGUAGE 'plpgsql';
