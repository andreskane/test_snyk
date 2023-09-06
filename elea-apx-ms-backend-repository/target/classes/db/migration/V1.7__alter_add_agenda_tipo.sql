CREATE TABLE agenda_tipo
(
    id     int IDENTITY (0,1) NOT NULL,
    nombre varchar(100)       NULL,
    CONSTRAINT agenda_tipo_PK PRIMARY KEY (id)
)GO


INSERT INTO agenda_tipo (nombre)
VALUES ('Visita'),
       ('Parte Diario'),
       ('Previsto');
GO


ALTER TABLE agenda
    ADD agenda_tipo_id int NULL
    CONSTRAINT agenda_tipo_FK FOREIGN KEY REFERENCES agenda_tipo(id),
    institucion_id int NULL
    CONSTRAINT agenda_institucion_FK REFERENCES institucion(id);
GO

UPDATE agenda set agenda_tipo_id = 0;
GO

ALTER TABLE agenda
    ALTER COLUMN agenda_tipo_id int NOT NULL;
GO
