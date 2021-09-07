INSERT INTO user (username, password, rol, enabled) VALUES
  ('cliente', '$2a$10$.Boj0h1UiN2qosirdYdNyuNlShb/wv1jZLDZmySVI2ByGa5Y9vMzq', 'ROLE_CLIENTE', 1),
  ('empleado', '$2a$10$.Boj0h1UiN2qosirdYdNyuNlShb/wv1jZLDZmySVI2ByGa5Y9vMzq', 'ROLE_EMPLEADO', 1);
  
INSERT INTO item (id, codigo, tipo, descripcion) VALUES
  (1, 'CAM_0001', 'Camiseta', 'Camiseta Marvel'),
  (2, 'CAM_0002', 'Camiseta', 'Camiseta DC comics'),
  (3, 'VAS_0001', 'Vaso', 'Camiseta Marvel'),
  (4, 'VAS_0002', 'Vaso', 'Camiseta DC comics');
  
INSERT INTO stock_ejercicio (id, item_id, year, valor_unitario, cantidad) VALUES
  (1, 1, '2021', 94, 98);
  
INSERT INTO movimiento (id, stock_ejercicio_id, tipo, fecha, valor_unitario, cantidad) VALUES
  (1, 1, 'SALDO_INICIAL', '2021-01-21', 94, 98);