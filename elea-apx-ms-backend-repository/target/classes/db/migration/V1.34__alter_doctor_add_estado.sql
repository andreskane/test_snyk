ALTER TABLE doctor ADD estado_doctor_id int NULL,
    CONSTRAINT doctor_estado_doctor_FK FOREIGN KEY (estado_doctor_id) REFERENCES estado_doctor (id)
GO