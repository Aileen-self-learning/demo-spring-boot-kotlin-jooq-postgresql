DROP TABLE IF EXISTS banks;
CREATE TABLE banks
(
    account_number  VARCHAR(255) NOT NULL PRIMARY KEY,
    trust     FLOAT(8) NOT NULL,
    transaction_fee  INT NOT NULL
);