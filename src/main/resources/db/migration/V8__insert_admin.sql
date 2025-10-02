INSERT INTO lottery_schema.users (
    user_id, email, password, fullname, phone, created_at, updated_at
)
VALUES
    ('adminuser0', 'admin@lottery.com',
     '$2a$10$OzggAYk9M389W6.B0GKeq.FNozoA.zJgPgam40/B4QTuPNTWxXc16', 'Admin User', '0123456789', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (user_id) DO NOTHING;

INSERT INTO lottery_schema.user_roles (user_id, role_id)
VALUES 
    ('adminuser0', (SELECT role_id FROM lottery_schema.roles WHERE role_name = 'ADMIN'))
ON CONFLICT DO NOTHING;
