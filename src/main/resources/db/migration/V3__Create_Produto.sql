CREATE TABLE IF NOT EXISTS produtos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255),
    valor FLOAT(2) NOT NULL,
    quantidade INT NOT NULL
);

INSERT INTO produtos (nome, descricao, valor, quantidade)
VALUES ('Garrafa', 'É uma garrafa', 5.00, 5);

INSERT INTO produtos (nome, descricao, valor, quantidade)
VALUES ('Caneta', 'É uma caneta', 5.00, 5);

INSERT INTO produtos (nome, descricao, valor, quantidade)
VALUES ('Garfo', 'É um garfo', 5.00, 5);

INSERT INTO produtos (nome, descricao, valor, quantidade)
VALUES ('Borracha', 'É uma borracha', 5.00, 5);
