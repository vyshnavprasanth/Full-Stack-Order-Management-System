CREATE TABLE customer (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE orders (
    order_id BIGINT PRIMARY KEY,
    order_name VARCHAR(255),
    delivery_date DATE,
    delivery_pricing DECIMAL(10,2),
    delivery_status VARCHAR(100),
    customer_id BIGINT,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);

---- Insert a customer
INSERT INTO customer (id, name)
VALUES (1,'Dracula'),
 (2,'Jamy'),
 (3,'James'),
 (4,'George'),
 (5,'DJ');

-- Insert orders for the above customer
INSERT INTO orders (order_id ,order_name, delivery_date, delivery_pricing, delivery_status, customer_id)
VALUES
(1001,'Paper ball','2025-10-05', 299.99, 'Shipped', 1),
(1002,'Paper bat' ,'2025-10-05', 149.49, 'Completed', 2),
(1003,'Paper ball','2025-10-05', 299.99, 'Completed', 3),
(1004,'Paper ball','2025-10-05', 299.99, 'Shipped', 4);