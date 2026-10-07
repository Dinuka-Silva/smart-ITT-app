-- Seed a demo Supervisor account
-- Password: supervisor (BCrypt hashed)
INSERT INTO users (id, full_name, employee_id, nic, mobile_number, email, password, role, status, created_at, updated_at)
VALUES (
    gen_random_uuid(),
    'Nimal Silva',
    'SUP001',
    '199012345678',
    '0711234567',
    'supervisor@smartitt.lk',
    '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lBji', -- "supervisor"
    'SUPERVISOR',
    'ACTIVE',
    NOW(),
    NOW()
) ON CONFLICT (email) DO NOTHING;

-- Get the supervisor user id and link to assigned terminal CICT
DO $$
DECLARE
    sup_id UUID;
    terminal_id UUID;
BEGIN
    SELECT id INTO sup_id FROM users WHERE email = 'supervisor@smartitt.lk';
    SELECT id INTO terminal_id FROM terminals WHERE code = 'CICT';

    IF sup_id IS NOT NULL AND terminal_id IS NOT NULL THEN
        INSERT INTO supervisors (id, assigned_terminal_id)
        VALUES (sup_id, terminal_id)
        ON CONFLICT (id) DO NOTHING;
    END IF;
END $$;

-- Seed a demo Driver account
-- Password: driver (BCrypt hashed)
INSERT INTO users (id, full_name, employee_id, nic, mobile_number, email, password, role, status, created_at, updated_at)
VALUES (
    gen_random_uuid(),
    'Kamal Perera',
    'DRV001',
    '198512345678',
    '0779876543',
    'driver@smartitt.lk',
    '$2a$10$8Jdz6.Ey2b4LU2w8vU0qReO3.LEnfOz6WCseyEdZh6FoWtfBzQOsS', -- "driver"
    'DRIVER',
    'ACTIVE',
    NOW(),
    NOW()
) ON CONFLICT (email) DO NOTHING;

-- Link driver to drivers table
DO $$
DECLARE
    drv_id UUID;
BEGIN
    SELECT id INTO drv_id FROM users WHERE email = 'driver@smartitt.lk';
    IF drv_id IS NOT NULL THEN
        INSERT INTO drivers (id, vehicle_number, driving_licence_number)
        VALUES (drv_id, 'WP-BA-1234', 'LIC-001234')
        ON CONFLICT (id) DO NOTHING;
    END IF;
END $$;
