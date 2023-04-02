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

 Date: 02/04/2023 16:59:56
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

INSERT INTO [dbo].[tb_alumno] ([carnet], [nombre], [apellido], [direccion], [fechanacimiento], [fechaingreso], [genero], [estado]) VALUES (N'FC16056', N'Fabricio', N'Corvera', N'Santo Domin , San Vicente', N'1991-09-27', N'2016-01-16', N'Masculino', N't')
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
  [codi autor] nvarchar(5) COLLATE Modern_Spanish_CI_AS  NOT NULL,
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

INSERT INTO [dbo].[tb_autor] ([codi autor], [nombreautor], [apellidoautor], [fechanacimientoautor], [nacionalidad]) VALUES (N'1', N'Fabricio', N'CorverU', N'1997-09-27', N'Nacional')
GO

INSERT INTO [dbo].[tb_autor] ([codi autor], [nombreautor], [apellidoautor], [fechanacimientoautor], [nacionalidad]) VALUES (N'2', N'Cristian', N'Mejía', N'1986-09-27', N'Nacional')
GO

COMMIT
GO


-- ----------------------------
-- Table structure for tb_cate ria
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[tb_cate ria]') AND type IN ('U'))
	DROP TABLE [dbo].[tb_cate ria]
GO

CREATE TABLE [dbo].[tb_cate ria] (
  [codi cate ria] nvarchar(5) COLLATE Modern_Spanish_CI_AS  NOT NULL,
  [nombrecate ria] nvarchar(50) COLLATE Modern_Spanish_CI_AS  NOT NULL
)
GO

ALTER TABLE [dbo].[tb_cate ria] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of tb_cate ria
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[tb_cate ria] ([codi cate ria], [nombrecate ria]) VALUES (N'1', N'Terrorifico')
GO

INSERT INTO [dbo].[tb_cate ria] ([codi cate ria], [nombrecate ria]) VALUES (N'2', N'Drama')
GO

INSERT INTO [dbo].[tb_cate ria] ([codi cate ria], [nombrecate ria]) VALUES (N'3', N'Ficcioncita')
GO

INSERT INTO [dbo].[tb_cate ria] ([codi cate ria], [nombrecate ria]) VALUES (N'4', N'Acción')
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
  [codi libro] nvarchar(5) COLLATE Modern_Spanish_CI_AS  NOT NULL,
  [titulolibro] nvarchar(100) COLLATE Modern_Spanish_CI_AS  NOT NULL,
  [existencia] int  NOT NULL,
  [codi cate ria] nvarchar(5) COLLATE Modern_Spanish_CI_AS  NOT NULL,
  [precio] decimal(14,2)  NOT NULL,
  [codi autor] nvarchar(5) COLLATE Modern_Spanish_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[tb_libro] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of tb_libro
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[tb_libro] ([codi libro], [titulolibro], [existencia], [codi cate ria], [precio], [codi autor]) VALUES (N'1234', N'Pepito', N'65', N'1', N'0.35', N'1')
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
  [codi _libro] nvarchar(5) COLLATE Modern_Spanish_CI_AS  NOT NULL,
  [fche_prestamo] date  NOT NULL,
  [codi _prestamo] nvarchar(5) COLLATE Modern_Spanish_CI_AS  NOT NULL,
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

INSERT INTO [dbo].[tb_prestamo_alumno] ([carnet_alumno], [codi _libro], [fche_prestamo], [codi _prestamo], [cantidadprestamo], [fecha_devolucion]) VALUES (N'CM16057', N'1234', N'2023-04-14', N'1', N'2', N'2025-04-23')
GO

COMMIT
GO


-- ----------------------------
-- View structure for vistalibros
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[vistalibros]') AND type IN ('V'))
	DROP VIEW [dbo].[vistalibros]
GO

CREATE VIEW [dbo].[vistalibros] AS SELECT l.[codi libro] as codigolibro, l.titulolibro , l.existencia , c.[nombrecate ria] as nombrecategoria, l.precio, a.nombreautor FROM db_libros.dbo.tb_libro as l 
inner join db_libros.dbo.tb_autor as a on a.[codi autor] = l.[codi autor] 
inner join db_libros.dbo.[tb_cate ria] as c on c.[codi cate ria] = l.[codi cate ria];
GO


-- ----------------------------
-- View structure for vistaprestamos
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[vistaprestamos]') AND type IN ('V'))
	DROP VIEW [dbo].[vistaprestamos]
GO

CREATE VIEW [dbo].[vistaprestamos] AS SELECT p.[codi _prestamo] as codiprestamo, p.carnet_alumno, a.apellido, a.nombre, p.[codi _libro] as codilibro, l.titulolibro, p.fche_prestamo, p.cantidadprestamo, p.fecha_devolucion
FROM db_libros.dbo.tb_prestamo_alumno as p 
INNER JOIN db_libros.dbo.tb_alumno as a on p.carnet_alumno = a.carnet
INNER JOIN db_libros.dbo.tb_libro as l on p.[codi _libro] = l.[codi libro]
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
ALTER TABLE [dbo].[tb_autor] ADD CONSTRAINT [tb_autor_pkey] PRIMARY KEY CLUSTERED ([codi autor])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table tb_cate ria
-- ----------------------------
ALTER TABLE [dbo].[tb_cate ria] ADD CONSTRAINT [tb_cate ria_pkey] PRIMARY KEY CLUSTERED ([codi cate ria])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table tb_libro
-- ----------------------------
ALTER TABLE [dbo].[tb_libro] ADD CONSTRAINT [tb_libro_pkey] PRIMARY KEY CLUSTERED ([codi libro])
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
ALTER TABLE [dbo].[tb_prestamo_alumno] ADD CONSTRAINT [tb_prestamo_alumno_pkey] PRIMARY KEY CLUSTERED ([codi _prestamo])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Foreign Keys structure for table tb_libro
-- ----------------------------
ALTER TABLE [dbo].[tb_libro] ADD CONSTRAINT [fk_autor] FOREIGN KEY ([codi autor]) REFERENCES [dbo].[tb_autor] ([codi autor]) ON DELETE CASCADE ON UPDATE CASCADE
GO

ALTER TABLE [dbo].[tb_libro] ADD CONSTRAINT [fk_cate ria] FOREIGN KEY ([codi cate ria]) REFERENCES [dbo].[tb_cate ria] ([codi cate ria]) ON DELETE CASCADE ON UPDATE CASCADE
GO


-- ----------------------------
-- Foreign Keys structure for table tb_prestamo_alumno
-- ----------------------------
ALTER TABLE [dbo].[tb_prestamo_alumno] ADD CONSTRAINT [fk_alumno] FOREIGN KEY ([carnet_alumno]) REFERENCES [dbo].[tb_alumno] ([carnet]) ON DELETE CASCADE ON UPDATE CASCADE
GO

ALTER TABLE [dbo].[tb_prestamo_alumno] ADD CONSTRAINT [fk_libro] FOREIGN KEY ([codi _libro]) REFERENCES [dbo].[tb_libro] ([codi libro]) ON DELETE CASCADE ON UPDATE CASCADE
GO

