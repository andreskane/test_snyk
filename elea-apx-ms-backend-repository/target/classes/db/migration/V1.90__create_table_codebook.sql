CREATE TABLE codebook (
	id int IDENTITY(0,1) NOT NULL,
    apellido varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    nombre varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    fecha_creacion_medico datetime NULL,
    matricula_nacional varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    matricula_provincial varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    f_turnos int NULL,
    r_turnos datetime NULL,
    f_hce int NULL,
    r_hce datetime NULL,
    f_recetas int NULL,
    r_recetas datetime NULL,
    CONSTRAINT codebook_PK PRIMARY KEY (id)
) go