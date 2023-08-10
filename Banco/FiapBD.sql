DROP TABLE java_proprietario;
COMMIT;

CREATE TABLE java_proprietario (
    id INTEGER,
    nome VARCHAR2(50),
    cpf INTEGER,
    PRIMARY KEY(id)
)

SELECT * FROM java_proprietario;

insert into java_proprietario(id, nome, cpf) VALUES(001, 'Francisco', 12345678901);

