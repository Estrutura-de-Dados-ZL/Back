CREATE TABLE IF NOT EXISTS produtos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255),
    valor FLOAT(2) NOT NULL,
    quantidade INT NOT NULL,
    tipo_produto BIGINT NOT NULL
);

ALTER TABLE produtos
ADD CONSTRAINT "FK01" FOREIGN KEY (tipo_produto) REFERENCES tipo_produto(id)
ON DELETE CASCADE;

INSERT INTO produtos (nome, descricao, valor, quantidade, tipo_produto)
VALUES ('Garrafa', 'É uma garrafa', 5.00, 5, 2);

INSERT INTO produtos (nome, descricao, valor, quantidade, tipo_produto)
VALUES ('Caneta', 'É uma caneta', 5.00, 5, 2);

INSERT INTO produtos (nome, descricao, valor, quantidade, tipo_produto)
VALUES ('Garfo', 'É um garfo', 5.00, 5, 3);

INSERT INTO produtos (nome, descricao, valor, quantidade, tipo_produto)
VALUES ('Borracha', 'É uma borracha', 5.00, 5, 4);
