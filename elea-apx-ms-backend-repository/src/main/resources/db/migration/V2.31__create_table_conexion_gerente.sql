CREATE TABLE conexion_gerente (
	id int IDENTITY(1,1) NOT NULL,
	id_gerente int NOT NULL,
	fecha datetime NOT NULL,
	CONSTRAINT conexion_gerente_pk PRIMARY KEY (id)
);