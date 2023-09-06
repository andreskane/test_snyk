ALTER TABLE doctor ADD CONSTRAINT doctor_especialidad_secundaria_FK FOREIGN KEY (especialidadSecundaria_id ) REFERENCES especialidad(id);
