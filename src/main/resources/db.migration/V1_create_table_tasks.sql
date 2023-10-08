CREATE TABLE task_entity
(
     id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     titulo VARCHAR(255) NOT NULL,
     descricao VARCHAR(255) NOT NULL,
     data DATE NOT NULL,
     prioridade VARCHAR(255) NOT NULL
);
