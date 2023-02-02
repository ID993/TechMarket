ALTER TABLE ad_keywords
    DROP CONSTRAINT ad_keywords_ad_id_fkey,
    DROP CONSTRAINT ad_keywords_keyword_id_fkey,
    ADD CONSTRAINT ad_keywords_ad_id_fkey FOREIGN KEY (ad_id) REFERENCES ad (ad_id) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT ad_keywords_keyword_id_fkey FOREIGN KEY (keyword_id) REFERENCES keyword (keyword_id) ON DELETE CASCADE ON UPDATE CASCADE;