CREATE TABLE datos_visita (
    id int IDENTITY(0,1) NOT NULL,
    institucion_id int NULL,
    direccion varchar(255) NULL,
    turno varchar(100) NOT NULL,
    especialidad_id int NULL,
    frecuencia int NOT NULL,
    asistente_id int NULL,
    created_date datetime NOT NULL,
    modified_by varchar(100) NOT NULL,
    modified_date datetime NOT NULL
) GO

CREATE TABLE horario_visita (
      id int IDENTITY(0,1) NOT NULL,
      dia int NOT NULL,
      desde varchar(100) NOT NULL,
      hasta varchar(100) NOT NULL,

) GO

CREATE TABLE datos_visita_horario_visita (
        horario_visita_id int NOT NULL,
        datos_visita_id int NOT NULL

) GO