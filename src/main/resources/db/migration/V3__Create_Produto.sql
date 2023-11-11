CREATE TABLE IF NOT EXISTS produtos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255),
    valor FLOAT(2) NOT NULL,
    quantidade INT NOT NULL,
    imagem VARCHAR(255),
    tipo_produto BIGINT NOT NULL
);

ALTER TABLE produtos
ADD CONSTRAINT "FK01" FOREIGN KEY (tipo_produto) REFERENCES tipo_produto(id)
ON DELETE CASCADE;

INSERT INTO produtos (nome, descricao, valor, quantidade, imagem, tipo_produto)
VALUES ('Garrafa', 'É uma garrafa', 5.00, 5, 'https://m.media-amazon.com/images/I/410+bHyQAIL._AC_SY300_SX300_.jpg', 2);

INSERT INTO produtos (nome, descricao, valor, quantidade, imagem, tipo_produto)
VALUES ('Caneta', 'É uma caneta', 5.00, 5, 'https://images.tcdn.com.br/img/img_prod/1140357/caneta_esferografica_bic_cristal_dura_mais_azul_ponta_media_de_1_0mm_2637_1_671e4fdf5e2c89e777e6923939970830.jpg', 2);

INSERT INTO produtos (nome, descricao, valor, quantidade, imagem, tipo_produto)
VALUES ('Garfo', 'É um garfo', 5.00, 5, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ28rP3TKlrlV6UhHK13Tq2h0G3aJkDx1U-CQ&usqp=CAU', 3);

INSERT INTO produtos (nome, descricao, valor, quantidade, tipo_produto)
VALUES ('Borracha', 'É uma borracha', 5.00, 5, 4);
