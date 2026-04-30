-- Datos de prueba para el taller Sesión 8
-- Suficientes registros para que el problema N+1 sea evidente

-- Clientes
INSERT INTO clientes (id, nombre, email) VALUES (1, 'Andrea Ramírez', 'andrea@ejemplo.com');
INSERT INTO clientes (id, nombre, email) VALUES (2, 'Carlos Méndez', 'carlos@ejemplo.com');
INSERT INTO clientes (id, nombre, email) VALUES (3, 'Diana Torres', 'diana@ejemplo.com');
INSERT INTO clientes (id, nombre, email) VALUES (4, 'Esteban Rojas', 'esteban@ejemplo.com');
INSERT INTO clientes (id, nombre, email) VALUES (5, 'Fernanda Silva', 'fernanda@ejemplo.com');

-- Pedidos (varios por cliente para evidenciar N+1)
INSERT INTO pedidos (id, numero, fecha_creacion, total, cliente_id, numero_tarjeta, observaciones) VALUES
(1, 'PED-001', '2026-04-01 10:00:00', 150000, 1, '4532-1234-5678-9012', 'Cliente frecuente'),
(2, 'PED-002', '2026-04-02 11:30:00', 89000, 1, '4532-1234-5678-9012', 'Entrega urgente'),
(3, 'PED-003', '2026-04-03 09:15:00', 220000, 1, '4532-1234-5678-9012', NULL),
(4, 'PED-004', '2026-04-04 14:20:00', 75000, 2, '5412-9876-5432-1098', NULL),
(5, 'PED-005', '2026-04-05 16:45:00', 340000, 2, '5412-9876-5432-1098', 'Pago en efectivo'),
(6, 'PED-006', '2026-04-06 08:30:00', 56000, 3, '4916-1111-2222-3333', NULL),
(7, 'PED-007', '2026-04-07 13:10:00', 195000, 3, '4916-1111-2222-3333', 'Direccion alterna'),
(8, 'PED-008', '2026-04-08 17:00:00', 410000, 3, '4916-1111-2222-3333', NULL),
(9, 'PED-009', '2026-04-09 10:30:00', 128000, 4, '4716-4321-8765-4321', NULL),
(10, 'PED-010', '2026-04-10 12:00:00', 67000, 5, '5234-5678-9012-3456', 'Primera compra');

-- Líneas de pedido (varias por pedido para hacer aún más visible el N+1)
INSERT INTO lineas_pedido (id, descripcion, cantidad, precio_unitario, pedido_id) VALUES
(1, 'Teclado mecánico', 2, 75000, 1),
(2, 'Mouse ergonómico', 1, 45000, 1),
(3, 'Monitor 24 pulgadas', 1, 89000, 2),
(4, 'Webcam HD', 1, 120000, 3),
(5, 'Audífonos bluetooth', 1, 100000, 3),
(6, 'Hub USB-C', 1, 75000, 4),
(7, 'Disco SSD 1TB', 1, 240000, 5),
(8, 'Memoria RAM 16GB', 2, 50000, 5),
(9, 'Cable HDMI', 2, 28000, 6),
(10, 'Soporte para laptop', 1, 65000, 7),
(11, 'Lámpara de escritorio', 1, 130000, 7),
(12, 'Silla ergonómica', 1, 410000, 8),
(13, 'Mousepad XL', 1, 35000, 9),
(14, 'Cargador rápido', 1, 93000, 9),
(15, 'Estuche para portátil', 1, 67000, 10);
