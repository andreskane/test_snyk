create table fidelea (
	id int IDENTITY(1,1) NOT NULL,
	tipomatricula varchar(100) NULL,
	nromatricula varchar(100) NULL,
	apellido varchar(100) NULL,
	nombre varchar(100) NULL,
  fecUltimoConsumo datetime NULL,
  consumos int NULL,
	CONSTRAINT fidelea_PK PRIMARY KEY (id)
) go