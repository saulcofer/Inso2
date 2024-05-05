-- Creación de la base de datos
CREATE DATABASE IF NOT EXISTS `proyecto_INSOII`;
USE `proyecto_INSOII`;

-- Creación de tablas
CREATE TABLE IF NOT EXISTS `personas` (
  `IdPerson` INT(11) NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(75) NOT NULL,
  `Apellidos` VARCHAR(100) NOT NULL,
  `FechaNacimiento` DATETIME NOT NULL,
  `Sexo` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`IdPerson`)
);

INSERT INTO `personas` (`Nombre`, `Apellidos`, `FechaNacimiento`, `Sexo`) VALUES
('Maria', 'Fernandez Rodriguez', '1990-01-01', 'F'),
('Antonio', 'García Rodríguez', '1995-05-10', 'M'),
('Gabriel', 'García Pérez', '1998-12-15', 'M');

CREATE TABLE IF NOT EXISTS `roles` (
  `IdRol` INT(11) NOT NULL AUTO_INCREMENT,
  `TipoUsuario` VARCHAR(20) NOT NULL,
  `Descripcion` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`IdRol`)
);

INSERT INTO `roles` (`IdRol`, `TipoUsuario`, `Descripcion`) VALUES
(1, 'E', 'Entrenador'),
(2, 'P', 'Participante'),
(3, 'A', 'Admin');

CREATE TABLE IF NOT EXISTS `usuarios` (
  `IdUser` INT(11) NOT NULL AUTO_INCREMENT,
  `Username` CHAR(20) NOT NULL,
  `Password` VARCHAR(20) NOT NULL,
  `UltimaConexion` DATETIME NULL,
  `IdPerson` INT(11) NOT NULL,
  `IdRol` INT(11) NOT NULL,
  PRIMARY KEY (`IdUser`),
  FOREIGN KEY (`IdPerson`) REFERENCES `personas`(`IdPerson`),
  FOREIGN KEY (`IdRol`) REFERENCES `roles`(`IdRol`)
);

/* INSERTS */
INSERT INTO `usuarios` (`IdUser`, `Username`, `Password`, `UltimaConexion`,`IdPerson`, `IdRol`) VALUES
(1, 'Profesor', 'Profesor', NULL, 1, 1),
(2, 'Alumno', 'Alumno', '2018-01-27 00:03:56', 2, 2),
(3, 'Admin', 'Admin', '2018-01-31 17:34:43', 3, 3);

CREATE TABLE IF NOT EXISTS `instalaciones` (
  `IdInstalacion` INT(11) NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(50) NOT NULL,
  `Descripcion` VARCHAR(100) NOT NULL,
  `Tipo` VARCHAR(20) NOT NULL,
  `Aforo_min` INT(10) NULL,
  `Aforo_max` INT(10) NOT NULL,
  PRIMARY KEY (`IdInstalacion`)
);

CREATE TABLE IF NOT EXISTS `sesiones` (
  `IdSesion` INT(11) NOT NULL AUTO_INCREMENT,
  `Titulo` VARCHAR(100) NOT NULL,
  `Cuerpo` VARCHAR(1000) NOT NULL,
  `Fecha` DATETIME NOT NULL,
  `Valoracion_media` DECIMAL(10,2) NULL,
  `Comentarios` VARCHAR(100) NULL,
  PRIMARY KEY (`IdSesion`)
);

CREATE TABLE IF NOT EXISTS `usuarios_sesiones` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `IdSesion` INT(11) NOT NULL,
  `IdUser` INT(11) NOT NULL,
  `Valoracion` INT(10) NULL,
  `Comentarios` VARCHAR(100) NULL,
  `Fecha_hora` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`),
  FOREIGN KEY (`IdSesion`) REFERENCES `sesiones`(`IdSesion`),
  FOREIGN KEY (`IdUser`) REFERENCES `usuarios`(`IdUser`)
);

CREATE TABLE IF NOT EXISTS `menus` (
  `IdMenu` INT(11) NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(50) NOT NULL,
  `Tipo` VARCHAR(1) NOT NULL,
  `IdRol` INT(11) NOT NULL,
  `IdMenu_Menu` INT(11) DEFAULT NULL,
  `Url` VARCHAR(100) DEFAULT NULL,
  PRIMARY KEY (`IdMenu`),
  KEY `FK_Menu_Rol` (`IdRol`),
  KEY `Fk_Menu_menu` (`IdMenu_Menu`),
  CONSTRAINT `FK_Menu_Rol` FOREIGN KEY (`IdRol`) REFERENCES `roles` (`IdRol`),
  CONSTRAINT `Fk_Menu_menu` FOREIGN KEY (`IdMenu_Menu`) REFERENCES `menus` (`IdMenu`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

INSERT INTO `menus` (`IdMenu`, `Nombre`, `Tipo`, `IdRol`, `IdMenu_Menu`, `Url`) VALUES
	(1, 'Usuario', 'S', 3, NULL, NULL),
	(2, 'Nuevo', 'I', 3, 1, '/privado/administrador/AltaUsuario.softwareII'),
	(3, 'Modificar', 'I', 3, 1, '/privado/administrador/ModificarUsuario.softwareII'),
	(4, 'Eliminar', 'I', 3, 1, '/privado/administrador/EliminarUsuario.softwareII'),
	(5, 'Roles', 'S', 3, NULL, NULL),
	(6, 'Nuevo', 'I', 3, 5, '/privado/administrador/AltaRol.softwareII'),
	(7, 'Modificar', 'I', 3, 5, '/privado/administrador/ModificarRol.softwareII'),
	(8, 'Eliminar', 'I', 3, 5, '/privado/administrador/EliminarRol.softwareII'),
	(9, 'Publicación', 'S', 2, NULL, NULL),
	(10, 'Nueva', 'I', 2, 9, '/privado//alumno/AltaPublicacion.softwareII'),
	(11, 'Modificar', 'I', 2, 9, '/privado/alumno/ModificarPublicacion.softwareII'),
	(12, 'Eliminar', 'I', 2, 9, '/privado//alumno/EliminarPublicacion.softwareII'),
	(13, 'Listar sesiones', 'S', 2, NULL, ''),
	(14, 'Sesiones', 'S', 1, NULL, NULL),
	(15, 'Leer', 'I', 1, 14, '/privado/profesor/ListarYFiltrarsesiones.softwareII'),
	(16, 'Eliminar', 'I', 1, 14, NULL),
	(17, 'Comentar', 'I', 1, NULL, '/privado/profesor/ComentarPublicacion.softwareII'),
	(18, 'Valorar', 'I', 1, NULL, NULL),
	(22, 'Instalaciones', 'S', 3, NULL, NULL),
	(23, 'Nueva', 'I', 3, 22, '/privado/administrador/AltaCategoria.softwareII'),
	(24, 'Modificar', 'I', 3, 22, '/privado/administrador/ModificarCategoria.softwareII'),
	(25, 'Eliminar', 'I', 3, 22, '/privado/administrador/EliminarCategoria.softwareII'),
	(26, 'Por Categoria', 'I', 2, 13, '/privado/alumno/ListarsesionesPorCategoria.softwareII'),
	(27, 'Por Valoración', 'I', 2, 13, '/privado/alumno/ListarsesionesPorValoracion.softwareII');



CREATE TABLE IF NOT EXISTS `instalaciones_sesiones` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `IdSesion` INT(11) NOT NULL,
  `IdInstalacion` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`idSesion`) REFERENCES `sesiones`(`IdSesion`),
  FOREIGN KEY (`IdInstalacion`) REFERENCES `instalaciones`(`IdInstalacion`)
);

