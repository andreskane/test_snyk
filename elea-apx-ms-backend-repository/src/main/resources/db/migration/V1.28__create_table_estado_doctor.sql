INSERT INTO motivo_tipo (nombre) VALUES ('visita_fallida'), ('previsto'),('baja_doctor');
GO

INSERT INTO motivo (id_tipo, nombre)
VALUES
(3, 'Bajo Potencial'),
(3, 'Duplicado'),
(3, 'Fallecimiento'),
(3, 'Jubilación'),
(3, 'Licencia'),
(3, 'Mudanza'),
(3, 'No Atiende Más'),
(3, 'No Recibe APM'),
(3, 'Rezonificación');
GO

CREATE TABLE estado_doctor (
     id int IDENTITY(1,1) NOT NULL,
     doctor_id int NOT NULL,
     motivo_estado_id int NOT NULL,
     comentarios varchar(255) NULL,
     estado varchar(20) NOT NULL,
     created_date datetime NOT NULL,
     modified_by varchar(100) NOT NULL,
     modified_date datetime NOT NULL,
     CONSTRAINT estado_doctor_PK PRIMARY KEY (id),
     CONSTRAINT doctor_FK FOREIGN KEY (doctor_id) REFERENCES doctor(id),
     CONSTRAINT motivo_baja_FK FOREIGN KEY (motivo_estado_id) REFERENCES motivo(id)
)
GO
