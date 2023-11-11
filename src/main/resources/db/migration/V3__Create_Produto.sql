CREATE TABLE IF NOT EXISTS produtos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255),
    valor FLOAT(2) NOT NULL,
    quantidade INT NOT NULL,
    tipoProduto BIGINT NOT NULL
);

ALTER TABLE produtos
ADD CONSTRAINT "FK01" FOREIGN KEY (tipoProduto) REFERENCES tipo_produto(id);

INSERT INTO produtos (nome, descricao, valor, quantidade, tipoProduto)
VALUES ('Garrafa', 'É uma garrafa', 5.00, 5, 1);

INSERT INTO produtos (nome, descricao, valor, quantidade, tipoProduto)
VALUES ('Caneta', 'É uma caneta', 5.00, 5, 2);

INSERT INTO produtos (nome, descricao, valor, quantidade, tipoProduto)
VALUES ('Garfo', 'É um garfo', 5.00, 5, 3);

INSERT INTO produtos (nome, descricao, valor, quantidade, tipoProduto)
VALUES ('Borracha', 'É uma borracha', 5.00, 5, 4);
