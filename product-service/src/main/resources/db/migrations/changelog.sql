-- liquibase formatted sql

-- changeset tapusd:product-init
-- preconditions onFail:MARK_RAN onError:MARK_RAN
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where table_name = 'product';
CREATE TABLE "product" (id serial PRIMARY KEY, "name" VARCHAR(150) NOT NULL);

-- changeset tapusd:product-load-data
-- preconditions onFail:MARK_RAN onError:MARK_RAN
-- precondition-sql-check expectedResult:1 select count(*) from information_schema.tables where table_name = 'product';
-- precondition-sql-check expectedResult:0 select count(*) from product;
INSERT INTO product (name) VALUES
    ('Iphone 12s'),
    ('Iphone 13 Pro'),
    ('Samsung Galaxy 23 Ultra');

-- changeset tapusd:product-price-field-add
-- precondition-sql-check expectedResult:1 select count(*) from information_schema.tables where table_name = 'product';
ALTER TABLE "product" ADD COLUMN price float NOT NULL DEFAULT 0.0;

-- changeset tapusd:product-price-field-add-update-init-data
-- precondition-sql-check expectedResult:3 select count(*) from product;
UPDATE "product" set price = 199.0 WHERE name = 'Iphone 12s';
UPDATE "product" set price = 399.0 WHERE name = 'Iphone 13 Pro';
UPDATE "product" set price = 250.0 WHERE name = 'Samsung Galaxy 23 Ultra';
