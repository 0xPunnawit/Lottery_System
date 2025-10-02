INSERT INTO lottery_schema.roles (role_name) VALUES ('USER') ON CONFLICT DO NOTHING;
INSERT INTO lottery_schema.roles (role_name) VALUES ('ADMIN') ON CONFLICT DO NOTHING;
