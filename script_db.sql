-- Creación de la base de datos
CREATE DATABASE IF NOT EXISTS `proyecto_INSOII`;
USE `proyecto_INSOII`;

-- Creación de tablas
CREATE TABLE `personas` (
  `Person_id` INT(11) NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(75) NOT NULL,
  `Apellidos` VARCHAR(100) NOT NULL,
  `FechaNacimiento` DATETIME NOT NULL,
  `Sexo` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`Person_id`)
);

INSERT INTO `personas` (`Nombre`, `Apellidos`, `FechaNacimiento`, `Sexo`) VALUES
('Maria', 'Fernandez Rodriguez', '1990-01-01', 'F'),
('Antonio', 'García Rodríguez', '1995-05-10', 'M'),
('Gabriel', 'García Pérez', '1998-12-15', 'M');

CREATE TABLE `roles` (
  `IdRol` INT(11) NOT NULL AUTO_INCREMENT,
  `TipoUsuario` VARCHAR(20) NOT NULL,
  `Descripcion` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`IdRol`)
);

INSERT INTO `roles` (`IdRol`, `TipoUsuario`, `Descripcion`) VALUES
(1, 'E', 'Entrenador'),
(2, 'P', 'Participante'),
(3, 'A', 'Admin');

CREATE TABLE `usuarios` (
  `User_id` INT(11) NOT NULL AUTO_INCREMENT,
  `Username` CHAR(20) NOT NULL,
  `Password` VARCHAR(20) NOT NULL,
  `UltimaConexion` DATETIME NULL,
  `Person_id` INT(11) NOT NULL,
  `IdRol` INT(11) NOT NULL,
  PRIMARY KEY (`User_id`),
  FOREIGN KEY (`Person_id`) REFERENCES `personas`(`Person_id`),
  FOREIGN KEY (`IdRol`) REFERENCES `roles`(`IdRol`)
);

/* INSERTS */
INSERT INTO `usuarios` (`User_id`, `Username`, `Password`, `UltimaConexion`,`Person_id`, `IdRol`) VALUES
(1, 'Profesor', 'Profesor', NULL, 3, 1),
(2, 'Alumno', 'Alumno', '2018-01-27 00:03:56', 4, 2),
(3, 'Admin', 'Admin', '2018-01-31 17:34:43', 5, 3);

CREATE TABLE `instalaciones` (
  `IdInstalacion` INT(11) NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(50) NOT NULL,
  `Descripcion` VARCHAR(100) NOT NULL,
  `Tipo` VARCHAR(20) NOT NULL,
  `Aforo_min` INT(10) NULL,
  `Aforo_max` INT(10) NOT NULL,
  PRIMARY KEY (`IdInstalacion`)
);

CREATE TABLE `sesiones` (
  `IdSesion` INT(11) NOT NULL AUTO_INCREMENT,
  `Titulo` VARCHAR(100) NOT NULL,
  `Cuerpo` VARCHAR(1000) NOT NULL,
  `Fecha` DATETIME NOT NULL,
  `Valoracion_media` DECIMAL(10,2) NULL,
  `Comentarios` VARCHAR(100) NULL,
  `IdInstalacion` INT(11) NOT NULL,
  PRIMARY KEY (`IdSesion`),
  FOREIGN KEY (`IdInstalacion`) REFERENCES `instalaciones`(`IdInstalacion`)
);

CREATE TABLE `usuarios_sesiones` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `IdSesion` INT(11) NOT NULL,
  `UserId` INT(11) NOT NULL,
  `Valoracion` INT(10) NULL,
  `Comentarios` VARCHAR(100) NULL,
  `Fecha_hora` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`),
  FOREIGN KEY (`IdSesion`) REFERENCES `sesiones`(`IdSesion`),
  FOREIGN KEY (`UserId`) REFERENCES `usuarios`(`User_id`)
);

CREATE TABLE `menus` (
  `IdMenu` INT(11) NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(50) NOT NULL,
  `Fecha_hora` TIMESTAMP(0) NOT NULL,
  `Estado` BIT(1) NOT NULL,
  `Url` VARCHAR(100) NOT NULL,
  `IdRol` INT(11) NOT NULL,
  PRIMARY KEY (`IdMenu`),
  FOREIGN KEY (`IdRol`) REFERENCES `roles`(`IdRol`)
);

INSERT INTO `menus` (`IdMenu`, `Nombre`, `Tipo`, `Estado`, `IdRol`, `IdMenu_Menu`, `url`) VALUES
	(1, 'Usuario', 'S', b'1', 3, NULL, NULL),
	(2, 'Nuevo', 'I', b'1', 3, 1, '/privado/administrador/AltaUsuario.softwareII'),
	(3, 'Modificar', 'I', b'1', 3, 1, '/privado/administrador/ModificarUsuario.softwareII'),
	(4, 'Eliminar', 'I', b'1', 3, 1, '/privado/administrador/EliminarUsuario.softwareII'),
	(5, 'Roles', 'S', b'1', 3, NULL, NULL),
	(6, 'Nuevo', 'I', b'1', 3, 5, '/privado/administrador/AltaRol.softwareII'),
	(7, 'Modificar', 'I', b'1', 3, 5, '/privado/administrador/ModificarRol.softwareII'),
	(8, 'Eliminar', 'I', b'1', 3, 5, '/privado/administrador/EliminarRol.softwareII'),
	(9, 'Publicación', 'S', b'1', 2, NULL, NULL),
	(10, 'Nueva', 'I', b'1', 2, 9, '/privado//alumno/AltaPublicacion.softwareII'),
	(11, 'Modificar', 'I', b'1', 2, 9, '/privado/alumno/ModificarPublicacion.softwareII'),
	(12, 'Eliminar', 'I', b'1', 2, 9, '/privado//alumno/EliminarPublicacion.softwareII'),
	(13, 'Listar sesiones', 'S', b'1', 2, NULL, ''),
	(14, 'sesiones', 'S', b'1', 1, NULL, NULL),
	(15, 'Leer', 'I', b'1', 1, 14, '/privado/profesor/ListarYFiltrarsesiones.softwareII'),
	(16, 'Eliminar', 'I', b'1', 1, 14, NULL),
	(17, 'Comentar', 'I', b'1', 1, NULL, '/privado/profesor/ComentarPublicacion.softwareII'),
	(18, 'Valorar', 'I', b'1', 1, NULL, NULL),
	(22, 'instalaciones', 'S', b'1', 3, NULL, NULL),
	(23, 'Nueva', 'I', b'1', 3, 22, '/privado/administrador/AltaCategoria.softwareII'),
	(24, 'Modificar', 'I', b'1', 3, 22, '/privado/administrador/ModificarCategoria.softwareII'),
	(25, 'Eliminar', 'I', b'1', 3, 22, '/privado/administrador/EliminarCategoria.softwareII'),
	(26, 'Por Categoria', 'I', b'1', 2, 13, '/privado/alumno/ListarsesionesPorCategoria.softwareII'),
	(27, 'Por Valoración', 'I', b'1', 2, 13, '/privado/alumno/ListarsesionesPorValoracion.softwareII');


CREATE TABLE `instalaciones_sesiones` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `idSesion` INT(11) NOT NULL,
  `idUser` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`idSesion`) REFERENCES `sesiones`(`IdSesion`),
  FOREIGN KEY (`idUser`) REFERENCES `usuarios`(`User_id`)
);

