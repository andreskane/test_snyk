DROP TABLE prescripciones_doctor;

CREATE TABLE prescripciones_doctor (
	id int IDENTITY(1,1) NOT NULL,
	doctor_id int NULL,
	fecha datetime NOT NULL,
	codigo_laboratorio varchar(100) NOT NULL,
	nombre_laboratorio varchar(100) NULL,
	codigo_producto varchar(100) NOT NULL,
	nombre_producto varchar(100) NULL,
	marca varchar(100) NOT NULL,
	prescripciones int NOT NULL,
	CONSTRAINT prescripciones_doctor_PK PRIMARY KEY (id),
	CONSTRAINT doctor_prescripciones_doctor_FK FOREIGN KEY (doctor_id) REFERENCES doctor(id)
)

CREATE TABLE marca_familia_producto (
	codigo_marca varchar(50) NOT NULL,
	id_familia_producto int NOT NULL
)
GO

CREATE PROCEDURE calcular_prescripciones_doctor
AS

INSERT INTO prescripciones_doctor(doctor_id, fecha, codigo_laboratorio, nombre_laboratorio,codigo_producto, nombre_producto, marca, prescripciones)
SELECT DISTINCT [doctor].id AS doctor_id, CONVERT(smalldatetime,[cup_prescripcion].DATA+'01') AS fecha, [cup_prescripcion].CDGLAB AS codigo_laboratorio, 
[cup_laboratorio].descripcion_laboratorio AS nombre_laboratorio, [cup_prescripcion].CDGPRO codigo_producto, [cup_producto].NOME AS nombre_producto, [cup_producto].MARCA AS marca, 
CAST([cup_prescripcion].PX1 AS INT) AS prescripciones
  FROM [cup_prescripcion]
  JOIN [cup_medico] on [cup_prescripcion].CDGMED = [cup_medico].CDGMED
  JOIN [doctor] on [cup_medico].CRM LIKE '%'+[doctor].matriculaProvincial OR [cup_medico].CRM LIKE '%'+[doctor].matriculaNacional AND [cup_medico].TIPO_DOM='C'
  JOIN [cup_producto] on [cup_producto].CODIGO = [cup_prescripcion].CDGPRO
  LEFT JOIN [cup_laboratorio] on [cup_laboratorio].codigo_laboratorio = [cup_prescripcion].CDGLAB
  WHERE [doctor].id IN (SELECT TOP(10) DOCTOR.id FROM DOCTOR)
  AND [cup_prescripcion].DATA IN ('202106','202107','202108')
  ORDER BY [doctor].id, fecha

GO