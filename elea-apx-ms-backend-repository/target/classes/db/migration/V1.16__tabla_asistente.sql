CREATE TABLE asistente (
    id int IDENTITY(1,1) NOT NULL,
    nombre varchar(100) NULL,
    apellido varchar(100) NULL,
    fechaNacimiento date NULL,
    email varchar(100) NULL,
    telefono varchar(100) NULL,
    CONSTRAINT asistente_PK PRIMARY KEY (id)
) GO
