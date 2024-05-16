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
  `Aforo_max` int NOT NULL,
  PRIMARY KEY (`IdInstalacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla proyecto_insoii.instalaciones: ~0 rows (aproximadamente)

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla proyecto_insoii.instalaciones_sesiones: ~0 rows (aproximadamente)

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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb3;

-- Volcando datos para la tabla proyecto_insoii.menus: ~26 rows (aproximadamente)
INSERT INTO `menus` (`IdMenu`, `Nombre`, `Tipo`, `IdRol`, `IdMenu_Menu`, `Url`) VALUES
	(1, 'Usuarios', 'S', 3, NULL, NULL),
	(2, 'Nuevo', 'I', 3, 1, '/faces/private/administrador/altausuario.xhtml'),
	(3, 'Editar', 'I', 3, 1, '/faces/private/administrador/editarUsuario.xhtml'),
	(5, 'Sesiones', 'S', 3, NULL, NULL),
	(6, 'Nuevo', 'I', 3, 5, '/privado/administrador/AltaRol.softwareII'),
	(7, 'Modificar', 'I', 3, 5, '/privado/administrador/ModificarRol.softwareII'),
	(8, 'Eliminar', 'I', 3, 5, '/privado/administrador/EliminarRol.softwareII'),
	(9, 'Publicación', 'S', 2, NULL, NULL),
	(10, 'Nueva', 'I', 2, 9, '/privado//alumno/AltaPublicacion.softwareII'),
	(11, 'Modificar', 'I', 2, 9, '/privado/alumno/ModificarPublicacion.softwareII'),
	(12, 'Eliminar', 'I', 2, 9, '/privado//alumno/EliminarPublicacion.softwareII'),
	(13, 'Listar sesiones', 'S', 2, NULL, ''),
	(14, 'Sesiones', 'S', 1, NULL, ''),
	(15, 'Leer', 'I', 1, 14, '/faces/private/profesor/SesionesEntrenador.xhtml'),
	(16, 'Eliminar', 'I', 1, 14, NULL),
	(17, 'Comentar', 'I', 1, NULL, '/privado/profesor/ComentarPublicacion.softwareII'),
	(18, 'Valorar', 'I', 1, NULL, NULL),
	(22, 'Instalaciones', 'S', 3, NULL, NULL),
	(23, 'Nueva', 'I', 3, 22, '/privado/administrador/AltaCategoria.softwareII'),
	(24, 'Modificar', 'I', 3, 22, '/privado/administrador/ModificarCategoria.softwareII'),
	(25, 'Eliminar', 'I', 3, 22, '/privado/administrador/EliminarCategoria.softwareII'),
	(26, 'Mis sesiones', 'I', 2, 13, '/privado/alumno/ListarsesionesPorCategoria.softwareII'),
	(27, 'Todas las sesiones', 'I', 2, 13, '/privado/alumno/ListarsesionesPorValoracion.softwareII'),
	(28, 'Mi perfil', 'S', 1, NULL, NULL),
	(29, 'Mi perfil', 'S', 2, NULL, NULL),
	(30, 'Mi perfil', 'S', 3, NULL, NULL);

-- Volcando estructura para tabla proyecto_insoii.personas
CREATE TABLE IF NOT EXISTS `personas` (
  `IdPerson` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(75) NOT NULL,
  `Apellidos` varchar(100) NOT NULL,
  `FechaNacimiento` datetime NOT NULL,
  `Sexo` varchar(1) NOT NULL,
  PRIMARY KEY (`IdPerson`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla proyecto_insoii.personas: ~4 rows (aproximadamente)
INSERT INTO `personas` (`IdPerson`, `Nombre`, `Apellidos`, `FechaNacimiento`, `Sexo`) VALUES
	(1, 'Messi', 'Messi', '2024-05-14 02:20:49', 'M'),
	(2, 'Antonio', 'García Rodríguez', '1995-05-10 00:00:00', 'M'),
	(3, 'Gabriel', 'García Pérez', '1998-12-15 00:00:00', 'M'),
	(4, 'anuelAAA', 'AAAAAAAAAAAA', '2024-05-01 00:00:00', 'M');

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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla proyecto_insoii.sesiones: ~2 rows (aproximadamente)
INSERT INTO `sesiones` (`IdSesion`, `Titulo`, `Cuerpo`, `Fecha`, `Valoracion_media`, `Comentarios`) VALUES
	(6, 'Messi', 'enano', '2024-05-15 00:00:00', 0.00, NULL),
	(7, 'cr7', 'cabra', '2024-05-18 00:00:00', 0.00, NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla proyecto_insoii.usuarios: ~4 rows (aproximadamente)
INSERT INTO `usuarios` (`IdUser`, `Username`, `Password`, `UltimaConexion`, `IdPerson`, `IdRol`) VALUES
	(1, 'Entrenador', 'Entre', NULL, 1, 1),
	(2, 'Alumno', 'Alumno', '2018-01-27 00:03:56', 2, 2),
	(3, 'Admin', 'Admin', '2018-01-31 17:34:43', 3, 3),
	(4, 'anuelAA', '123', NULL, 4, 1);

-- Volcando estructura para tabla proyecto_insoii.usuarios_sesiones
CREATE TABLE IF NOT EXISTS `usuarios_sesiones` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `IdSesion` int NOT NULL,
  `IdUser` int NOT NULL,
  `Valoracion` int DEFAULT NULL,
  `Comentarios` varchar(100) DEFAULT NULL,
  `Fecha_hora` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`),
  KEY `IdSesion` (`IdSesion`),
  KEY `IdUser` (`IdUser`),
  CONSTRAINT `usuarios_sesiones_ibfk_1` FOREIGN KEY (`IdSesion`) REFERENCES `sesiones` (`IdSesion`),
  CONSTRAINT `usuarios_sesiones_ibfk_2` FOREIGN KEY (`IdUser`) REFERENCES `usuarios` (`IdUser`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla proyecto_insoii.usuarios_sesiones: ~2 rows (aproximadamente)
INSERT INTO `usuarios_sesiones` (`Id`, `IdSesion`, `IdUser`, `Valoracion`, `Comentarios`, `Fecha_hora`) VALUES
	(4, 6, 1, NULL, NULL, '2024-05-14 02:26:15'),
	(5, 7, 1, NULL, NULL, '2024-05-14 02:26:46');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
