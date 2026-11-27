DROP DATABASE IF EXISTS alquilervideojuegos;

CREATE DATABASE alquilervideojuegos;
USE alquilervideojuegos;

-- Tabla de categorías de videojuegos
DROP TABLE IF EXISTS categoria;
CREATE TABLE categoria (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nombre TEXT
);

-- Tabla de videojuegos
DROP TABLE IF EXISTS videojuego;
CREATE TABLE videojuego (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    titulo TEXT,
    plataforma TEXT,
    desarrolladora TEXT,
    categoria INTEGER,
    FOREIGN KEY(categoria) REFERENCES categoria(id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabla de socios
DROP TABLE IF EXISTS socio;
CREATE TABLE socio (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nombre TEXT,
    apellidos TEXT
);

-- Tabla de alquileres
CREATE TABLE alquiler (
    idAlquiler INTEGER PRIMARY KEY AUTO_INCREMENT,
    idVideojuego INTEGER,
    idsocio INTEGER,
    fechaAlquiler DATETIME,
    FOREIGN KEY(idVideojuego) REFERENCES videojuego(id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(idsocio) REFERENCES socio(id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- Inserción de categorías de videojuegos
INSERT INTO categoria VALUES (1, 'Acción');
INSERT INTO categoria VALUES (2, 'Aventura');
INSERT INTO categoria VALUES (3, 'Deportes');
INSERT INTO categoria VALUES (4, 'Estrategia');
INSERT INTO categoria VALUES (5, 'Rol (RPG)');
INSERT INTO categoria VALUES (6, 'Simulación');
INSERT INTO categoria VALUES (7, 'Carreras');
INSERT INTO categoria VALUES (8, 'Puzzle');
INSERT INTO categoria VALUES (9, 'Terror');

-- Inserción de videojuegos
INSERT INTO videojuego VALUES (1, 'The Legend of Zelda: Tears of the Kingdom', 'Nintendo Switch', 'Nintendo', 2);
INSERT INTO videojuego VALUES (2, 'EA Sports FC 25', 'PlayStation 5', 'EA Sports', 3);
INSERT INTO videojuego VALUES (3, 'Minecraft', 'PC', 'Mojang Studios', 6);
INSERT INTO videojuego VALUES (4, 'Baldur''s Gate 3', 'PC', 'Larian Studios', 5);
INSERT INTO videojuego VALUES (5, 'Age of Empires IV', 'PC', 'World''s Edge', 4);
INSERT INTO videojuego VALUES (6, 'Gran Turismo 7', 'PlayStation 5', 'Polyphony Digital', 7);
INSERT INTO videojuego VALUES (7, 'Resident Evil 4', 'PlayStation 5', 'Capcom', 9);
INSERT INTO videojuego VALUES (8, 'Super Mario Bros. Wonder', 'Nintendo Switch', 'Nintendo', 8);
INSERT INTO videojuego VALUES (9, 'God of War Ragnarök', 'PlayStation 5', 'Santa Monica Studio', 1);
INSERT INTO videojuego VALUES (10, 'Forza Horizon 5', 'Xbox Series X', 'Playground Games', 7);

-- Inserción de socios
INSERT INTO socio VALUES (1, 'Álvaro', 'Aguilar');
INSERT INTO socio VALUES (2, 'Beatriz', 'Blanco');
INSERT INTO socio VALUES (3, 'Cristian', 'Carmona');
INSERT INTO socio VALUES (4, 'Daniel', 'Delgado');
INSERT INTO socio VALUES (5, 'Elena', 'Espinosa');

-- Inserción de alquileres
INSERT INTO alquiler VALUES (1, 3, 2, '2026-10-17 10:15:00');
INSERT INTO alquiler VALUES (2, 6, 4, '2026-10-20 09:00:00');
INSERT INTO alquiler VALUES (3, 9, 5, '2026-10-25 14:30:00');

-- Creación de usuario y asignación de permisos
DROP USER IF EXISTS 'alquilervideojuegos'@'localhost';
DROP USER IF EXISTS 'alquilervideojuegos'@'%';

CREATE USER 'alquilervideojuegos'@'localhost' IDENTIFIED BY 'rent25';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, EXECUTE
    ON alquilervideojuegos.* TO 'alquilervideojuegos'@'localhost';

CREATE USER 'alquilervideojuegos'@'%' IDENTIFIED BY 'rent25';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, EXECUTE
    ON alquilervideojuegos.* TO 'alquilervideojuegos'@'%';

FLUSH PRIVILEGES;
