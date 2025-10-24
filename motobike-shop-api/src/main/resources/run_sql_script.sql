-- Add secondary image columns to products table
ALTER TABLE products 
ADD COLUMN secondary_image_1 VARCHAR(500),
ADD COLUMN secondary_image_2 VARCHAR(500),
ADD COLUMN secondary_image_3 VARCHAR(500);

-- Verify the columns were added
SELECT COLUMN_NAME, DATA_TYPE, CHARACTER_MAXIMUM_LENGTH 
FROM INFORMATION_SCHEMA.COLUMNS 
WHERE TABLE_NAME = 'products' 
AND COLUMN_NAME LIKE '%secondary%';

