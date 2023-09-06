CREATE TABLE institucion_tipo
(
    id   int IDENTITY (1,1) NOT NULL,
    name varchar(100)       NULL,
    CONSTRAINT tipo_institucion_PK PRIMARY KEY (id)
)
GO

CREATE TABLE institucion_categoria
(
    id     int IDENTITY (1,1) NOT NULL,
    nombre varchar(100)       NULL,
    CONSTRAINT categoria_institucion_PK PRIMARY KEY (id)
)
GO

CREATE TABLE institucion
(
    id           int IDENTITY (1,1) NOT NULL,
    nombre       varchar(100)       NOT NULL,
    privado      bit                NOT NULL,
    id_categoria int                NOT NULL,
    id_tipo      int                NOT NULL,
    razon_social varchar(100)       NULL,

    CONSTRAINT institucion_PK PRIMARY KEY (id),
    CONSTRAINT institucion_FK FOREIGN KEY (id_tipo) REFERENCES institucion_tipo (id),
    CONSTRAINT institucion_FK_1 FOREIGN KEY (id_categoria) REFERENCES institucion_categoria (id)
)
GO