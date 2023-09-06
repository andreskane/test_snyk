ALTER TABLE agenda
    ADD visita_exitosa bit NULL,
    observaciones varchar (255) NULL,
    acompanante varchar (100) NULL,
    direccion varchar (255) NULL,
    perfil varchar (100) NULL,
    agenda_motivo_fallida_id int NULL,
    lastUpdated datetime NULL,
    CONSTRAINT agenda_motivo_fallida_FK FOREIGN KEY (agenda_motivo_fallida_id) REFERENCES motivo(id)
GO

CREATE TABLE agenda_producto
(
    id                   int IDENTITY (0,1) NOT NULL,
    id_agenda            int                NOT NULL,
    id_producto          int                NOT NULL,
    CONSTRAINT agenda_producto_PK PRIMARY KEY (id),
    CONSTRAINT agenda_producto_agenda_FK FOREIGN KEY (id_agenda) REFERENCES agenda (id),
    CONSTRAINT agenda_producto_producto_FK FOREIGN KEY (id_producto) REFERENCES producto (id)
)
GO