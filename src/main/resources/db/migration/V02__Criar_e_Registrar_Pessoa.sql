CREATE TABLE pessoa(
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    logradouro VARCHAR(50),
    numero VARCHAR(6),
    complemento VARCHAR(5),
    cep VARCHAR(12),
    cidade VARCHAR(30),
    estado VARCHAR(2),
    ativo BOOLEAN NOT NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, logradouro, numero, complemento, cep, cidade, estado, ativo) VALUE ('Claudinei Gomes Mendes', 'General Clark', '1735', 'CASA', '32.115-140', 'Contagem', 'MG', true);