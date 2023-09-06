CREATE TABLE apm (
	id int IDENTITY(1,1) NOT NULL,
	primerNombre varchar(100) NULL,
	segundoNombre varchar(100) NULL,
	primerApellido varchar(100) NULL,
	segundoApellido varchar(100) NULL,
	email varchar(100) NULL,
	CONSTRAINT apm_PK PRIMARY KEY (id)
) GO

CREATE TABLE doctor (
	id int IDENTITY(1,1) NOT NULL,
	primerNombre varchar(100) NULL,
	segundoNombre varchar(100) NULL,
	primerApellido varchar(100) NULL,
	segundoApellido varchar(100) NULL,
	fechaNacimiento date NULL,
    email varchar(100) NULL,
	matriculaNacional varchar(100) NULL,
	matriculaProvincial varchar(100) NULL,
    CONSTRAINT doctor_PK PRIMARY KEY (id),
) GO

CREATE TABLE agenda (
	id int IDENTITY(1,1) NOT NULL,
	inicio datetime NOT NULL,
	fin datetime NULL,
	apm_id int NULL,
	doctor_id int NULL,
	descripcion varchar(255) NULL,
	lugar varchar(100) NULL,
	turno varchar(50) NULL,
	CONSTRAINT agenda_PK PRIMARY KEY (id),
	CONSTRAINT agenda_apm_FK FOREIGN KEY (apm_id) REFERENCES apm(id),
	CONSTRAINT agenda_doctor_FK FOREIGN KEY (doctor_id) REFERENCES doctor(id)
) GO

