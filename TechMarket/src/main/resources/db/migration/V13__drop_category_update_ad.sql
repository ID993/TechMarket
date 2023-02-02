DROP TABLE category CASCADE;
ALTER TABLE ad
    DROP COLUMN status,
    DROP COLUMN category,
    ADD COLUMN status INT,
    ADD COLUMN category INT;