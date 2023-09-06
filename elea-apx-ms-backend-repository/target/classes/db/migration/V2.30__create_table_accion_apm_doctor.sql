CREATE TABLE accion_apm_doctor (
	id int IDENTITY(0,1) NOT NULL,
	id_doctor int NOT NULL,
	id_accion int NOT NULL
);

ALTER TABLE accion_apm_doctor ADD CONSTRAINT accion_apm_doctor_accion_FK FOREIGN KEY (id_accion) REFERENCES accion_apm(id);
ALTER TABLE accion_apm_doctor ADD CONSTRAINT accion_apm_doctor_doctor_FK FOREIGN KEY (id_doctor) REFERENCES doctor(id);