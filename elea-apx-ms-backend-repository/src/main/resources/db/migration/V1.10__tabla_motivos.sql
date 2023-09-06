CREATE TABLE motivo_tipo
(
    id     int IDENTITY (1,1) NOT NULL,
    nombre varchar(100)       NULL,
    CONSTRAINT motivo_tipo_PK PRIMARY KEY (id)
)
GO

CREATE TABLE motivo
(
    id          int IDENTITY (1,1) NOT NULL,
    id_tipo     int                NOT NULL,
    nombre      varchar(100)       NOT NULL,
    CONSTRAINT motivo_PK PRIMARY KEY (id),
    CONSTRAINT motivo_tipo_FK FOREIGN KEY (id_tipo) REFERENCES motivo_tipo (id)
)
GO