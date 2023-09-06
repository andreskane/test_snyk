CREATE TABLE gerente_regional (
	id int IDENTITY(0,1) NOT NULL,
	nombre varchar(100) NOT NULL,
	apellido varchar(100) NOT NULL,
	email varchar(100) NOT NULL,
	legajo int NOT NULL,
    CONSTRAINT gerente_regional_PK PRIMARY KEY (id)
) GO

ALTER TABLE apm ADD fecha_nacimiento date NULL GO
ALTER TABLE apm ADD telefono varchar(100) NULL GO
ALTER TABLE apm ADD gerente_regional_id int NULL GO
ALTER TABLE apm ADD dias_registro_visita int NULL GO
ALTER TABLE apm ADD legajo int NULL GO