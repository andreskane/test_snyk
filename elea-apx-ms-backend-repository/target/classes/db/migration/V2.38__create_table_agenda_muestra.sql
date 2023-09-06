CREATE TABLE agenda_muestra (
	id_agenda int NOT NULL,
	nombre varchar(300) COLLATE SQL_Latin1_General_CP1_CI_AI NOT NULL,
	cantidad int NOT NULL,
	id int IDENTITY(0,1) NOT NULL
);

ALTER TABLE agenda_muestra ADD CONSTRAINT agenda_muestra_agenda_FK FOREIGN KEY (id_agenda) REFERENCES agenda(id);
go