CREATE TABLE nasabah
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name       VARCHAR(255) NOT NULL,
    address         TEXT         NOT NULL,
    birth_place     VARCHAR(100) NOT NULL,
    birth_date      DATE         NOT NULL,
    identity_number VARCHAR(16)  NOT NULL UNIQUE,
    phone           VARCHAR(20)  NOT NULL
);