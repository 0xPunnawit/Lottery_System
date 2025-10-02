CREATE TABLE IF NOT EXISTS lottery_schema.user_ticket (
    user_ticket_id BIGSERIAL PRIMARY KEY,
    user_id VARCHAR(10) NOT NULL,
    ticket_id VARCHAR(6) NOT NULL,
    purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES lottery_schema.users (user_id) ON DELETE CASCADE,
    FOREIGN KEY (ticket_id) REFERENCES lottery_schema.lottery (ticket_id) ON DELETE CASCADE,
    UNIQUE(user_id, ticket_id)
);
