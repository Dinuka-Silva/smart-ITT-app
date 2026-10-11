-- =====================================================
-- V6: Add cover_image and ensure profile_photo and cover_image are TEXT
-- =====================================================

ALTER TABLE users ADD COLUMN IF NOT EXISTS cover_image TEXT;
ALTER TABLE users ALTER COLUMN profile_photo TYPE TEXT;
ALTER TABLE users ALTER COLUMN cover_image TYPE TEXT;
