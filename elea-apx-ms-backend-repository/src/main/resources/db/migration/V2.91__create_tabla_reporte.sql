CREATE TABLE reporte (
	id int IDENTITY(0,1) NOT NULL,
	nombre varchar(300) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	url varchar(500) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	inactivo bit NULL,
	CONSTRAINT reporte_PK PRIMARY KEY (id)
);