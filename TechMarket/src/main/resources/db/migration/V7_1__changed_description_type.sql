ALTER TABLE ad
    DROP CONSTRAINT ad_description_key,
    ADD COLUMN IF NOT EXISTS description VARCHAR(512);