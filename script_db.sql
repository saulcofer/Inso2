-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.3.0 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para proyecto_insoii
CREATE DATABASE IF NOT EXISTS `proyecto_insoii` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `proyecto_insoii`;

-- Volcando estructura para tabla proyecto_insoii.instalaciones
CREATE TABLE IF NOT EXISTS `instalaciones` (
  `IdInstalacion` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL,
  `Descripcion` varchar(100) NOT NULL,
  `Tipo` varchar(20) NOT NULL,
  `Aforo_min` int DEFAULT NULL,
  `Aforo_max` int DEFAULT NULL,
  PRIMARY KEY (`IdInstalacion`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla proyecto_insoii.instalaciones: ~21 rows (aproximadamente)
INSERT INTO `instalaciones` (`IdInstalacion`, `Nombre`, `Descripcion`, `Tipo`, `Aforo_min`, `Aforo_max`) VALUES
	(1, 'Reino de León', 'Campo furbo 1 rfef', 'I', NULL, 15000),
	(2, 'Reino de León2', 'Campo furbo 2 rfef', 'I', NULL, 15000),
	(3, 'Sala de Pelotas', 'Pelotas ', 'M', NULL, 2),
	(4, 'Sala de Yoga', 'Boxeo', 'M', NULL, 1),
	(5, 'Pista de Tenis', 'Cancha de tenis', 'I', NULL, 500),
	(6, 'Gimnasio', 'Espacio para hacer ejercicio', 'I', NULL, 100),
	(7, 'Piscina', 'Piscina olímpica', 'I', NULL, 5000),
	(8, 'Sala de Pesas', 'Equipos de levantamiento de pesas', 'M', NULL, 10),
	(9, 'Cancha de Baloncesto', 'Cancha de baloncesto', 'I', NULL, 200),
	(10, 'Sala de Spinning', 'Bicicletas estáticas', 'M', NULL, 20),
	(11, 'Campo de Fútbol', 'Campo de fútbol', 'I', NULL, 1000),
	(12, 'Sala de Pilates', 'Ejercicios de pilates', 'M', NULL, 5),
	(13, 'Pista de Atletismo', 'Pista de carreras', 'I', NULL, 100),
	(14, 'Sala de Boxeo', 'Espacio para practicar boxeo', 'M', NULL, 5),
	(15, 'Cancha de Voleibol', 'Cancha de voleibol', 'I', NULL, 100),
	(16, 'Sala de Zumba', 'Clases de zumba', 'M', NULL, 15),
	(17, 'Cancha de Fútbol Sala', 'Cancha de fútbol sala', 'I', NULL, 100),
	(18, 'Sala de Escalada', 'Paredes de escalada', 'M', NULL, 10),
	(19, 'Cancha de Bádminton', 'Cancha de bádminton', 'I', NULL, 50),
	(20, 'Sala de Aeróbicos', 'Clases de aeróbicos', 'M', NULL, 20),
	(21, 'Cancha de Rugby', 'Cancha de rugby', 'I', NULL, 200);

-- Volcando estructura para tabla proyecto_insoii.instalaciones_sesiones
CREATE TABLE IF NOT EXISTS `instalaciones_sesiones` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `IdSesion` int NOT NULL,
  `IdInstalacion` int NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `IdSesion` (`IdSesion`),
  KEY `IdInstalacion` (`IdInstalacion`),
  CONSTRAINT `instalaciones_sesiones_ibfk_1` FOREIGN KEY (`IdSesion`) REFERENCES `sesiones` (`IdSesion`),
  CONSTRAINT `instalaciones_sesiones_ibfk_2` FOREIGN KEY (`IdInstalacion`) REFERENCES `instalaciones` (`IdInstalacion`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla proyecto_insoii.instalaciones_sesiones: ~2 rows (aproximadamente)
INSERT INTO `instalaciones_sesiones` (`Id`, `IdSesion`, `IdInstalacion`) VALUES
	(115, 53, 4),
	(116, 54, 7),
	(117, 55, 3),
	(118, 55, 4);

-- Volcando estructura para tabla proyecto_insoii.menus
CREATE TABLE IF NOT EXISTS `menus` (
  `IdMenu` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL,
  `Tipo` varchar(1) NOT NULL,
  `IdRol` int NOT NULL,
  `IdMenu_Menu` int DEFAULT NULL,
  `Url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`IdMenu`),
  KEY `FK_Menu_Rol` (`IdRol`),
  KEY `Fk_Menu_menu` (`IdMenu_Menu`),
  CONSTRAINT `Fk_Menu_menu` FOREIGN KEY (`IdMenu_Menu`) REFERENCES `menus` (`IdMenu`),
  CONSTRAINT `FK_Menu_Rol` FOREIGN KEY (`IdRol`) REFERENCES `roles` (`IdRol`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb3;

-- Volcando datos para la tabla proyecto_insoii.menus: ~18 rows (aproximadamente)
INSERT INTO `menus` (`IdMenu`, `Nombre`, `Tipo`, `IdRol`, `IdMenu_Menu`, `Url`) VALUES
	(1, 'Usuarios', 'S', 3, NULL, NULL),
	(2, 'Nuevo', 'I', 3, 1, '/faces/private/administrador/altausuario.xhtml'),
	(3, 'Editar', 'I', 3, 1, '/faces/private/administrador/editarUsuario.xhtml'),
	(5, 'Sesiones', 'S', 3, NULL, NULL),
	(7, 'Editar', 'I', 3, 5, '/faces/private/administrador/EditarSesion.xhtml'),
	(13, 'Listar sesiones', 'S', 2, NULL, ''),
	(14, 'Sesiones', 'S', 1, NULL, ''),
	(15, 'Mis sesiones', 'I', 1, 14, '/faces/private/profesor/SesionesEntrenador.xhtml'),
	(22, 'Instalaciones', 'S', 3, NULL, NULL),
	(24, 'Editar', 'I', 3, 22, '/faces/private/administrador/EditarInstalacion.xhtml'),
	(26, 'Mis sesiones', 'I', 2, 13, '/faces/private/alumno/listarMisSesiones.xhtml'),
	(27, 'Todas las sesiones', 'I', 2, 13, '/faces/private/alumno/listarSesiones.xhtml'),
	(28, 'Mi perfil', 'S', 1, NULL, NULL),
	(29, 'Mi perfil', 'S', 2, NULL, NULL),
	(30, 'Mi perfil', 'S', 3, NULL, NULL),
	(31, 'Editar perfil', 'I', 1, 28, '/faces/private/perfil.xhtml'),
	(32, 'Editar perfil', 'I', 2, 29, '/faces/private/perfil.xhtml'),
	(33, 'Editar perfil', 'I', 3, 30, '/faces/private/perfil.xhtml');

-- Volcando estructura para tabla proyecto_insoii.personas
CREATE TABLE IF NOT EXISTS `personas` (
  `IdPerson` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(75) NOT NULL,
  `Apellidos` varchar(100) NOT NULL,
  `FechaNacimiento` datetime NOT NULL,
  `Sexo` varchar(1) NOT NULL,
  PRIMARY KEY (`IdPerson`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla proyecto_insoii.personas: ~20 rows (aproximadamente)
INSERT INTO `personas` (`IdPerson`, `Nombre`, `Apellidos`, `FechaNacimiento`, `Sexo`) VALUES
	(1, 'Messi', 'Messi', '2024-05-14 02:20:49', 'M'),
	(2, 'Antonio', 'García Rodríguez', '1995-05-10 00:00:00', 'M'),
	(3, 'Gabriel', 'García Pérez', '1998-12-15 00:00:00', 'M'),
	(4, 'Maria', 'Lopez', '1990-07-20 00:00:00', 'F'),
	(5, 'Juan', 'Martinez', '1985-03-12 00:00:00', 'M'),
	(6, 'Laura', 'Gonzalez', '1992-09-05 00:00:00', 'F'),
	(7, 'Carlos', 'Sanchez', '1988-11-30 00:00:00', 'M'),
	(8, 'Ana', 'Fernandez', '1993-06-25 00:00:00', 'F'),
	(9, 'Pedro', 'Gomez', '1997-02-18 00:00:00', 'M'),
	(10, 'Sofia', 'Rodriguez', '1994-08-15 00:00:00', 'F'),
	(11, 'Diego', 'Hernandez', '1989-04-10 00:00:00', 'M'),
	(12, 'Isabella', 'Perez', '1991-10-05 00:00:00', 'F'),
	(13, 'Javier', 'Torres', '1996-01-20 00:00:00', 'M'),
	(14, 'Valentina', 'Lopez', '1999-07-15 00:00:00', 'F'),
	(15, 'Manuel', 'Garcia', '1992-03-12 00:00:00', 'M'),
	(16, 'Camila', 'Martinez', '1987-09-05 00:00:00', 'F'),
	(17, 'Ricardo', 'Sanchez', '1994-11-30 00:00:00', 'M'),
	(18, 'Julia', 'Fernandez', '1989-06-25 00:00:00', 'F'),
	(19, 'Luis', 'Gomez', '1993-02-18 00:00:00', 'M'),
	(20, 'Valeria', 'Rodriguez', '1996-08-15 00:00:00', 'F'),
	(37, 'Penélope', 'Cruz', '2024-06-01 00:00:00', 'F'),
	(38, 'Pepe', 'castro', '2024-05-16 00:00:00', 'M');

-- Volcando estructura para tabla proyecto_insoii.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `IdRol` int NOT NULL AUTO_INCREMENT,
  `TipoUsuario` varchar(20) NOT NULL,
  `Descripcion` varchar(100) NOT NULL,
  PRIMARY KEY (`IdRol`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla proyecto_insoii.roles: ~3 rows (aproximadamente)
INSERT INTO `roles` (`IdRol`, `TipoUsuario`, `Descripcion`) VALUES
	(1, 'E', 'Entrenador'),
	(2, 'P', 'Participante'),
	(3, 'A', 'Admin');

-- Volcando estructura para tabla proyecto_insoii.sesiones
CREATE TABLE IF NOT EXISTS `sesiones` (
  `IdSesion` int NOT NULL AUTO_INCREMENT,
  `Titulo` varchar(100) NOT NULL,
  `Cuerpo` varchar(1000) NOT NULL,
  `Fecha` datetime NOT NULL,
  `Valoracion_media` decimal(10,2) DEFAULT NULL,
  `Comentarios` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`IdSesion`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla proyecto_insoii.sesiones: ~3 rows (aproximadamente)
INSERT INTO `sesiones` (`IdSesion`, `Titulo`, `Cuerpo`, `Fecha`, `Valoracion_media`, `Comentarios`) VALUES
	(53, 'Sesion de Prueba 1', 'asfdasdfas', '2024-05-31 00:00:00', 5.32, 'nullpenelope12:Hola soy nueva  penelope12:Buenas soy nueva  penelope12:Buenas soy nueva  penelope12:'),
	(54, 'Sesión de natación', 'para pepe', '2024-05-27 00:00:00', 3.89, 'Alumno:hola pepe  Pepe:hola compis  Pepe:hola compis  Pepe:hola compis  Pepe:hola compis  '),
	(55, 'Sesion de Prueba 33', '33', '2024-05-14 00:00:00', 4.67, '');

-- Volcando estructura para tabla proyecto_insoii.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `IdUser` int NOT NULL AUTO_INCREMENT,
  `Username` char(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `UltimaConexion` datetime DEFAULT NULL,
  `IdPerson` int NOT NULL,
  `IdRol` int NOT NULL,
  PRIMARY KEY (`IdUser`),
  KEY `IdPerson` (`IdPerson`),
  KEY `IdRol` (`IdRol`),
  CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`IdPerson`) REFERENCES `personas` (`IdPerson`),
  CONSTRAINT `usuarios_ibfk_2` FOREIGN KEY (`IdRol`) REFERENCES `roles` (`IdRol`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla proyecto_insoii.usuarios: ~20 rows (aproximadamente)
INSERT INTO `usuarios` (`IdUser`, `Username`, `Password`, `UltimaConexion`, `IdPerson`, `IdRol`) VALUES
	(1, 'Entrenador', 'Entrenador', NULL, 1, 1),
	(2, 'Alumno', 'Alumno', '2018-01-27 00:03:56', 2, 2),
	(3, 'Admin', 'Admin', '2018-01-31 17:34:43', 3, 3),
	(4, 'maria_lopez', 'password4', NULL, 4, 2),
	(5, 'juan_martinez', 'password5', NULL, 5, 1),
	(6, 'laura_gonzalez', 'password6', NULL, 6, 2),
	(7, 'carlos_sanchez', 'password7', NULL, 7, 1),
	(8, 'ana_fernandez', 'password8', NULL, 8, 2),
	(9, 'pedro_gomez', 'password9', NULL, 9, 1),
	(10, 'sofia_rodriguez', 'password10', NULL, 10, 2),
	(11, 'diego_hernandez', 'password11', NULL, 11, 1),
	(12, 'isabella_perez', 'password12', NULL, 12, 2),
	(13, 'javier_torres', 'password13', NULL, 13, 1),
	(14, 'valentina_lopez', 'password14', NULL, 14, 2),
	(15, 'manuel_garcia', 'password15', NULL, 15, 1),
	(16, 'camila_martinez', 'password16', NULL, 16, 2),
	(17, 'ricardo_sanchez', 'password17', NULL, 17, 1),
	(18, 'julia_fernandez', 'password18', NULL, 18, 2),
	(19, 'luis_gomez', 'password19', NULL, 19, 1),
	(20, 'valeria_rodriguez', 'password20', NULL, 20, 2),
	(37, 'penelope12', 'penelope', NULL, 37, 2),
	(38, 'Pepe', 'Pepe', NULL, 38, 2);

-- Volcando estructura para tabla proyecto_insoii.usuarios_sesiones
CREATE TABLE IF NOT EXISTS `usuarios_sesiones` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `IdSesion` int NOT NULL,
  `IdUser` int NOT NULL,
  `Valoracion` int DEFAULT NULL,
  `Comentarios` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Fecha_hora` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`),
  KEY `IdSesion` (`IdSesion`),
  KEY `IdUser` (`IdUser`),
  CONSTRAINT `usuarios_sesiones_ibfk_1` FOREIGN KEY (`IdSesion`) REFERENCES `sesiones` (`IdSesion`),
  CONSTRAINT `usuarios_sesiones_ibfk_2` FOREIGN KEY (`IdUser`) REFERENCES `usuarios` (`IdUser`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla proyecto_insoii.usuarios_sesiones: ~3 rows (aproximadamente)
INSERT INTO `usuarios_sesiones` (`Id`, `IdSesion`, `IdUser`, `Valoracion`, `Comentarios`, `Fecha_hora`) VALUES
	(105, 53, 1, NULL, NULL, '2024-05-28 19:05:41'),
	(106, 53, 37, NULL, NULL, '2024-05-28 19:10:33'),
	(107, 54, 1, NULL, NULL, '2024-05-28 19:16:54'),
	(110, 54, 2, NULL, NULL, '2024-05-28 19:24:08'),
	(111, 54, 38, NULL, NULL, '2024-05-28 19:26:18'),
	(112, 55, 7, NULL, NULL, '2024-05-28 19:41:47'),
	(113, 55, 38, NULL, NULL, '2024-05-28 19:42:02'),
	(114, 55, 37, NULL, NULL, '2024-05-28 19:43:10');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
