CREATE TABLE email (
	id int IDENTITY(0,1) NOT NULL,
	asunto varchar(300) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	mensaje varchar(3000) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	fecha_envio datetime NULL,
	id_accion int NULL,
	CONSTRAINT email_PK PRIMARY KEY (id)
);

ALTER TABLE email ADD CONSTRAINT email_accion_FK FOREIGN KEY (id_accion) REFERENCES accion_apm(id);