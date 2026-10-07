-- =====================================================
-- V2: Create Users, Drivers, Supervisors tables
-- =====================================================

CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    full_name VARCHAR(150) NOT NULL,
    employee_id VARCHAR(50) NOT NULL UNIQUE,
    nic VARCHAR(15) NOT NULL UNIQUE,
    mobile_number VARCHAR(20) NOT NULL UNIQUE,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
    status VARCHAR(30) NOT NULL DEFAULT 'PENDING_APPROVAL',
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE drivers (
    id UUID PRIMARY KEY REFERENCES users(id),
    vehicle_number VARCHAR(50) NOT NULL UNIQUE,
    driving_licence_number VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE supervisors (
    id UUID PRIMARY KEY REFERENCES users(id),
    assigned_terminal_id UUID NOT NULL REFERENCES terminals(id)
);

-- =====================================================
-- V3: Create Vehicles table
-- =====================================================
CREATE TABLE vehicles (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    vehicle_number VARCHAR(50) NOT NULL UNIQUE,
    chassis_number VARCHAR(100) UNIQUE,
    assigned_driver_id UUID REFERENCES drivers(id),
    status VARCHAR(30) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- =====================================================
-- V4: Create Trips and Containers
-- =====================================================
CREATE TABLE trips (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    trip_number VARCHAR(30) NOT NULL UNIQUE,
    driver_id UUID NOT NULL REFERENCES drivers(id),
    vehicle_number VARCHAR(50),
    vessel_name VARCHAR(150) NOT NULL,
    chassis_number VARCHAR(100),
    source_terminal VARCHAR(10) NOT NULL,
    dest_terminal VARCHAR(10) NOT NULL,
    status VARCHAR(30) NOT NULL DEFAULT 'IN_PROGRESS',
    notes TEXT,
    start_time TIMESTAMP DEFAULT NOW(),
    end_time TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE containers (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    container_number VARCHAR(20) NOT NULL,
    size VARCHAR(10) NOT NULL,
    trip_id UUID NOT NULL REFERENCES trips(id) ON DELETE CASCADE,
    source_terminal VARCHAR(10) NOT NULL,
    dest_terminal VARCHAR(10) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'LOADED',
    unloaded_at VARCHAR(10),
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- =====================================================
-- V5: Approvals and Notifications
-- =====================================================
CREATE TABLE trip_approvals (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    trip_id UUID NOT NULL REFERENCES trips(id),
    supervisor_id UUID NOT NULL REFERENCES supervisors(id),
    status VARCHAR(20) NOT NULL,
    rejection_reason TEXT,
    decision_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE notifications (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES users(id),
    title VARCHAR(200) NOT NULL,
    body TEXT NOT NULL,
    is_read BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE device_tokens (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES users(id),
    token TEXT NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE refresh_tokens (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES users(id),
    token TEXT NOT NULL UNIQUE,
    expiry TIMESTAMP NOT NULL,
    revoked BOOLEAN NOT NULL DEFAULT FALSE
);

-- Indexes
CREATE INDEX idx_trips_driver_id ON trips(driver_id);
CREATE INDEX idx_trips_status ON trips(status);
CREATE INDEX idx_trips_source_terminal ON trips(source_terminal);
CREATE INDEX idx_trips_dest_terminal ON trips(dest_terminal);
CREATE INDEX idx_containers_trip_id ON containers(trip_id);
CREATE INDEX idx_containers_status ON containers(status);
CREATE INDEX idx_notifications_user_id ON notifications(user_id);
