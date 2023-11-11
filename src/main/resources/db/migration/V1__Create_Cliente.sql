CREATE TABLE IF NOT EXISTS clientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11),
    cnpj VARCHAR(14),
    rua VARCHAR(255) NOT NULL,
    numero INT NOT NULL,
    cep VARCHAR(8) NOT NULL
);

INSERT INTO clientes (nome, cpf, rua, numero, cep)
VALUES ('Joazinho', '12345678901', 'Rua do Cliente', 123, '12345678');

INSERT INTO clientes (nome, cnpj, rua, numero, cep)
VALUES ('Mariazinha', '12345678901234', 'Rua do Cliente', 123, '12345678');

INSERT INTO clientes (nome, cnpj, rua, numero, cep)
VALUES ('Pedrinho', '12345678901234', 'Rua do Cliente', 123, '12345678');

INSERT INTO clientes (nome, cpf, rua, numero, cep)
VALUES ('Rodriguinho', '12345678901', 'Rua do Cliente', 123, '12345678');
