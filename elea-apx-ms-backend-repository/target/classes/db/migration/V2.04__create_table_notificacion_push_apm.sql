CREATE TABLE notificacion_push_apm (
	id int IDENTITY(0,1) NOT NULL,
	id_notificacion int NOT NULL,
	id_apm int NOT NULL,
	CONSTRAINT notificacionpush_apm_PK PRIMARY KEY (id)
);
ALTER TABLE notificacion_push_apm ADD CONSTRAINT notificacion_push_apm_FK FOREIGN KEY (id_apm) REFERENCES apm(id);
ALTER TABLE notificacion_push_apm ADD CONSTRAINT notificacion_push_notificacion_FK FOREIGN KEY (id_notificacion) REFERENCES notificacion_push(id);
