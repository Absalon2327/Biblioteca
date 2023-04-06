/*
 Navicat Premium Data Transfer

 Source Server         : SQLServer
 Source Server Type    : SQL Server
 Source Server Version : 15004298
 Source Host           : localhost:1433
 Source Catalog        : db_libros
 Source Schema         : dbo

 Target Server Type    : SQL Server
 Target Server Version : 15004298
 File Encoding         : 65001

 Date: 06/04/2023 11:07:47
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
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[tb_alumno] ([carnet], [nombre], [apellido], [direccion], [fechanacimiento], [fechaingreso], [genero], [estado]) VALUES (N'CM16057', N'Cristian', N'Mejía', N'San Vicente', N'1997-09-27', N'2016-01-16', N'Masculino', N't')
GO

INSERT INTO [dbo].[tb_alumno] ([carnet], [nombre], [apellido], [direccion], [fechanacimiento], [fechaingreso], [genero], [estado]) VALUES (N'FC16056', N'Fabricio', N'Corvera', N'Santo Domingo, San Vicente', N'1991-09-27', N'2016-01-16', N'Masculino', N't')
GO

COMMIT
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
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[tb_autor] ([codigoautor], [nombreautor], [apellidoautor], [fechanacimientoautor], [nacionalidad]) VALUES (N'1', N'Fabricio', N'CorverU', N'1997-09-27', N'Nacional')
GO

INSERT INTO [dbo].[tb_autor] ([codigoautor], [nombreautor], [apellidoautor], [fechanacimientoautor], [nacionalidad]) VALUES (N'2', N'Cristian', N'Mejía', N'1986-09-27', N'Nacional')
GO

COMMIT
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
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[tb_categoria] ([codigocategoria], [nombrecategoria]) VALUES (N'1', N'Terrorifico')
GO

INSERT INTO [dbo].[tb_categoria] ([codigocategoria], [nombrecategoria]) VALUES (N'2', N'Drama')
GO

INSERT INTO [dbo].[tb_categoria] ([codigocategoria], [nombrecategoria]) VALUES (N'3', N'Ficcioncita')
GO

INSERT INTO [dbo].[tb_categoria] ([codigocategoria], [nombrecategoria]) VALUES (N'4', N'Acción')
GO

COMMIT
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
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[tb_libro] ([codigolibro], [titulolibro], [existencia], [codigocategoria], [precio], [codigoautor]) VALUES (N'12345', N'Don Quijote de la Mancha', N'25', N'1', N'0.25', N'1')
GO

INSERT INTO [dbo].[tb_libro] ([codigolibro], [titulolibro], [existencia], [codigocategoria], [precio], [codigoautor]) VALUES (N'35214', N'Harry Potter', N'14', N'3', N'2.00', N'2')
GO

INSERT INTO [dbo].[tb_libro] ([codigolibro], [titulolibro], [existencia], [codigocategoria], [precio], [codigoautor]) VALUES (N'75123', N'Pepito', N'96', N'4', N'0.60', N'1')
GO

INSERT INTO [dbo].[tb_libro] ([codigolibro], [titulolibro], [existencia], [codigocategoria], [precio], [codigoautor]) VALUES (N'78546', N'Alicia en el país de las maravillas', N'47', N'2', N'0.47', N'2')
GO

INSERT INTO [dbo].[tb_libro] ([codigolibro], [titulolibro], [existencia], [codigocategoria], [precio], [codigoautor]) VALUES (N'95123', N'Mago de Oz', N'31', N'1', N'0.95', N'1')
GO

COMMIT
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
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[tb_prestamo_alumno] ([carnet_alumno], [codigo_libro], [fche_prestamo], [codigo_prestamo], [cantidadprestamo], [fecha_devolucion]) VALUES (N'CM16057', N'35214', N'2022-04-01', N'1', N'5', N'2022-04-05')
GO

COMMIT
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

