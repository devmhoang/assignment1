-- Insert 3 users (1 admin, 2 customers)
INSERT INTO demo.users (id, username, password, role) VALUES
('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'admin_user', '$2a$10$TBaflXvIFYDiUFmyN6dyM.OYhIADjykZDz4KTjV8rk.JiY03m7Uhe', 'admin'), -- DEFAULT PASSWORD: abc123
('e4eebc99-9c0b-4ef8-bb6d-6bb9bd380a15', 'sarah_jones', '$2a$10$TBaflXvIFYDiUFmyN6dyM.OYhIADjykZDz4KTjV8rk.JiY03m7Uhe', 'customer'),
('f5eebc99-9c0b-4ef8-bb6d-6bb9bd380a16', 'david_brown', '$2a$10$TBaflXvIFYDiUFmyN6dyM.OYhIADjykZDz4KTjV8rk.JiY03m7Uhe', 'customer');

-- Insert 10 products
INSERT INTO demo.products (id, product_name, product_desc, product_price, product_stock) VALUES
(1, 'Wireless Mouse', 'sampleDesc1', 25000, 10),
(2, 'USB-C Cable', 'sampleDesc2', 12000, 10),
(3, 'Desk Lamp', 'sampleDesc3', 35000, 10),
(4, 'Notebook Set', 'sampleDesc4', 15000, 10),
(5, 'Water Bottle', 'sampleDesc5', 28000, 10),
(6, 'Phone Stand', 'sampleDesc6', 18000, 10),
(7, 'Bluetooth Speaker', 'sampleDesc7', 45000, 4),
(8, 'Mechanical Pencil', 'sampleDesc8', 8000, 10),
(9, 'Laptop Sleeve', 'sampleDesc9', 22000, 10),
(10, 'Desk Organizer', 'sampleDesc10', 32000, 10);
-- Reset the sequence to the maximum existing ID + 1
SELECT setval('demo.products_id_seq', (SELECT COALESCE(MAX(id), 0) FROM demo.products) + 1, false);

-- Insert 10 orders
INSERT INTO demo.orders (id, order_items, order_status, payment_total, customer_id, order_detail) VALUES
('a1eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 
 '[{"id":1, "itemname":"Wireless Mouse", "quantity":2, "subtotal":50000}, {"id":3, "itemname":"Desk Lamp", "quantity":1, "subtotal":35000}]'::jsonb, 
 'delivered', 85000, 'e4eebc99-9c0b-4ef8-bb6d-6bb9bd380a15', 'Express shipping requested'),

('a2eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 
 '[{"id":2, "itemname":"USB-C Cable", "quantity":3, "subtotal":36000}, {"id":5, "itemname":"Water Bottle", "quantity":2, "subtotal":56000}, {"id":8, "itemname":"Mechanical Pencil", "quantity":5, "subtotal":40000}]'::jsonb, 
 'completed', 132000, 'f5eebc99-9c0b-4ef8-bb6d-6bb9bd380a16', 'Standard delivery'),

('a3eebc99-9c0b-4ef8-bb6d-6bb9bd380a13', 
 '[{"id":7, "itemname":"Bluetooth Speaker", "quantity":1, "subtotal":45000}, {"id":9, "itemname":"Laptop Sleeve", "quantity":2, "subtotal":44000}]'::jsonb, 
 'shipping', 89000, 'e4eebc99-9c0b-4ef8-bb6d-6bb9bd380a15', 'Gift wrap requested'),

('a4eebc99-9c0b-4ef8-bb6d-6bb9bd380a14', 
 '[{"id":4, "itemname":"Notebook Set", "quantity":4, "subtotal":60000}, {"id":6, "itemname":"Phone Stand", "quantity":3, "subtotal":54000}, {"id":10, "itemname":"Desk Organizer", "quantity":1, "subtotal":32000}]'::jsonb, 
 'confirmed', 146000, 'f5eebc99-9c0b-4ef8-bb6d-6bb9bd380a16', 'Leave at door'),

('a5eebc99-9c0b-4ef8-bb6d-6bb9bd380a15', 
 '[{"id":1, "itemname":"Wireless Mouse", "quantity":5, "subtotal":125000}, {"id":2, "itemname":"USB-C Cable", "quantity":10, "subtotal":120000}]'::jsonb, 
 'pending', 245000, 'e4eebc99-9c0b-4ef8-bb6d-6bb9bd380a15', 'Waiting for payment confirmation');