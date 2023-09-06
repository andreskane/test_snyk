ALTER TABLE datos_visita ADD CONSTRAINT datos_visita_especialidad_FK FOREIGN KEY (especialidad_id) REFERENCES especialidad(id);
ALTER TABLE doctor ADD CONSTRAINT doctor_especialidad_FK FOREIGN KEY (especialidad_id) REFERENCES especialidad(id);
go

