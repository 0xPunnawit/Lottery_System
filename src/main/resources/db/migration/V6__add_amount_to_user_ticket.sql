ALTER TABLE lottery_schema.user_ticket
ADD COLUMN amount INT NOT NULL DEFAULT 1;

CREATE INDEX idx_user_ticket_amount ON lottery_schema.user_ticket(amount);
