-- 1. Пользователи
CREATE TABLE users (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    full_name VARCHAR(255) NOT NULL,
    role VARCHAR(32) NOT NULL, -- CUSTOMER, COURIER, ADMIN
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 2. Склады
CREATE TABLE warehouses (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(512) NOT NULL
);

-- 3. Поставщики
CREATE TABLE suppliers (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    contact_email VARCHAR(255)
);

-- 4. Товары
CREATE TABLE products (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    sku VARCHAR(64) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    price NUMERIC(12,2) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 5. Товары поставщиков (Many-to-Many с доп. полями)
CREATE TABLE supplier_products (
    supplier_id BIGINT NOT NULL REFERENCES suppliers(id),
    product_id BIGINT NOT NULL REFERENCES products(id),
    supply_price NUMERIC(12,2) NOT NULL,
    lead_time_days INT,
    PRIMARY KEY (supplier_id, product_id)
);

-- 6. Остатки на складе (Many-to-Many с доп. полями)
CREATE TABLE stock_items (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    product_id BIGINT NOT NULL REFERENCES products(id),
    warehouse_id BIGINT NOT NULL REFERENCES warehouses(id),
    quantity INT NOT NULL DEFAULT 0,
    reserved INT NOT NULL DEFAULT 0,
    UNIQUE (product_id, warehouse_id)
);

-- 7. Заказы
CREATE TABLE orders (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    customer_id BIGINT NOT NULL REFERENCES users(id),
    status VARCHAR(32) NOT NULL, -- CREATED, PAID, SHIPPED, DELIVERED, CANCELLED
    total_amount NUMERIC(12,2) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 8. Позиции заказа
CREATE TABLE order_items (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    order_id BIGINT NOT NULL REFERENCES orders(id),
    product_id BIGINT NOT NULL REFERENCES products(id),
    quantity INT NOT NULL,
    price_at_order NUMERIC(12,2) NOT NULL
);

-- 9. Доставки
CREATE TABLE shipments (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    order_id BIGINT NOT NULL UNIQUE REFERENCES orders(id), -- 1 к 1
    courier_id BIGINT REFERENCES users(id),
    status VARCHAR(32) NOT NULL, -- PENDING, IN_TRANSIT, DELIVERED, FAILED
    dispatched_at TIMESTAMP,
    delivered_at TIMESTAMP
);

-- 10. История движений товара
CREATE TABLE stock_movements (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    stock_item_id BIGINT NOT NULL REFERENCES stock_items(id),
    delta INT NOT NULL,
    reason VARCHAR(64) NOT NULL, -- ORDER_RESERVE, ORDER_SHIP, SUPPLY, CANCEL, RETURN
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);