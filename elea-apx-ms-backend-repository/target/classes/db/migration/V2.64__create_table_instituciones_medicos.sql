CREATE TABLE instituciones_medicos (
	id int IDENTITY(0,1) NOT NULL,
	id_medico int NULL,
	id_institucion int NULL,
	active bit DEFAULT 1 NOT NULL,
	CONSTRAINT instituciones_medicos_pk PRIMARY KEY (id),
	CONSTRAINT uq_instituciones_medicos UNIQUE (id_medico,id_institucion),
	CONSTRAINT instituciones_medicos_doctor_id_fk FOREIGN KEY (id_medico) REFERENCES doctor(id),
	CONSTRAINT instituciones_medicos_institucion_id_fk FOREIGN KEY (id_institucion) REFERENCES institucion(id),
);
