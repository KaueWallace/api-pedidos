INSERT INTO produto (nome, preco, estoque)
VALUES ('Mouse Gamer', 120.0, 10);

INSERT INTO produto (nome, preco, estoque)
VALUES ('Teclado Mecânico', 250.0, 5);

INSERT INTO produto (nome, preco, estoque)
VALUES ('Monitor', 900.0, 3);


INSERT INTO pedido (data_pedido, valor_total)
VALUES ('2026-05-26T10:30:00', 120.0);

INSERT INTO pedido (data_pedido, valor_total)
VALUES ('2026-05-26T11:15:00', 240.0);

INSERT INTO pedido (data_pedido, valor_total)
VALUES ('2026-05-26T14:45:00', 370.0);

INSERT INTO pedido (data_pedido, valor_total)
VALUES ('2026-05-26T16:20:00', 120.0);

INSERT INTO pedido (data_pedido, valor_total)
VALUES ('2026-05-26T18:00:00', 370.0);


INSERT INTO item_pedido (
    pedido_id,
    produto_id,
    quantidade,
    preco_unitario,
    sub_total
) VALUES

(1, 1, 1, 120.0, 120.0),

(2, 1, 2, 120.0, 240.0),

(3, 1, 1, 120.0, 120.0),
(3, 2, 1, 250.0, 250.0),

(4, 1, 1, 120.0, 120.0),

(5, 1, 1, 120.0, 120.0),
(5, 2, 1, 250.0, 250.0);