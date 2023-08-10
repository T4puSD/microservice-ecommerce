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