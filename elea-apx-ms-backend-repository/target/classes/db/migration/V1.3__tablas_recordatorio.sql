CREATE TABLE recordatorio
(
    id                   int IDENTITY (0,1) NOT NULL,
    id_agenda            int                NOT NULL,
    fecha_completado     datetime           NULL,
    id_doctor            int                NULL,
    id_institucion       int                NULL,
    id_agenda_completado int                NULL,
    CONSTRAINT NewTable_PK PRIMARY KEY (id),
    CONSTRAINT recordatorio_FK FOREIGN KEY (id_doctor) REFERENCES doctor (id),
    CONSTRAINT recordatorio_FK_1 FOREIGN KEY (id_agenda) REFERENCES agenda (id),
    CONSTRAINT recordatorio_FK_2 FOREIGN KEY (id_institucion) REFERENCES institucion (id),
    CONSTRAINT recordatorio_FK_3 FOREIGN KEY (id_agenda_completado) REFERENCES agenda (id)
)
GO

CREATE TABLE recordatorio_item_tipo
(
    id     int IDENTITY (1,1)                                NOT NULL,
    nombre varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    CONSTRAINT recordatorio_item_tipo_PK PRIMARY KEY (id)
)
GO

INSERT INTO recordatorio_item_tipo (nombre)
VALUES ('Objetivo'),
       ('Muestra'),
       ('Material Impreso');

CREATE TABLE recordatorio_item
(
    id                      int IDENTITY (1,1)                                NOT NULL,
    id_recordatorio         int                                               NOT NULL,
    id_tipo                 int                                               NOT NULL,
    descripcion             varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    id_material_promocional int                                               NULL,
    CONSTRAINT recordatorio_item_PK PRIMARY KEY (id),
    CONSTRAINT recordatorio_item_FK FOREIGN KEY (id_tipo) REFERENCES material_promocional_tipo (id),
    CONSTRAINT recordatorio_item_FK_1 FOREIGN KEY (id_recordatorio) REFERENCES recordatorio (id)
)
GO
