CREATE TABLE accion_apm (
	id int IDENTITY(0,1) NOT NULL,
	created_date datetime DEFAULT getdate() NULL,
	usuario varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	tipo_accion varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	apm_id int NULL,
	modified_date datetime NULL,
	modified_by varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	destinatarios varchar(3000) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT accion_apm_PK PRIMARY KEY (id)
);
ALTER TABLE accion_apm ADD CONSTRAINT accion_apm_FK FOREIGN KEY (apm_id) REFERENCES apm(id);