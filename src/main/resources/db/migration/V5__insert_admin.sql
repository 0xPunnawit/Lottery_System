-- Insert admin user into users table
INSERT INTO lottery_schema.users (user_id, email, password, fullname, phone, created_at, updated_at, role)
VALUES
    ('adminuser0', 'admin@lottery.com',
     '$2a$10$OzggAYk9M389W6.B0GKeq.FNozoA.zJgPgam40/B4QTuPNTWxXc16', 'Admin User', '0123456789',
     CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ADMIN')
ON CONFLICT (user_id) DO NOTHING;  -- Avoid inserting if the user already exists
