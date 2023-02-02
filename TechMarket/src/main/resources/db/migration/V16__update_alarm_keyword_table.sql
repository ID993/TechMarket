DROP TABLE alarm_keyword;
CREATE TABLE IF NOT EXISTS alarm_keyword (

        alarm_id BIGINT REFERENCES alarm (alarm_id) ON UPDATE CASCADE ON DELETE CASCADE,
        keyword_id BIGINT REFERENCES keyword (keyword_id) ON UPDATE CASCADE ON DELETE CASCADE,
        CONSTRAINT alarm_keyword_pkey PRIMARY KEY (alarm_id, keyword_id)

);