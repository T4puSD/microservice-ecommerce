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
-- preconditions onFail:MARK_RAN onError:MARK_RAN
-- precondition-sql-check expectedResult:1 select count(*) from information_schema.tables where table_name = 'product';
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.columns where table_name = 'product' and column_name = 'price';
ALTER TABLE "product" ADD COLUMN price float NOT NULL DEFAULT 0.0;

-- changeset tapusd:product-price-field-add-update-init-data
-- preconditions onFail:MARK_RAN onError:MARK_RAN
-- precondition-sql-check expectedResult:3 select count(*) from product;
UPDATE "product" set price = 199.0 WHERE name = 'Iphone 12s';
UPDATE "product" set price = 399.0 WHERE name = 'Iphone 13 Pro';
UPDATE "product" set price = 250.0 WHERE name = 'Samsung Galaxy 23 Ultra';

-- changeset tapusd:product-name-unique
-- preconditions onFail:MARK_RAN onError:MARK_RAN
-- precondition-sql-check expectedResult:0 select * from information_schema.table_constraints where table_name = 'product' and constraint_name = 'unique_product_name';
ALTER TABLE "product" ADD CONSTRAINT "unique_product_name" UNIQUE (name);
--rollback alter table "product" drop constraint "unique-product-name";