CREATE TABLE producto_tipo
(
    id     int IDENTITY (1,1) NOT NULL,
    nombre varchar(100)       NULL,
    CONSTRAINT producto_tipo_PK PRIMARY KEY (id)
)
GO


CREATE TABLE producto
(
    id          int IDENTITY (1,1) NOT NULL,
    id_tipo     int                NOT NULL,
    nombre      varchar(100)       NOT NULL,
    descripcion varchar(255)       NULL,
    CONSTRAINT producto_PK PRIMARY KEY (id),
    CONSTRAINT producto_tipo_FK FOREIGN KEY (id_tipo) REFERENCES producto_tipo (id)
)
GO

CREATE TABLE visita_producto
(
    id int IDENTITY(1,1) NOT NULL,
    agenda_id int NOT NULL,
    producto_id int NOT NULL,
    CONSTRAINT visita_producto_PK PRIMARY KEY (id),
    CONSTRAINT visita_producto_agenda_FK FOREIGN KEY (agenda_id) REFERENCES agenda(id),
    CONSTRAINT visita_producto_producto_FK FOREIGN KEY (producto_id) REFERENCES producto(id)
)
GO