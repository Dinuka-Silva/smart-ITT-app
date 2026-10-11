-- Migration V5: Seed SCK ITT Vehicle Fleet (19 Vehicles) and operator column

ALTER TABLE vehicles ADD COLUMN IF NOT EXISTS operator VARCHAR(100) DEFAULT 'SCK Logistics';
ALTER TABLE drivers ADD COLUMN IF NOT EXISTS chassis_number VARCHAR(50);

-- Insert 19 SCK ITT Fleet Vehicles
-- 1. SCK Logistics Main Fleet (10 vehicles)
INSERT INTO vehicles (id, vehicle_number, chassis_number, operator, status, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'LY 5234', 'SCK 100', 'SCK Logistics', 'ACTIVE', NOW(), NOW()),
    (gen_random_uuid(), 'LY 5235', 'SCK 101', 'SCK Logistics', 'ACTIVE', NOW(), NOW()),
    (gen_random_uuid(), 'LY 5236', 'SCK 102', 'SCK Logistics', 'ACTIVE', NOW(), NOW()),
    (gen_random_uuid(), 'LY 5237', 'SCK 103', 'SCK Logistics', 'ACTIVE', NOW(), NOW()),
    (gen_random_uuid(), 'LY 5238', 'SCK 104', 'SCK Logistics', 'ACTIVE', NOW(), NOW()),
    (gen_random_uuid(), 'LY 5665', 'SCK 105', 'SCK Logistics', 'ACTIVE', NOW(), NOW()),
    (gen_random_uuid(), 'LY 5708', 'SCK 106', 'SCK Logistics', 'ACTIVE', NOW(), NOW()),
    (gen_random_uuid(), 'LY 5711', 'SCK 107', 'SCK Logistics', 'ACTIVE', NOW(), NOW()),
    (gen_random_uuid(), 'LY 5717', 'SCK 108', 'SCK Logistics', 'ACTIVE', NOW(), NOW()),
    (gen_random_uuid(), 'LY 5721', 'SCK 109', 'SCK Logistics', 'ACTIVE', NOW(), NOW()),

-- 2. SDR LINK Fleet (6 vehicles)
    (gen_random_uuid(), 'LY 6528', 'SCK 115', 'SDR LINK', 'ACTIVE', NOW(), NOW()),
    (gen_random_uuid(), 'LY 6529', 'SCK 117', 'SDR LINK', 'ACTIVE', NOW(), NOW()),
    (gen_random_uuid(), 'LY 5596', 'SCK 122', 'SDR LINK', 'ACTIVE', NOW(), NOW()),
    (gen_random_uuid(), 'LY 6530', 'SCK 119', 'SDR LINK', 'ACTIVE', NOW(), NOW()),
    (gen_random_uuid(), 'LY 6531', 'SCK 120', 'SDR LINK', 'ACTIVE', NOW(), NOW()),
    (gen_random_uuid(), 'LY 6532', 'SCK 121', 'SDR LINK', 'ACTIVE', NOW(), NOW()),

-- 3. E3 Logistics Fleet (3 vehicles)
    (gen_random_uuid(), 'LY 5597', 'SCK 123', 'E3 Logistics', 'ACTIVE', NOW(), NOW()),
    (gen_random_uuid(), 'LY 5598', 'SCK 124', 'E3 Logistics', 'ACTIVE', NOW(), NOW()),
    (gen_random_uuid(), 'LY 5600', 'SCK 125', 'E3 Logistics', 'ACTIVE', NOW(), NOW())
ON CONFLICT (vehicle_number) DO UPDATE
SET chassis_number = EXCLUDED.chassis_number,
    operator = EXCLUDED.operator,
    updated_at = NOW();
