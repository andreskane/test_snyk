


CREATE TABLE material_promocional_tipo
(
    id     int IDENTITY (0,1) NOT NULL,
    nombre varchar(100)       NULL,
    CONSTRAINT material_promocional_tipo_PK PRIMARY KEY (id)
)
GO


CREATE TABLE material_promocional
(
    id          int IDENTITY (0,1) NOT NULL,
    id_tipo     int                NOT NULL,
    nombre      varchar(100)       NOT NULL,
    descripcion varchar(255)       NULL,
    CONSTRAINT material_promocional_PK PRIMARY KEY (id),
    CONSTRAINT material_promocional_FK FOREIGN KEY (id_tipo) REFERENCES material_promocional_tipo (id)
)
GO
