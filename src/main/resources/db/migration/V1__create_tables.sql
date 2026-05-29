CREATE TABLE pessoa (
    id_pessoa INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf CHAR(11) NOT NULL,
    cep CHAR(9) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL
);

CREATE TABLE livro (
    id_livro INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    data_lancamento DATE NOT NULL
);

-- Tabela intermediária para a relação M:N (Empréstimo)
CREATE TABLE emprestimo (
    id_pessoa INT,
    id_livro INT,
    PRIMARY KEY (id_pessoa, id_livro),
    FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa) ON DELETE CASCADE,
    FOREIGN KEY (id_livro) REFERENCES livro(id_livro) ON DELETE CASCADE
);