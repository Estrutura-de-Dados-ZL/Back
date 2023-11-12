CREATE TABLE IF NOT EXISTS tipo_produto(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL
);

INSERT INTO tipo_produto (descricao)
VALUES ('produtos industriais');

INSERT INTO tipo_produto (descricao)
VALUES ('bens de conveniência');

INSERT INTO tipo_produto (descricao)
VALUES ('bens de impulso');

INSERT INTO tipo_produto (descricao)
VALUES ('bens perecíveis');

INSERT INTO tipo_produto (descricao)
VALUES ('produtos ao consumidor');

INSERT INTO tipo_produto (descricao)
VALUES ('bens de compra comparada');

INSERT INTO tipo_produto (descricao)
VALUES ('bens de especialidade');

INSERT INTO tipo_produto (descricao)
VALUES ('bens duráveis');

