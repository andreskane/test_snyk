CREATE TABLE apm_calificacion_doctor (
	id int IDENTITY(0,1) NOT NULL,
	id_apm int NOT NULL,
	id_doctor int NOT NULL,
	calificacion_doctor int NOT NULL,
	fecha_calificacion date,
	proxima_calificacion date
	CONSTRAINT apm_calificacion_doctor_pk PRIMARY KEY (id)
);
GO