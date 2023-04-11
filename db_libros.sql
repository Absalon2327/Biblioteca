/*
 Navicat Premium Data Transfer

 Source Server         : SQLSERVERBACKEND
 Source Server Type    : SQL Server
 Source Server Version : 15002000
 Source Host           : localhost:1433
 Source Catalog        : db_libros
 Source Schema         : dbo

 Target Server Type    : SQL Server
 Target Server Version : 15002000
 File Encoding         : 65001

 Date: 10/04/2023 15:40:21
*/


-- ----------------------------
-- Table structure for tb_alumno
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[tb_alumno]') AND type IN ('U'))
	DROP TABLE [dbo].[tb_alumno]
GO

CREATE TABLE [dbo].[tb_alumno] (
  [carnet] nvarchar(7) COLLATE Modern_Spanish_CI_AS  NOT NULL,
  [nombre] nvarchar(25) COLLATE Modern_Spanish_CI_AS  NOT NULL,
  [apellido] nvarchar(25) COLLATE Modern_Spanish_CI_AS  NOT NULL,
  [direccion] nvarchar(300) COLLATE Modern_Spanish_CI_AS  NOT NULL,
  [fechanacimiento] date  NOT NULL,
  [fechaingreso] date  NOT NULL,
  [genero] nvarchar(15) COLLATE Modern_Spanish_CI_AS  NOT NULL,
  [estado] varchar(5) COLLATE Modern_Spanish_CI_AS  NOT NULL
)
GO

ALTER TABLE [dbo].[tb_alumno] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of tb_alumno
-- ----------------------------
INSERT INTO [dbo].[tb_alumno] ([carnet], [nombre], [apellido], [direccion], [fechanacimiento], [fechaingreso], [genero], [estado]) VALUES (N'CM16057', N'Cristian', N'Mejía', N'San Vicente', N'1997-09-27', N'2016-01-16', N'Masculino', N't')
GO

INSERT INTO [dbo].[tb_alumno] ([carnet], [nombre], [apellido], [direccion], [fechanacimiento], [fechaingreso], [genero], [estado]) VALUES (N'FC16056', N'Fabricio', N'Corvera', N'Santo Domingo, San Vicente', N'1991-09-27', N'2016-01-16', N'Masculino', N't')
GO


-- ----------------------------
-- Table structure for tb_autor
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[tb_autor]') AND type IN ('U'))
	DROP TABLE [dbo].[tb_autor]
GO

CREATE TABLE [dbo].[tb_autor] (
  [codigoautor] nvarchar(5) COLLATE Modern_Spanish_CI_AS  NOT NULL,
  [nombreautor] nvarchar(60) COLLATE Modern_Spanish_CI_AS  NULL,
  [apellidoautor] nvarchar(35) COLLATE Modern_Spanish_CI_AS  NULL,
  [fechanacimientoautor] date  NULL,
  [nacionalidad] nvarchar(40) COLLATE Modern_Spanish_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[tb_autor] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of tb_autor
-- ----------------------------
INSERT INTO [dbo].[tb_autor] ([codigoautor], [nombreautor], [apellidoautor], [fechanacimientoautor], [nacionalidad]) VALUES (N'1', N'Fabricio', N'CorverU', N'1997-09-27', N'Nacional')
GO

INSERT INTO [dbo].[tb_autor] ([codigoautor], [nombreautor], [apellidoautor], [fechanacimientoautor], [nacionalidad]) VALUES (N'2', N'Cristian', N'Mejía', N'1986-09-27', N'Nacional')
GO


-- ----------------------------
-- Table structure for tb_categoria
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[tb_categoria]') AND type IN ('U'))
	DROP TABLE [dbo].[tb_categoria]
GO

CREATE TABLE [dbo].[tb_categoria] (
  [codigocategoria] nvarchar(5) COLLATE Modern_Spanish_CI_AS  NOT NULL,
  [nombrecategoria] nvarchar(50) COLLATE Modern_Spanish_CI_AS  NOT NULL
)
GO

ALTER TABLE [dbo].[tb_categoria] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of tb_categoria
-- ----------------------------
INSERT INTO [dbo].[tb_categoria] ([codigocategoria], [nombrecategoria]) VALUES (N'1', N'Terrorifico')
GO

INSERT INTO [dbo].[tb_categoria] ([codigocategoria], [nombrecategoria]) VALUES (N'2', N'Drama')
GO

INSERT INTO [dbo].[tb_categoria] ([codigocategoria], [nombrecategoria]) VALUES (N'3', N'Ficcioncita')
GO

INSERT INTO [dbo].[tb_categoria] ([codigocategoria], [nombrecategoria]) VALUES (N'4', N'Acción')
GO


-- ----------------------------
-- Table structure for tb_libro
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[tb_libro]') AND type IN ('U'))
	DROP TABLE [dbo].[tb_libro]
GO

CREATE TABLE [dbo].[tb_libro] (
  [codigolibro] nvarchar(5) COLLATE Modern_Spanish_CI_AS  NOT NULL,
  [titulolibro] nvarchar(100) COLLATE Modern_Spanish_CI_AS  NOT NULL,
  [existencia] int  NOT NULL,
  [codigocategoria] nvarchar(5) COLLATE Modern_Spanish_CI_AS  NOT NULL,
  [precio] decimal(14,2)  NOT NULL,
  [codigoautor] nvarchar(5) COLLATE Modern_Spanish_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[tb_libro] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of tb_libro
-- ----------------------------
INSERT INTO [dbo].[tb_libro] ([codigolibro], [titulolibro], [existencia], [codigocategoria], [precio], [codigoautor]) VALUES (N'12345', N'Don Quijote de la Mancha', N'25', N'1', N'.25', N'1')
GO

INSERT INTO [dbo].[tb_libro] ([codigolibro], [titulolibro], [existencia], [codigocategoria], [precio], [codigoautor]) VALUES (N'35214', N'Harry Potter', N'14', N'3', N'2.00', N'2')
GO

INSERT INTO [dbo].[tb_libro] ([codigolibro], [titulolibro], [existencia], [codigocategoria], [precio], [codigoautor]) VALUES (N'75123', N'Pepito', N'96', N'4', N'.60', N'1')
GO

INSERT INTO [dbo].[tb_libro] ([codigolibro], [titulolibro], [existencia], [codigocategoria], [precio], [codigoautor]) VALUES (N'78546', N'Alicia en el país de las maravillas', N'47', N'2', N'.47', N'2')
GO

INSERT INTO [dbo].[tb_libro] ([codigolibro], [titulolibro], [existencia], [codigocategoria], [precio], [codigoautor]) VALUES (N'95123', N'Mago de Oz', N'31', N'1', N'.95', N'1')
GO


-- ----------------------------
-- Table structure for tb_prestamo_alumno
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[tb_prestamo_alumno]') AND type IN ('U'))
	DROP TABLE [dbo].[tb_prestamo_alumno]
GO

CREATE TABLE [dbo].[tb_prestamo_alumno] (
  [carnet_alumno] nvarchar(7) COLLATE Modern_Spanish_CI_AS  NOT NULL,
  [codigo_libro] nvarchar(5) COLLATE Modern_Spanish_CI_AS  NOT NULL,
  [fche_prestamo] date  NOT NULL,
  [codigo_prestamo] nvarchar(5) COLLATE Modern_Spanish_CI_AS  NOT NULL,
  [cantidadprestamo] int  NOT NULL,
  [fecha_devolucion] date  NOT NULL
)
GO

ALTER TABLE [dbo].[tb_prestamo_alumno] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of tb_prestamo_alumno
-- ----------------------------
INSERT INTO [dbo].[tb_prestamo_alumno] ([carnet_alumno], [codigo_libro], [fche_prestamo], [codigo_prestamo], [cantidadprestamo], [fecha_devolucion]) VALUES (N'CM16057', N'35214', N'2022-04-01', N'1', N'5', N'2022-04-05')
GO


-- ----------------------------
-- Table structure for tb_usuarios
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[tb_usuarios]') AND type IN ('U'))
	DROP TABLE [dbo].[tb_usuarios]
GO

CREATE TABLE [dbo].[tb_usuarios] (
  [idusuario] varchar(50) COLLATE Modern_Spanish_CI_AS  NOT NULL,
  [nombreusuario] varchar(30) COLLATE Modern_Spanish_CI_AS  NOT NULL,
  [apellidousuario] varchar(30) COLLATE Modern_Spanish_CI_AS  NOT NULL,
  [usuario] varchar(30) COLLATE Modern_Spanish_CI_AS  NOT NULL,
  [contrasenia] varchar(200) COLLATE Modern_Spanish_CI_AS  NOT NULL,
  [nivelusuario] varchar(15) COLLATE Modern_Spanish_CI_AS  NOT NULL,
  [estadousuario] varchar(15) COLLATE Modern_Spanish_CI_AS  NOT NULL
)
GO

ALTER TABLE [dbo].[tb_usuarios] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of tb_usuarios
-- ----------------------------
INSERT INTO [dbo].[tb_usuarios] ([idusuario], [nombreusuario], [apellidousuario], [usuario], [contrasenia], [nivelusuario], [estadousuario]) VALUES (N'USER01', N'Fabricio', N'Corvera', N'fabri', N'$2a$10$Z9lC./cClcUvP.vXT1X3peg43TVgaIGrs39gMzjonkXPZsGg29CDS', N'Administrador', N'activo')
GO

INSERT INTO [dbo].[tb_usuarios] ([idusuario], [nombreusuario], [apellidousuario], [usuario], [contrasenia], [nivelusuario], [estadousuario]) VALUES (N'USER02', N'Cristian', N'Mejía', N'Cris', N'$2a$10$B91ucJAz1.3ou8FyRZdaAutxLltalyOixkEUGMoiXIMJYtUMk2vIW', N'Administrador', N'activo')
GO

INSERT INTO [dbo].[tb_usuarios] ([idusuario], [nombreusuario], [apellidousuario], [usuario], [contrasenia], [nivelusuario], [estadousuario]) VALUES (N'USER03', N'Moisés', N'Lucer', N'snow', N'$2a$10$CC6GODplHZwIPXFm7yFnBOmFilSk/dq5gILucqQEFV/zgysZCqIJG', N'Administrador', N'activo')
GO

INSERT INTO [dbo].[tb_usuarios] ([idusuario], [nombreusuario], [apellidousuario], [usuario], [contrasenia], [nivelusuario], [estadousuario]) VALUES (N'USER04', N'Kenia', N'Ramírez', N'ken', N'$2a$10$FzydneV9drj.9GZ58xhCXO7RIdT6i/2FM4avjpsSvct.doQME6F4K', N'Administrador', N'activo')
GO


-- ----------------------------
-- View structure for llenarcomboAlumno
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[llenarcomboAlumno]') AND type IN ('V'))
	DROP VIEW [dbo].[llenarcomboAlumno]
GO

CREATE VIEW [dbo].[llenarcomboAlumno] AS select a.carnet as carnett, concat(a.nombre, ' ', a.apellido) as nombrecompleto from tb_alumno a
GO


-- ----------------------------
-- View structure for llenarcomboLibro
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[llenarcomboLibro]') AND type IN ('V'))
	DROP VIEW [dbo].[llenarcomboLibro]
GO

CREATE VIEW [dbo].[llenarcomboLibro] AS select l.codigolibro as cod, l.titulolibro as titulo from tb_libro l
GO


-- ----------------------------
-- View structure for vistaconsulta1
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[vistaconsulta1]') AND type IN ('V'))
	DROP VIEW [dbo].[vistaconsulta1]
GO

CREATE VIEW [dbo].[vistaconsulta1] AS SELECT carnet, nombre, apellido, DATEDIFF(year, fechanacimiento, GETDATE()) as edad, 
DATEDIFF(day, fechanacimiento, GETDATE()) / 365.25 AS edad_exacta, 
CAST(DATEDIFF(day, fechanacimiento, GETDATE()) / 365.25 as int) AS edad_real 
from db_libros.dbo.tb_alumno
GO


-- ----------------------------
-- View structure for vistafechasprestamo
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[vistafechasprestamo]') AND type IN ('V'))
	DROP VIEW [dbo].[vistafechasprestamo]
GO

CREATE VIEW [dbo].[vistafechasprestamo] AS select pa.fche_prestamo as fechaprestamo from tb_prestamo_alumno pa
inner join tb_alumno as a on a.carnet= pa.carnet_alumno
inner join tb_libro as l on l.codigolibro= pa.codigo_libro
GO


-- ----------------------------
-- View structure for vistalibros
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[vistalibros]') AND type IN ('V'))
	DROP VIEW [dbo].[vistalibros]
GO

CREATE VIEW [dbo].[vistalibros] AS SELECT l.codigolibro, l.titulolibro , l.existencia , c.nombrecategoria, l.precio, a.nombreautor FROM db_libros.dbo.tb_libro as l 
inner join db_libros.dbo.tb_autor as a on a.codigoautor = l.codigoautor 
inner join db_libros.dbo.tb_categoria as c on c.codigocategoria = l.codigocategoria
GO


-- ----------------------------
-- View structure for vistaprestamos
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[vistaprestamos]') AND type IN ('V'))
	DROP VIEW [dbo].[vistaprestamos]
GO

CREATE VIEW [dbo].[vistaprestamos] AS SELECT p.codigo_prestamo as codiprestamo, p.carnet_alumno, a.apellido, a.nombre, p.codigo_libro as codilibro, l.titulolibro, p.fche_prestamo, p.cantidadprestamo, p.fecha_devolucion
FROM db_libros.dbo.tb_prestamo_alumno as p 
INNER JOIN db_libros.dbo.tb_alumno as a on p.carnet_alumno = a.carnet
INNER JOIN db_libros.dbo.tb_libro as l on p.codigo_libro = l.codigolibro
GO


-- ----------------------------
-- View structure for vistaselectcategorias
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[vistaselectcategorias]') AND type IN ('V'))
	DROP VIEW [dbo].[vistaselectcategorias]
GO

CREATE VIEW [dbo].[vistaselectcategorias] AS SELECT c.codigocategoria as codigo, c.nombrecategoria as nombre FROM db_libros.dbo.tb_categoria as c 
INNER JOIN db_libros.dbo.tb_libro as l ON c.codigocategoria = l.codigocategoria GROUP BY c.codigocategoria, c.nombrecategoria
GO


-- ----------------------------
-- View structure for vistausuarios
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[vistausuarios]') AND type IN ('V'))
	DROP VIEW [dbo].[vistausuarios]
GO

CREATE VIEW [dbo].[vistausuarios] AS SELECT
	tb_usuarios.idusuario AS id,
	tb_usuarios.nombreusuario AS nombre,
	tb_usuarios.apellidousuario AS apellido,
	tb_usuarios.usuario AS usuario,
	tb_usuarios.contrasenia AS contra,
	tb_usuarios.nivelusuario AS nivel,
	tb_usuarios.estadousuario AS estado 
FROM
	dbo.tb_usuarios 
WHERE
	estadousuario = 'activo'
GO


-- ----------------------------
-- function structure for funcionconsulta5
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[funcionconsulta5]') AND type IN ('FN', 'FS', 'FT', 'IF', 'TF'))
	DROP FUNCTION[dbo].[funcionconsulta5]
GO

CREATE FUNCTION [dbo].[funcionconsulta5] (@parametro int)
RETURNS TABLE
AS
RETURN
(
	SELECT l.codigolibro as codigolibro, l.titulolibro , l.precio FROM db_libros.dbo.tb_libro as l WHERE l.codigocategoria = @parametro
);
GO


-- ----------------------------
-- procedure structure for insertarUsuarios
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[insertarUsuarios]') AND type IN ('FN', 'FS', 'FT', 'IF', 'TF'))
	DROP PROCEDURE[dbo].[insertarUsuarios]
GO

CREATE PROCEDURE [dbo].[insertarUsuarios] @idUser VARCHAR ( 50 ),
@nombreUser VARCHAR ( 30 ),
@apellidoUser VARCHAR ( 30 ),
@usuario VARCHAR ( 15 ),
@contras VARCHAR ( 200 ),
@nivelUser VARCHAR ( 15 ),
@estadoUser VARCHAR ( 25 ) AS BEGIN
	INSERT INTO tb_usuarios ( idusuario, nombreusuario, apellidousuario, usuario, contrasenia, nivelusuario, estadousuario )
	VALUES
		( @idUser, @nombreUser,@apellidoUser,@usuario,@contras,@nivelUser,@estadoUser );
END
GO


-- ----------------------------
-- procedure structure for editarUsuarios
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[editarUsuarios]') AND type IN ('FN', 'FS', 'FT', 'IF', 'TF'))
	DROP PROCEDURE[dbo].[editarUsuarios]
GO

CREATE PROCEDURE [dbo].[editarUsuarios] @idUser VARCHAR ( 50 ),
@nombreUser VARCHAR ( 30 ),
@apellidoUser VARCHAR ( 30 ),
@usuario VARCHAR ( 15 ),
@nivelUser VARCHAR ( 15 ),
@estadoUser VARCHAR ( 25 ) AS BEGIN
	UPDATE tb_usuarios 
	SET nombreusuario = @nombreUser,
	apellidousuario = @apellidoUser,
	usuario = @usuario,
	nivelusuario = @nivelUser,
	estadousuario = @estadoUser 
	WHERE
		idusuario = @idUser;
	
END
GO


-- ----------------------------
-- procedure structure for eliminarUsuarios
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[eliminarUsuarios]') AND type IN ('FN', 'FS', 'FT', 'IF', 'TF'))
	DROP PROCEDURE[dbo].[eliminarUsuarios]
GO

CREATE PROCEDURE [dbo].[eliminarUsuarios] @idUser VARCHAR ( 50 ) AS BEGIN
	DELETE 
	FROM
		tb_usuarios 
	WHERE
		idusuario = @idUser 
	END
GO


-- ----------------------------
-- procedure structure for usuraioEspecifico
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[usuraioEspecifico]') AND type IN ('FN', 'FS', 'FT', 'IF', 'TF'))
	DROP PROCEDURE[dbo].[usuraioEspecifico]
GO

CREATE PROCEDURE [dbo].[usuraioEspecifico] @idUser VARCHAR ( 50 ) AS BEGIN
	SELECT
		idusuario AS id,
		nombreusuario AS nombre,
		apellidousuario AS apellido,
		usuario AS usuario,
		nivelusuario AS nivel,
		estadousuario AS estado 
	FROM
		tb_usuarios 
	WHERE
	idusuario = @idUser 
	END
GO


-- ----------------------------
-- procedure structure for editContraUser
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[editContraUser]') AND type IN ('FN', 'FS', 'FT', 'IF', 'TF'))
	DROP PROCEDURE[dbo].[editContraUser]
GO

CREATE PROCEDURE [dbo].[editContraUser] @idUser VARCHAR ( 50 ), @contraUser VARCHAR ( 200 ) AS BEGIN
	UPDATE tb_usuarios 
	SET tb_usuarios.contrasenia = @contraUser
	WHERE
		tb_usuarios.idusuario = @idUser;
	
END;
GO


-- ----------------------------
-- procedure structure for obtenerCredenciales
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[obtenerCredenciales]') AND type IN ('FN', 'FS', 'FT', 'IF', 'TF'))
	DROP PROCEDURE[dbo].[obtenerCredenciales]
GO

CREATE PROCEDURE [dbo].[obtenerCredenciales] @usuarioNombre VARCHAR ( 50 ) AS BEGIN
	SELECT
		idusuario AS id,
		contrasenia AS contra,
		usuario As usuario,
		nombreusuario AS nombre,
		apellidousuario AS apellido,
		nivelusuario AS nivel
	FROM
		tb_usuarios 
	WHERE
	usuario = @usuarioNombre AND estadousuario = 'activo'
	END
GO


-- ----------------------------
-- procedure structure for atualizartablaAlumno
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[atualizartablaAlumno]') AND type IN ('FN', 'FS', 'FT', 'IF', 'TF'))
	DROP PROCEDURE[dbo].[atualizartablaAlumno]
GO

CREATE PROCEDURE [dbo].[atualizartablaAlumno] 
	@nombre nvarchar(25),
	@apellido nvarchar(25),
	@direccion nvarchar(300),
	@fechanacimiento date,
	@fechaingreso date,
	@genero nvarchar(15),
	@estado varchar(5),
	@carnet nvarchar(7)
	
AS
BEGIN
	UPDATE tb_alumno set
	nombre=@nombre,
	apellido=@apellido,
	direccion=@direccion,
	fechanacimiento= @fechanacimiento,
	fechaingreso= @fechaingreso,
	genero= @genero,
	estado= @estado
	where carnet= @carnet
END
GO


-- ----------------------------
-- procedure structure for actualizartablaAutor
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[actualizartablaAutor]') AND type IN ('FN', 'FS', 'FT', 'IF', 'TF'))
	DROP PROCEDURE[dbo].[actualizartablaAutor]
GO

CREATE PROCEDURE [dbo].[actualizartablaAutor] 
	@nombreautor nvarchar(60),
	@apellidoautor nvarchar(35),
	@fechanacimientoautor date,
	@nacionalidad nvarchar(40),
	@codigoautor nvarchar(5)
	
AS
BEGIN
	UPDATE tb_autor set 
	nombreautor=@nombreautor,
	apellidoautor=@apellidoautor,
	fechanacimientoautor=@fechanacimientoautor,
	nacionalidad=@nacionalidad
	where codigoautor=@codigoautor
END
GO


-- ----------------------------
-- procedure structure for actualizartablaCategoria
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[actualizartablaCategoria]') AND type IN ('FN', 'FS', 'FT', 'IF', 'TF'))
	DROP PROCEDURE[dbo].[actualizartablaCategoria]
GO

CREATE PROCEDURE [dbo].[actualizartablaCategoria]
	-- Add the parameters for the stored procedure here
	@nombrecategoria nvarchar(50),
	@codigocategoria nvarchar(5)
AS
BEGIN
UPDATE tb_categoria set
nombrecategoria=@nombrecategoria
where codigocategoria=@codigocategoria
END
GO


-- ----------------------------
-- procedure structure for actualizartablaLibro
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[actualizartablaLibro]') AND type IN ('FN', 'FS', 'FT', 'IF', 'TF'))
	DROP PROCEDURE[dbo].[actualizartablaLibro]
GO

CREATE PROCEDURE [dbo].[actualizartablaLibro]
	-- Add the parameters for the stored procedure here
	@titulolibro nvarchar(100),
	@existencia int,
	@codigocategoria nvarchar(5),
	@precio decimal(14,2),
	@codigoautor nvarchar(5),
	@codigolibro nvarchar(5)
AS
BEGIN

UPDATE tb_libro set
titulolibro=@titulolibro,
existencia=@existencia,
codigocategoria=@codigocategoria,
precio=@precio,
codigoautor=@codigoautor
where codigolibro=@codigolibro

END
GO


-- ----------------------------
-- procedure structure for actualizartablaPrestamoalumno
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[actualizartablaPrestamoalumno]') AND type IN ('FN', 'FS', 'FT', 'IF', 'TF'))
	DROP PROCEDURE[dbo].[actualizartablaPrestamoalumno]
GO

CREATE PROCEDURE [dbo].[actualizartablaPrestamoalumno]
	-- Add the parameters for the stored procedure here
	@carnet_alumno nvarchar(7),
	@codigo_libro nvarchar(5),
	@fche_prestamo date,
	@cantidadprestamo int,
	@fecha_devolucion date,
	@codigo_prestamo nvarchar(5)

AS
BEGIN
UPDATE tb_prestamo_alumno set
carnet_alumno=@carnet_alumno,
codigo_libro=@codigo_libro,
fche_prestamo=@fche_prestamo,
cantidadprestamo=@cantidadprestamo,
fecha_devolucion=@fecha_devolucion
where codigo_prestamo=@codigo_prestamo
END
GO


-- ----------------------------
-- function structure for funcionconsulta2
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[funcionconsulta2]') AND type IN ('FN', 'FS', 'FT', 'IF', 'TF'))
	DROP FUNCTION[dbo].[funcionconsulta2]
GO

CREATE FUNCTION [dbo].[funcionconsulta2] 
(@parametro nvarchar(15))
RETURNS TABLE 
AS
RETURN 
(
	-- Add the SELECT statement with parameter references here
	
select concat(a.carnet,' ', a.nombre, a.apellido) as datos, l.codigolibro as codigolibro, l.titulolibro as titulo,
pa.fche_prestamo as fechaprestamo, pa.fecha_devolucion as fechadevolucion
from tb_prestamo_alumno pa
inner join tb_alumno as a on a.carnet= pa.carnet_alumno
inner join tb_libro as l on l.codigolibro= pa.codigo_libro
where pa.fche_prestamo=@parametro
)
GO


-- ----------------------------
-- procedure structure for insertarAutor
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[insertarAutor]') AND type IN ('FN', 'FS', 'FT', 'IF', 'TF'))
	DROP PROCEDURE[dbo].[insertarAutor]
GO

CREATE PROCEDURE [dbo].[insertarAutor]
	-- Add the parameters for the stored procedure here
	@codigoautor nvarchar(5),
	@nombreautor nvarchar(60),
	@apellidoautor nvarchar(35),
	@fechanacimientoautor date,
	@nacionalidad nvarchar(40)
AS
BEGIN

	insert into tb_autor( codigoautor,
	nombreautor, 
	apellidoautor, 
	fechanacimientoautor, 
	nacionalidad) 
	values (@codigoautor,
	@nombreautor,
	@apellidoautor,
	@fechanacimientoautor,
	@nacionalidad)
END
GO


-- ----------------------------
-- procedure structure for insertarAlumno
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[insertarAlumno]') AND type IN ('FN', 'FS', 'FT', 'IF', 'TF'))
	DROP PROCEDURE[dbo].[insertarAlumno]
GO

CREATE PROCEDURE [dbo].[insertarAlumno]
	-- Add the parameters for the stored procedure here
	@carnet nvarchar(7),
	@nombre nvarchar(25),
	@apellido nvarchar(25),
	@direccion nvarchar(300),
	@fechanacimiento date,
	@fechaingreso date,
	@genero nvarchar(15),
	@estado varchar(5)


AS
BEGIN

	insert into tb_alumno (carnet, 
	nombre, 
	apellido, 
	direccion, 
	fechanacimiento, 
	fechaingreso,
	genero, estado) 
	values(@carnet,
	@nombre,
	@apellido,
	@direccion,
	@fechanacimiento,
	@fechaingreso,
	@genero,
	@estado
	)
END
GO


-- ----------------------------
-- procedure structure for insertarCategoria
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[insertarCategoria]') AND type IN ('FN', 'FS', 'FT', 'IF', 'TF'))
	DROP PROCEDURE[dbo].[insertarCategoria]
GO

CREATE PROCEDURE [dbo].[insertarCategoria]
	-- Add the parameters for the stored procedure here
	@codigocategoria nvarchar(5),
	@nombrecategoria nvarchar(50)
AS
BEGIN

	insert into tb_categoria( codigocategoria, nombrecategoria) 
	values (@codigocategoria, @nombrecategoria)
END
GO


-- ----------------------------
-- procedure structure for insertarLibro
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[insertarLibro]') AND type IN ('FN', 'FS', 'FT', 'IF', 'TF'))
	DROP PROCEDURE[dbo].[insertarLibro]
GO

CREATE PROCEDURE [dbo].[insertarLibro]
	-- Add the parameters for the stored procedure here
	@codigolibro nvarchar(5),
	@titulolibro nvarchar(100),
	@existencia int,
	@codigocategoria nvarchar(5),
	@precio decimal(14,2),
	@codigoautor nvarchar(5)
AS
BEGIN

	insert into tb_libro( codigolibro,
	titulolibro,
	existencia,
	codigocategoria,
	precio,
	codigoautor)
	values (@codigolibro,
	@titulolibro,
	@existencia,
	@codigocategoria,
	@precio,
	@codigoautor)
END
GO


-- ----------------------------
-- procedure structure for insertarPrestamoalumno
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[insertarPrestamoalumno]') AND type IN ('FN', 'FS', 'FT', 'IF', 'TF'))
	DROP PROCEDURE[dbo].[insertarPrestamoalumno]
GO

CREATE PROCEDURE [dbo].[insertarPrestamoalumno]
	-- Add the parameters for the stored procedure here
	@carnet_alumno nvarchar(7),
	@codigo_libro nvarchar(5),
	@fche_prestamo date,
	@codigo_prestamo nvarchar(5),
	@cantidadprestamo int,
	@fecha_devolucion date
	

AS
BEGIN

	insert into tb_prestamo_alumno( carnet_alumno,
	codigo_libro,
	fche_prestamo,
	codigo_prestamo,
	cantidadprestamo,
	fecha_devolucion)

	values (@carnet_alumno,
	@codigo_libro,
	@fche_prestamo,
	@codigo_prestamo,
	@cantidadprestamo,
	@fecha_devolucion)
END
GO


-- ----------------------------
-- procedure structure for eliminarAlumno
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[eliminarAlumno]') AND type IN ('FN', 'FS', 'FT', 'IF', 'TF'))
	DROP PROCEDURE[dbo].[eliminarAlumno]
GO

CREATE PROCEDURE [dbo].[eliminarAlumno]
@carnet nvarchar(7)
AS
BEGIN
DELETE FROM tb_alumno where carnet=@carnet
END
GO


-- ----------------------------
-- procedure structure for eliminarAutor
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[eliminarAutor]') AND type IN ('FN', 'FS', 'FT', 'IF', 'TF'))
	DROP PROCEDURE[dbo].[eliminarAutor]
GO

CREATE PROCEDURE [dbo].[eliminarAutor]
@codigoautor nvarchar(5)

AS
BEGIN
DELETE FROM tb_autor where codigoautor=@codigoautor

END
GO


-- ----------------------------
-- procedure structure for eliminarCategoria
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[eliminarCategoria]') AND type IN ('FN', 'FS', 'FT', 'IF', 'TF'))
	DROP PROCEDURE[dbo].[eliminarCategoria]
GO

CREATE PROCEDURE [dbo].[eliminarCategoria]
@codigocategoria nvarchar(5)

AS
BEGIN
DELETE FROM tb_categoria where codigocategoria=@codigocategoria

END
GO


-- ----------------------------
-- procedure structure for eliminarLibro
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[eliminarLibro]') AND type IN ('FN', 'FS', 'FT', 'IF', 'TF'))
	DROP PROCEDURE[dbo].[eliminarLibro]
GO

CREATE PROCEDURE [dbo].[eliminarLibro]
@codigolibro nvarchar(5)

AS
BEGIN
DELETE FROM tb_libro where codigolibro=@codigolibro

END
GO


-- ----------------------------
-- procedure structure for eliminarPrestamoalumno
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[eliminarPrestamoalumno]') AND type IN ('FN', 'FS', 'FT', 'IF', 'TF'))
	DROP PROCEDURE[dbo].[eliminarPrestamoalumno]
GO

CREATE PROCEDURE [dbo].[eliminarPrestamoalumno]
@codigoprestamo nvarchar(5)

AS
BEGIN
DELETE FROM tb_prestamo_alumno where codigo_prestamo=@codigoprestamo

END
GO


-- ----------------------------
-- Checks structure for table tb_alumno
-- ----------------------------
ALTER TABLE [dbo].[tb_alumno] ADD CONSTRAINT [CK_alumnogenero] CHECK ([tb_alumno].[genero]='Femenino' OR [tb_alumno].[genero]='Masculino')
GO

ALTER TABLE [dbo].[tb_alumno] ADD CONSTRAINT [CK_fechas] CHECK ([tb_alumno].[fechanacimiento]<[tb_alumno].[fechaingreso])
GO


-- ----------------------------
-- Primary Key structure for table tb_alumno
-- ----------------------------
ALTER TABLE [dbo].[tb_alumno] ADD CONSTRAINT [tb_alumno_pkey] PRIMARY KEY CLUSTERED ([carnet])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table tb_autor
-- ----------------------------
ALTER TABLE [dbo].[tb_autor] ADD CONSTRAINT [tb_autor_pkey] PRIMARY KEY CLUSTERED ([codigoautor])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table tb_categoria
-- ----------------------------
ALTER TABLE [dbo].[tb_categoria] ADD CONSTRAINT [tb_categoria_pkey] PRIMARY KEY CLUSTERED ([codigocategoria])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table tb_libro
-- ----------------------------
ALTER TABLE [dbo].[tb_libro] ADD CONSTRAINT [tb_libro_pkey] PRIMARY KEY CLUSTERED ([codigolibro])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Checks structure for table tb_prestamo_alumno
-- ----------------------------
ALTER TABLE [dbo].[tb_prestamo_alumno] ADD CONSTRAINT [CK_fechasprestamo] CHECK ([tb_prestamo_alumno].[fche_prestamo]<[tb_prestamo_alumno].[fecha_devolucion])
GO


-- ----------------------------
-- Primary Key structure for table tb_prestamo_alumno
-- ----------------------------
ALTER TABLE [dbo].[tb_prestamo_alumno] ADD CONSTRAINT [tb_prestamo_alumno_pkey] PRIMARY KEY CLUSTERED ([codigo_prestamo])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table tb_usuarios
-- ----------------------------
ALTER TABLE [dbo].[tb_usuarios] ADD CONSTRAINT [PK__tb_usuar__080A9743D42A3E04] PRIMARY KEY CLUSTERED ([idusuario])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Foreign Keys structure for table tb_libro
-- ----------------------------
ALTER TABLE [dbo].[tb_libro] ADD CONSTRAINT [fk_autor] FOREIGN KEY ([codigoautor]) REFERENCES [dbo].[tb_autor] ([codigoautor]) ON DELETE CASCADE ON UPDATE CASCADE
GO

ALTER TABLE [dbo].[tb_libro] ADD CONSTRAINT [fk_categoria] FOREIGN KEY ([codigocategoria]) REFERENCES [dbo].[tb_categoria] ([codigocategoria]) ON DELETE CASCADE ON UPDATE CASCADE
GO


-- ----------------------------
-- Foreign Keys structure for table tb_prestamo_alumno
-- ----------------------------
ALTER TABLE [dbo].[tb_prestamo_alumno] ADD CONSTRAINT [fk_alumno] FOREIGN KEY ([carnet_alumno]) REFERENCES [dbo].[tb_alumno] ([carnet]) ON DELETE CASCADE ON UPDATE CASCADE
GO

ALTER TABLE [dbo].[tb_prestamo_alumno] ADD CONSTRAINT [fk_libro] FOREIGN KEY ([codigo_libro]) REFERENCES [dbo].[tb_libro] ([codigolibro]) ON DELETE CASCADE ON UPDATE CASCADE
GO

