CREATE TABLE categoria_doctor (
	id int IDENTITY(0,1) NOT NULL,
	nombre varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	puntaje int NOT NULL,
	CONSTRAINT categoria_doctor_id_PK PRIMARY KEY (id)
);

INSERT INTO categoria_doctor (nombre,puntaje) VALUES
	 ('Sin categoría',10),
	 ('Bronce',19),
	 ('Plata',29),
	 ('Oro',38),
	 ('Platino',39);


