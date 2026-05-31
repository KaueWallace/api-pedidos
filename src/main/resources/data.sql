-- USUÁRIOS

INSERT INTO usuario (nome, email, senha, role)
VALUES
(
    'Administrador',
    'admin2@email.com',
    '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',
    'ADMIN'
);

-- PRODUTOS

INSERT INTO produto (nome, preco, estoque)
VALUES ('Mouse Gamer', 120.0, 10);

INSERT INTO produto (nome, preco, estoque)
VALUES ('Teclado Mecânico', 250.0, 5);

INSERT INTO produto (nome, preco, estoque)
VALUES ('Monitor', 900.0, 3);