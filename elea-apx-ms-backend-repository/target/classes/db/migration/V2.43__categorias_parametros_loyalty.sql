CREATE TABLE categoria_doctor (
	id int IDENTITY(1,1) NOT NULL,
	nombre varchar(100) NULL,
	puntaje int NOT NULL,
	CONSTRAINT categoria_PK PRIMARY KEY (id)
)

INSERT INTO categoria_doctor (nombre, puntaje)
VALUES
('Sin categoría',10),
('Bronce',19),
('Plata',29),
('Oro',38),
('Platino',39)

ALTER TABLE doctor ADD categoria_id int NULL DEFAULT 1  WITH VALUES

ALTER TABLE doctor ADD CONSTRAINT doctor_categoria_FK FOREIGN KEY (categoria_id) REFERENCES categoria_doctor(id)

CREATE TABLE prioridad_loyalty (
	id int IDENTITY(1,1) NOT NULL,
	multiplicador NUMERIC(4,2) NOT NULL,
	CONSTRAINT prioridad_loyalty_PK PRIMARY KEY (id),
)

INSERT INTO prioridad_loyalty (multiplicador)
VALUES
(3.0),
(2.5),
(2.0),
(1.5),
(0.5),
(0.5)

CREATE TABLE parametros_loyalty (
	id int IDENTITY(1,1) NOT NULL,
	descripcion varchar(100) NULL,
	prioridad_id int NOT NULL,
	limite_superior int NOT NULL,
	puntaje_superior int NOT NULL,
	limite_alto int NOT NULL,
	puntaje_alto int NOT NULL,
	limite_medio int NOT NULL,
	puntaje_medio int NOT NULL,
	puntaje_bajo int NOT NULL,
	limite_inferior int NOT NULL,
	puntaje_inferior int NOT NULL,
	CONSTRAINT parametros_loyalty_PK PRIMARY KEY (id),
	CONSTRAINT prioridad_parametros_loyalty_FK FOREIGN KEY (prioridad_id) REFERENCES prioridad_loyalty(id)
)

INSERT INTO parametros_loyalty (descripcion, prioridad_id, limite_superior, puntaje_superior, limite_alto, puntaje_alto, 
limite_medio, puntaje_medio, puntaje_bajo, limite_inferior, puntaje_inferior)
VALUES
('Cantidad de Prescripciones Generales',5,3300,5,1600,4,660,3,2,200,1),
('Prescripciones ELEA',1,50,5,30,4,20,3,2,10,1),
('Productos Grilla promocional de Marketing',2,50,5,30,4,20,3,2,10,1)

CREATE TABLE prescripciones_doctor (
	id int IDENTITY(1,1) NOT NULL,
	doctor_id int NULL,
	fecha datetime NOT NULL,
	codigo_laboratorio varchar(100) NOT NULL,
	prescripciones int NOT NULL,
	codigo_producto varchar(100) NOT NULL,
	CONSTRAINT prescripciones_doctor_PK PRIMARY KEY (id),
	CONSTRAINT doctor_prescripciones_doctor_FK FOREIGN KEY (doctor_id) REFERENCES doctor(id)
)

GO