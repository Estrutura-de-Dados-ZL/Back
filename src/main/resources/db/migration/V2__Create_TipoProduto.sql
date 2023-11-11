CREATE TABLE IF NOT EXISTS tipo_produto(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL
);

INSERT INTO tipo_produto (descricao)
VALUES ('produtos ao consumidor (ou bens de consumo) - usados por usuários-finais');

INSERT INTO tipo_produto (descricao)
VALUES ('bens de compra comparada - alguma comparação com outros bens como carros
e TVs. Ou seja, são produtos que exigem um alto esforço do consumidor para
comparar os requisitos e fazer uma escolha que atenda às suas necessidades.');

INSERT INTO tipo_produto (descricao)
VALUES ('abastecimento e serviços - bens que facilitam a produção');

INSERT INTO tipo_produto (descricao)
VALUES ('produtos intermediários - resulta da fabricação de outro produto');


