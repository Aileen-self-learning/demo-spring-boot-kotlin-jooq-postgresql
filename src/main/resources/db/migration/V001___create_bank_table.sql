CREATE TABLE IF NOT EXISTS banks
(
    account_number  VARCHAR(255) NOT NULL PRIMARY KEY,
    trust     FLOAT8 NOT NULL,
    transaction_fee  INT NOT NULL
);