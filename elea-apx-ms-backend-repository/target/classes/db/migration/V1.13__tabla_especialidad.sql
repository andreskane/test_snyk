CREATE TABLE especialidad
(
    id int IDENTITY (1,1) NOT NULL,
    nombre varchar(100)       NULL,
)GO

ALTER TABLE doctor ADD especialidad_id int NULL GO
ALTER TABLE doctor ADD especialidadSecundaria_id int NULL GO
