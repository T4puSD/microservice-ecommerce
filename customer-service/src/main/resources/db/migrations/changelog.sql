-- liquibase formatted sql

-- changeset tapusd:1691412517889-1
-- preconditions onFail:MARK_RAN onError:MARK_RAN
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where table_name = 'customer';
CREATE TABLE "customer" ("id" INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, "name" VARCHAR(150) NOT NULL, "email" VARCHAR(255) NOT NULL, "password" VARCHAR(255) NOT NULL, "date_of_birth" date, CONSTRAINT "customer_pkey" PRIMARY KEY ("id"));

-- changeset tapusd:Customer-Load-Data
-- preconditions onFail:MARK_RAN onError:MARK_RAN
-- precondition-sql-check expectedResult:1 select count(*) from information_schema.tables where table_name = 'customer';
-- precondition-sql-check expectedResult:0 select count(*) from customer;
INSERT INTO customer (name, email, password, date_of_birth) VALUES
    ('Jack', 'jack@dummy.com', 'dummy', '1999-04-01'),
    ('Amy', 'amy@dummy.com', 'dummy', '1999-04-01');

-- changeset tapusd:add-password_salt_field
-- preconditions onFail:MARK_RAN onError:MARK_RAN
-- precondition-sql-check expectedResult:1 select count(*) from information_schema.tables where table_name = 'customer';
ALTER TABLE customer ADD COLUMN password_salt varchar(12) NOT NULL DEFAULT 'Sgb238HBl23x';
-- new password is also dummy in bcrypt for all previous users
UPDATE customer SET password = '$2a$12$PDdZsB1u7QgN1QyIFI6hluO41LaH.DjsWQV62jJPMhoTPFRoIq8Yq' WHERE password = 'dummy';