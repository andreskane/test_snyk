CREATE TABLE linea (
	id int IDENTITY(1,1) NOT NULL,
    nombre varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    CONSTRAINT linea_PK PRIMARY KEY (id)
) go