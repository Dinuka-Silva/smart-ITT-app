-- =====================================================
-- V4: Add Driver Code and Profile fields
-- =====================================================

-- Users table updates
ALTER TABLE users ADD COLUMN IF NOT EXISTS username VARCHAR(50);
ALTER TABLE users ADD COLUMN IF NOT EXISTS address TEXT;
ALTER TABLE users ADD COLUMN IF NOT EXISTS date_of_birth DATE;
ALTER TABLE users ADD COLUMN IF NOT EXISTS emergency_contact_name VARCHAR(150);
ALTER TABLE users ADD COLUMN IF NOT EXISTS emergency_contact_number VARCHAR(30);
ALTER TABLE users ADD COLUMN IF NOT EXISTS profile_photo TEXT;

-- Drivers table updates
ALTER TABLE drivers ADD COLUMN IF NOT EXISTS driver_code VARCHAR(30);
ALTER TABLE drivers ADD COLUMN IF NOT EXISTS license_expiry_date DATE;

-- Allow vehicle_number to be nullable for fresh driver registration
ALTER TABLE drivers ALTER COLUMN vehicle_number DROP NOT NULL;

-- Unique constraints
CREATE UNIQUE INDEX IF NOT EXISTS idx_users_username ON users(username) WHERE username IS NOT NULL;
CREATE UNIQUE INDEX IF NOT EXISTS idx_drivers_driver_code ON drivers(driver_code) WHERE driver_code IS NOT NULL;

-- Update existing demo driver with driver_code DRV-00001
UPDATE drivers d
SET driver_code = 'DRV-00001'
FROM users u
WHERE d.id = u.id AND u.email = 'driver@smartitt.lk' AND d.driver_code IS NULL;

UPDATE users
SET username = 'DRV-00001',
    address = 'No. 45, Harbour View Road, Colombo 15',
    date_of_birth = '1985-04-12',
    emergency_contact_name = 'Sunil Perera',
    emergency_contact_number = '0771122334'
WHERE email = 'driver@smartitt.lk' AND username IS NULL;

UPDATE users
SET username = 'supervisor@smartitt.lk'
WHERE email = 'supervisor@smartitt.lk' AND username IS NULL;
