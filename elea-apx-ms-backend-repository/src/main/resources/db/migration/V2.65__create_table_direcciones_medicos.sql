CREATE TABLE direcciones_medicos (
	id int IDENTITY(0,1) NOT NULL,
	id_medico int NOT NULL,
	id_direccion int NOT NULL,
	active bit DEFAULT 1 NOT NULL,
	CONSTRAINT direcciones_medicos_pk PRIMARY KEY (id),
	CONSTRAINT uq_direcciones_medicos UNIQUE (id_medico,id_direccion),
	CONSTRAINT direcciones_medicos_direccion FOREIGN KEY (id_direccion) REFERENCES direccion(id),
	CONSTRAINT direcciones_medicos_medico FOREIGN KEY (id_medico) REFERENCES doctor(id),
);