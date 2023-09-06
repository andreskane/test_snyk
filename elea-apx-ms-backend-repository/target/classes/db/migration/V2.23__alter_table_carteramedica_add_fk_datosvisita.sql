ALTER TABLE cartera_medica ADD CONSTRAINT cartera_datos_visita_FK FOREIGN KEY (datos_visita_id) REFERENCES datos_visita(id);
