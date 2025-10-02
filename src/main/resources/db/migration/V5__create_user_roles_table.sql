CREATE TABLE IF NOT EXISTS lottery_schema.user_roles (
    user_id VARCHAR(10) NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES lottery_schema.users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES lottery_schema.roles(role_id) ON DELETE CASCADE
);
