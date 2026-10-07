CREATE TABLE terminals (
    id UUID PRIMARY KEY,
    code VARCHAR(10) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL UNIQUE,
    active BOOLEAN NOT NULL DEFAULT TRUE
);

-- Seed Terminals
INSERT INTO terminals (id, code, name, active) VALUES
(gen_random_uuid(), 'CICT', 'Colombo International Container Terminals', true),
(gen_random_uuid(), 'CWIT', 'Colombo West International Terminal', true),
(gen_random_uuid(), 'ECT', 'East Container Terminal', true),
(gen_random_uuid(), 'JCT', 'Jaya Container Terminal', true),
(gen_random_uuid(), 'UCT', 'Unity Container Terminal', true),
(gen_random_uuid(), 'SAGT', 'South Asia Gateway Terminals', true);
