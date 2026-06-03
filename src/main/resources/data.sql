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

INSERT INTO produto (nome, preco, estoque, imagem)
VALUES ('Mouse Gamer', 120.0, 10, 'https://m.media-amazon.com/images/I/61C7blR0ZAL._AC_.jpg');

INSERT INTO produto (nome, preco, estoque, imagem)
VALUES ('Teclado Mecânico', 250.0, 5, 'https://cdn2.unrealengine.com/mechanical-keyboard-diagonal-4080x2295-d50ff434f19c.jpg');

INSERT INTO produto (nome, preco, estoque, imagem)
VALUES ('Monitor', 900.0, 3, 'https://assets.rockpapershotgun.com/images/2018/02/BenQ-EL2870U-face-on.jpg');

-- Endereços

INSERT INTO endereco (
    cep,
    numero,
    complemento,
    bairro,
    cidade,
    estado,
    usuario_id
)
VALUES
(
    '69100-000',
    '123',
    'Próximo à praça',
    'Centro',
    'Itacoatiara',
    'AM',
    1
);

INSERT INTO endereco (
    cep,
    numero,
    complemento,
    bairro,
    cidade,
    estado,
    usuario_id
)
VALUES
(
    '69050-010',
    '500',
    'Sala 202',
    'Chapada',
    'Manaus',
    'AM',
    1
);

INSERT INTO endereco (
    cep,
    numero,
    complemento,
    bairro,
    cidade,
    estado,
    usuario_id
)
VALUES
(
    '01310-100',
    '1500',
    'Apto 1203',
    'Bela Vista',
    'São Paulo',
    'SP',
    1
);