CREATE TABLE customers
(
    id         VARCHAR(36) PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    email      VARCHAR(100) NOT NULL UNIQUE,
    active     BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP    NOT NULL DEFAULT NOW()
);

CREATE TABLE products
(
    id          VARCHAR(36) PRIMARY KEY,
    name        VARCHAR(100)   NOT NULL,
    description VARCHAR(255),
    price       DECIMAL(10, 2) NOT NULL,
    stock       INTEGER        NOT NULL DEFAULT 0,
    active      BOOLEAN        NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMP      NOT NULL DEFAULT NOW()
);

CREATE TABLE orders
(
    id          VARCHAR(36) PRIMARY KEY,
    customer_id VARCHAR(36)    NOT NULL REFERENCES customers (id),
    status      VARCHAR(20)    NOT NULL DEFAULT 'PENDING',
    total       DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    created_at  TIMESTAMP      NOT NULL DEFAULT NOW()
);

CREATE TABLE order_items
(
    id         VARCHAR(36) PRIMARY KEY,
    order_id   VARCHAR(36)    NOT NULL REFERENCES orders (id),
    product_id VARCHAR(36)    NOT NULL REFERENCES products (id),
    quantity   INTEGER        NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    subtotal   DECIMAL(10, 2) NOT NULL
);

CREATE TABLE transactions
(
    id         VARCHAR(36) PRIMARY KEY,
    order_id   VARCHAR(36)    NOT NULL REFERENCES orders (id),
    amount     DECIMAL(10, 2) NOT NULL,
    status     VARCHAR(20)    NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMP      NOT NULL DEFAULT NOW()
);