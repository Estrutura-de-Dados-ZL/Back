CREATE TABLE IF NOT EXISTS checkout (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente OBJECT NOT NULL,
    valor_total FLOAT(2) NOT NULL,
    produtos OBJECT NOT NULL
);