CREATE TABLE clientes (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100)
);

CREATE TABLE produtos (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(100),
    valor NUMERIC(20,2)
);

CREATE TABLE pedidos (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    cliente_id INTEGER REFERENCES clientes (id),
    data_pedido TIMESTAMP,
    total NUMERIC(20,2)
);


CREATE TABLE itens (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    produto_id INTEGER REFERENCES produtos (id),
    pedido_id INTEGER REFERENCES pedidos (id),
    quantidade INTEGER,
    valor_unitario NUMERIC(20,2),
    total NUMERIC(20,2)
);
