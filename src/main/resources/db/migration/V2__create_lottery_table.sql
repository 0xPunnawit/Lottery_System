CREATE TABLE IF NOT EXISTS lottery_schema.lottery (
    ticket_id VARCHAR(6) PRIMARY KEY,
    price INT NOT NULL,
    amount INT NOT NULL
);
