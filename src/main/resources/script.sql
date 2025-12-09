CREATE DATABASE IF NOT EXISTS loginjavafx;
USE loginjavafx;

-- Tabla 1: Usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,  -- CAMBIO: De correo a username
    password VARCHAR(100) NOT NULL,
    nombre_completo VARCHAR(100)           -- Para mostrar "Hola, Pepe Perez"
);

-- Tabla 2: Tareas (Relación 1:N con Usuarios)
CREATE TABLE IF NOT EXISTS tareas (
    id_tarea INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descripcion TEXT,
    prioridad VARCHAR(20),
    progreso INT DEFAULT 0,
    completada BOOLEAN DEFAULT FALSE,
    fecha_limite DATE,
    id_usuario INT,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
    );

-- Tabla 3: Etiquetas (Para cumplir requisito N:M)
CREATE TABLE IF NOT EXISTS etiquetas (
    id_etiqueta INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50)
    );

-- Tabla 4: Relación N:M
CREATE TABLE IF NOT EXISTS tareas_etiquetas (
    id_tarea INT,
    id_etiqueta INT,
    PRIMARY KEY (id_tarea, id_etiqueta),
    FOREIGN KEY (id_tarea) REFERENCES tareas(id_tarea),
    FOREIGN KEY (id_etiqueta) REFERENCES etiquetas(id_etiqueta)
    );

-- DATOS DE PRUEBA (Para poder hacer login)
INSERT INTO usuarios (username, password, nombre_completo) VALUES ('admin', '1234', 'Administrador del Sistema');
INSERT INTO usuarios (username, password, nombre_completo) VALUES ('pepe', '1234', 'Pepe García');