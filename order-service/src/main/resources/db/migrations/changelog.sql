-- liquibase formatted sql

-- changeset tapusd:order-init
-- preconditions onFail:MARK_RAN onError:MARK_RAN
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where table_name = 'order';
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where table_name = 'order_product';
CREATE TABLE "order" (
    id serial PRIMARY KEY,
    order_no VARCHAR(250) NOT NULL,
    customer_id bigint NOT NULL,
    total float
);
CREATE TABLE "order_product" (
     id serial PRIMARY KEY,
     order_id bigint NOT NULL,
     product_id bigint NOT NULL,
     price float NOT NULL,
     quantity float NOT NULL,
     FOREIGN KEY (order_id) REFERENCES "order"(id)
);

-- changeset tapusd:order-add-customer-name-email-column
-- preconditions onFail:MARK_RAN onError:MARK_RAN
-- precondition-sql-check expectedResult:1 select count(*) from information_schema.tables where table_name = 'order';
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.columns where table_name = 'order' and column_name = 'customer_name';
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.columns where table_name = 'order' and column_name = 'customer_email';
ALTER TABLE "order" ADD customer_name varchar(150) NOT NULL DEFAULT '',
    ADD customer_email varchar(255) NOT NULL DEFAULT '';

-- changeset tapusd:order-change-order-product-quantity-to-integer-from-float
-- preconditions onFail:MARK_RAN onError:MARK_RAN
-- precondition-sql-check expectedResult:1 select count(*) from information_schema.tables where table_name = 'order_product';
-- precondition-sql-check expectedResult:1 select count(*) from information_schema.columns where table_name = 'order_product' and column_name = 'quantity';
ALTER TABLE "order_product" ALTER COLUMN quantity TYPE int;

-- changeset tapusd:order-change-order-no-type-from-varchar-to-uuid
-- preconditions onFail:MARK_RAN onError:MARK_RAN
-- precondition-sql-check expectedResult:1 select count(*) from information_schema.tables where table_name = 'order_product';
-- precondition-sql-check expectedResult:1 select count(*) from information_schema.columns where table_name = 'order' and column_name = 'order_no';
ALTER TABLE "order" ALTER COLUMN order_no TYPE uuid USING order_no::uuid;
