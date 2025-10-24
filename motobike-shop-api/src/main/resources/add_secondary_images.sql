-- Add secondary image columns to products table
ALTER TABLE products 
ADD COLUMN secondary_image_1 VARCHAR(500),
ADD COLUMN secondary_image_2 VARCHAR(500),
ADD COLUMN secondary_image_3 VARCHAR(500);

