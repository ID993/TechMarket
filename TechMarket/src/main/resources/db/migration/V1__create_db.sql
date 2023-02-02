--CREATE TYPE status AS ENUM ('Active', 'Cancelled', 'Sold', 'Expired');

CREATE TABLE IF NOT EXISTS users (

user_id BIGSERIAL NOT NULL PRIMARY KEY,
full_name VARCHAR(255) NOT NULL,
email VARCHAR(255) UNIQUE NOT NULL,
password VARCHAR(255) NOT NULL

);

CREATE TABLE IF NOT EXISTS ad (

ad_id BIGSERIAL NOT NULL PRIMARY KEY,
name VARCHAR(255),
description VARCHAR(512) UNIQUE NOT NULL,
images VARCHAR(255),
start_price FLOAT(2),
status VARCHAR(255),
sold_price FLOAT(2),
sold_to BIGINT,
user_id BIGINT,
category_id BIGINT,
bid_id BIGINT

);

CREATE TABLE IF NOT EXISTS category (

category_id BIGSERIAL NOT NULL PRIMARY KEY,
name VARCHAR(255),
featured_image VARCHAR(255),
description VARCHAR(512) UNIQUE NOT NULL

);

CREATE TABLE IF NOT EXISTS bid (

bid_id BIGSERIAL NOT NULL PRIMARY KEY,
price FLOAT(2),
time_to_bid INT,
date_of_bid TIMESTAMP(2),
user_id BIGINT

);

CREATE TABLE IF NOT EXISTS alarm (

alarm_id BIGSERIAL NOT NULL PRIMARY KEY,
price_min FLOAT(2),
price_max FLOAT(2),
user_id BIGINT,
category_id BIGINT

);

CREATE TABLE IF NOT EXISTS keyword (

keyword_id BIGSERIAL NOT NULL PRIMARY KEY,
keyword VARCHAR(255)

);

CREATE TABLE ad_keywords (

ad_id BIGSERIAL NOT NULL REFERENCES ad (ad_id),
keyword_id BIGSERIAL NOT NULL REFERENCES keyword(keyword_id),

PRIMARY KEY (ad_id, keyword_id)
);

CREATE TABLE alarm_keyword (

alarm_id BIGSERIAL NOT NULL REFERENCES alarm (alarm_id),
keyword_id BIGSERIAL NOT NULL REFERENCES keyword(keyword_id),

PRIMARY KEY (alarm_id, keyword_id)
);

ALTER TABLE ad
    ADD CONSTRAINT fk_ad_user FOREIGN KEY (user_id) REFERENCES users (user_id),
    ADD CONSTRAINT fk_ad_category FOREIGN KEY (category_id) REFERENCES category (category_id),
    ADD CONSTRAINT fk_ad_bid FOREIGN KEY (bid_id) REFERENCES bid (bid_id);

ALTER TABLE bid
    ADD CONSTRAINT fk_bid_user FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE alarm
    ADD CONSTRAINT fk_alarm_user FOREIGN KEY (user_id) REFERENCES users (user_id),
    ADD CONSTRAINT fk_alarm_category FOREIGN KEY (category_id) REFERENCES category (category_id);