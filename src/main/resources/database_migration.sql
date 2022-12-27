CREATE TABLE customers (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    cpf varchar(11)
);

CREATE TABLE products (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(100),
    `value` NUMERIC(20,2)
);

CREATE TABLE sales (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    customer_id INTEGER REFERENCES customers (id),
    created TIMESTAMP,
    total NUMERIC(20,2),
    status varchar(20)
);


CREATE TABLE items (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    product_id INTEGER REFERENCES products (id),
    sale_id INTEGER REFERENCES sales (id),
    amount INTEGER,
    unitary_value NUMERIC(20,2),
    total NUMERIC(20,2)
);
