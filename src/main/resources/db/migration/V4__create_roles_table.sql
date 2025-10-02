CREATE TABLE IF NOT EXISTS lottery_schema.roles (
    role_id BIGSERIAL PRIMARY KEY,
    role_name VARCHAR(20) NOT NULL UNIQUE
);
