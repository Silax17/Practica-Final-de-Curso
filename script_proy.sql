CREATE TABLE `Sala` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `nombre` varchar(50),
  `capacidad` integer,
  `recursos_disponibles` varchar(200)
);

CREATE TABLE `Empleado` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `nombre` varchar(50),
  `email` varchar(100) UNIQUE,
  `departamento` varchar(100)
);

CREATE TABLE `Reserva` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `empleado_id` int,
  `sala_id` int,
  `fecha` date,
  `hora_inicio` time,
  `hora_final` time,
  FOREIGN KEY (empleado_id) REFERENCES Empleado(id),
  FOREIGN KEY (sala_id) REFERENCES Sala(id)
);

INSERT INTO Sala (nombre,capacidad,recursos_disponibles)VALUES 
  ('Sala 1',34,'Pantalla, Pizarra'),
  ('Sala 2', 10,'Pizarra'),
  ('Sala 3', 50,'Pantalla, Pizarra, Tablero');

INSERT INTO Empleado (nombre,email,departamento)VALUES 
  ('Paco Gómez','pago@gmail.com','Logística'),
  ('Maria Novales','novalesmaria22@gmail.com','Recursos Humanos'),
  ('Victor Martín','martinvic01@gmail.com','Comercial');

INSERT INTO Reserva(empleado_id,sala_id,fecha,hora_inicio,hora_final)VALUES 
  (1,1,'2025-06-24','08:00:00','10:00:00'),
  (2,2,'2025-06-25','10:00:00','12:00:00'),
  (3,1,'2025-06-24','09:00:00','11:00:00');


