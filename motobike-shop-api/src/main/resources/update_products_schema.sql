-- Update products table to add new columns for enhanced product management
-- This script adds secondary_images (JSON) and specifications (TEXT) columns

-- Add secondary_images column to store multiple secondary image URLs as JSON
ALTER TABLE products 
ADD COLUMN secondary_images JSON COMMENT 'JSON array of secondary image URLs';

-- Add specifications column to store detailed product specifications
ALTER TABLE products 
ADD COLUMN specifications TEXT COMMENT 'Detailed product specifications';

-- Update existing products to have empty secondary_images array
UPDATE products 
SET secondary_images = '[]' 
WHERE secondary_images IS NULL;

-- Show the updated table structure
DESCRIBE products;

