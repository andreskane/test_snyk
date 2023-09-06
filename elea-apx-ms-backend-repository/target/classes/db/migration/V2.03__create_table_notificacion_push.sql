CREATE TABLE notificacion_push (
	id int IDENTITY(0,1) NOT NULL,
	titulo varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	descripcion varchar(1000) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	accion varchar(500) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	topico varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	token varchar(2000) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	estado varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	tipo varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	created_date datetime NULL,
	modified_date datetime NULL,
	modified_by varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	hora_prevista time(0) NULL,
	CONSTRAINT notificacion_push_PK PRIMARY KEY (id)
);
