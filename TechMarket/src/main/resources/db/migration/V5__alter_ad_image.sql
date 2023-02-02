ALTER TABLE ad
    DROP COLUMN images,
    ADD COLUMN image_id UUID,
    ADD CONSTRAINT fk_ad_images FOREIGN KEY (image_id) REFERENCES images (image_id);

ALTER TABLE category
    DROP COLUMN featured_image,
    ADD COLUMN image_id UUID,
    ADD CONSTRAINT fk_category_images FOREIGN KEY (image_id) REFERENCES images (image_id);

CREATE TABLE IF NOT EXISTS ad_images (

    ad_id BIGINT REFERENCES ad (ad_id) ON UPDATE CASCADE ON DELETE CASCADE,
    image_id UUID REFERENCES images (image_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT ad_images_pkey PRIMARY KEY (ad_id, image_id)

);

