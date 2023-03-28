/*
 Navicat Premium Data Transfer

 Source Server         : POSTGRES
 Source Server Type    : PostgreSQL
 Source Server Version : 140007
 Source Host           : localhost:5432
 Source Catalog        : db_libros
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 140007
 File Encoding         : 65001

 Date: 28/03/2023 09:31:12
*/


-- ----------------------------
-- Table structure for tb_alumno
-- ----------------------------
DROP TABLE IF EXISTS "public"."tb_alumno";
CREATE TABLE "public"."tb_alumno" (
  "carnet" varchar(7) COLLATE "pg_catalog"."default" NOT NULL,
  "nombre" varchar(25) COLLATE "pg_catalog"."default" NOT NULL,
  "apellido" varchar(25) COLLATE "pg_catalog"."default" NOT NULL,
  "direccion" varchar(300) COLLATE "pg_catalog"."default" NOT NULL,
  "fechanacimiento" date NOT NULL,
  "fechaingreso" date NOT NULL,
  "genero" varchar(15) COLLATE "pg_catalog"."default" NOT NULL,
  "estado" bool NOT NULL
)
;

-- ----------------------------
-- Records of tb_alumno
-- ----------------------------
INSERT INTO "public"."tb_alumno" VALUES ('FC16056', 'Fabricio', 'Corvera', 'Santo Domingo, San Vicente', '1991-09-27', '2016-01-16', 'Masculino', 't');

-- ----------------------------
-- Table structure for tb_autor
-- ----------------------------
DROP TABLE IF EXISTS "public"."tb_autor";
CREATE TABLE "public"."tb_autor" (
  "codigoautor" varchar(5) COLLATE "pg_catalog"."default" NOT NULL,
  "nombreautor" varchar(60) COLLATE "pg_catalog"."default",
  "apellidoautor" varchar(35) COLLATE "pg_catalog"."default",
  "fechanacimientoautor" date,
  "nacionalidad" varchar(40) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of tb_autor
-- ----------------------------
INSERT INTO "public"."tb_autor" VALUES ('2', 'Cristian', 'Mejía', '1986-09-27', 'Nacional');
INSERT INTO "public"."tb_autor" VALUES ('1', 'Fabricio', 'CorverU', '1997-09-27', 'Nacional');

-- ----------------------------
-- Table structure for tb_categoria
-- ----------------------------
DROP TABLE IF EXISTS "public"."tb_categoria";
CREATE TABLE "public"."tb_categoria" (
  "codigocategoria" varchar(5) COLLATE "pg_catalog"."default" NOT NULL,
  "nombrecategoria" varchar(50) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Records of tb_categoria
-- ----------------------------
INSERT INTO "public"."tb_categoria" VALUES ('1', 'Terrorifico');
INSERT INTO "public"."tb_categoria" VALUES ('4', 'Acción');
INSERT INTO "public"."tb_categoria" VALUES ('3', 'Ficcioncita');
INSERT INTO "public"."tb_categoria" VALUES ('2', 'Drama');

-- ----------------------------
-- Table structure for tb_libro
-- ----------------------------
DROP TABLE IF EXISTS "public"."tb_libro";
CREATE TABLE "public"."tb_libro" (
  "codigolibro" varchar(5) COLLATE "pg_catalog"."default" NOT NULL,
  "titulolibro" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "existencia" int4 NOT NULL,
  "codigocategoria" varchar(5) COLLATE "pg_catalog"."default" NOT NULL,
  "precio" numeric(14,2) NOT NULL,
  "codigoautor" varchar(5) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of tb_libro
-- ----------------------------

-- ----------------------------
-- Table structure for tb_prestamo_alumno
-- ----------------------------
DROP TABLE IF EXISTS "public"."tb_prestamo_alumno";
CREATE TABLE "public"."tb_prestamo_alumno" (
  "carnet_alumno" varchar(7) COLLATE "pg_catalog"."default" NOT NULL,
  "codigo_libro" varchar(5) COLLATE "pg_catalog"."default" NOT NULL,
  "fche_prestamo" date NOT NULL,
  "codigo_prestamo" varchar(5) COLLATE "pg_catalog"."default" NOT NULL,
  "cantidadprestamo" int4 NOT NULL
)
;

-- ----------------------------
-- Records of tb_prestamo_alumno
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table tb_alumno
-- ----------------------------
ALTER TABLE "public"."tb_alumno" ADD CONSTRAINT "tb_alumno_pkey" PRIMARY KEY ("carnet");

-- ----------------------------
-- Primary Key structure for table tb_autor
-- ----------------------------
ALTER TABLE "public"."tb_autor" ADD CONSTRAINT "tb_autor_pkey" PRIMARY KEY ("codigoautor");

-- ----------------------------
-- Primary Key structure for table tb_categoria
-- ----------------------------
ALTER TABLE "public"."tb_categoria" ADD CONSTRAINT "tb_categoria_pkey" PRIMARY KEY ("codigocategoria");

-- ----------------------------
-- Primary Key structure for table tb_libro
-- ----------------------------
ALTER TABLE "public"."tb_libro" ADD CONSTRAINT "tb_libro_pkey" PRIMARY KEY ("codigolibro");

-- ----------------------------
-- Primary Key structure for table tb_prestamo_alumno
-- ----------------------------
ALTER TABLE "public"."tb_prestamo_alumno" ADD CONSTRAINT "tb_prestamo_alumno_pkey" PRIMARY KEY ("codigo_prestamo");

-- ----------------------------
-- Foreign Keys structure for table tb_libro
-- ----------------------------
ALTER TABLE "public"."tb_libro" ADD CONSTRAINT "fk_autor" FOREIGN KEY ("codigoautor") REFERENCES "public"."tb_autor" ("codigoautor") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "public"."tb_libro" ADD CONSTRAINT "fk_categoria" FOREIGN KEY ("codigocategoria") REFERENCES "public"."tb_categoria" ("codigocategoria") ON DELETE RESTRICT ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Keys structure for table tb_prestamo_alumno
-- ----------------------------
ALTER TABLE "public"."tb_prestamo_alumno" ADD CONSTRAINT "fk_alumno" FOREIGN KEY ("carnet_alumno") REFERENCES "public"."tb_alumno" ("carnet") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "public"."tb_prestamo_alumno" ADD CONSTRAINT "fk_libro" FOREIGN KEY ("codigo_libro") REFERENCES "public"."tb_libro" ("codigolibro") ON DELETE RESTRICT ON UPDATE CASCADE;
