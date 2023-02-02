alter table files
    add column ad_id bigint,
    ADD CONSTRAINT fk_ad_files FOREIGN KEY (ad_id) REFERENCES ad (ad_id);