DROP TABLE prescripciones_doctor;

CREATE TABLE prescripciones_doctor (
	id int IDENTITY(1,1) NOT NULL,
	doctor_id int NULL,
	fecha datetime NOT NULL,
	codigo_laboratorio varchar(100) NOT NULL,
	nombre_laboratorio varchar(100) NULL,
	codigo_producto varchar(100) NOT NULL,
	nombre_producto varchar(100) NULL,
	prescripciones int NOT NULL,
	CONSTRAINT prescripciones_doctor_PK PRIMARY KEY (id),
	CONSTRAINT doctor_prescripciones_doctor_FK FOREIGN KEY (doctor_id) REFERENCES doctor(id)
)
GO