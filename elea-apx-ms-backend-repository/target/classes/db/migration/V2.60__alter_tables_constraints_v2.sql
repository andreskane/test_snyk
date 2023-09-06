ALTER TABLE apm ADD CONSTRAINT apm_gerente_FK FOREIGN KEY (gerente_regional_id) REFERENCES usuario(id);
ALTER TABLE apm_authentication ADD CONSTRAINT apm_authentication_apm_FK FOREIGN KEY (id_apm) REFERENCES apm(id);
ALTER TABLE apm_calificacion_doctor ADD CONSTRAINT apm_calificacion_doctor_apm_FK FOREIGN KEY (id_apm) REFERENCES apm(id);
ALTER TABLE apm_calificacion_doctor ADD CONSTRAINT apm_calificacion_doctor_doctor_FK FOREIGN KEY (id_doctor) REFERENCES doctor(id);
ALTER TABLE codigo_postal ADD CONSTRAINT codigo_postal_provincia_FK FOREIGN KEY (id_provincia) REFERENCES provincia(id);

ALTER TABLE material_promocional_ciclos ADD CONSTRAINT material_promocional_ciclos_id_FK FOREIGN KEY (id_material_promocional) REFERENCES material_promocional(id);
ALTER TABLE material_promocional_ciclos ADD CONSTRAINT material_promocional_id_ciclo_FK FOREIGN KEY (id_ciclo) REFERENCES ciclo(id);
ALTER TABLE material_promocional_especialidades ADD CONSTRAINT material_promocional_id_especialidades_FK FOREIGN KEY (id_material_promocional) REFERENCES material_promocional(id);
ALTER TABLE material_promocional_especialidades ADD CONSTRAINT material_promocional_especialidades_id_FK FOREIGN KEY (id_especialidades) REFERENCES especialidad(id);
ALTER TABLE material_promocional_linea_asociada ADD CONSTRAINT material_promocional_id_linea_FK FOREIGN KEY (id_material_promocional) REFERENCES material_promocional(id);
ALTER TABLE material_promocional_linea_asociada ADD CONSTRAINT material_promocional_linea_id_FK FOREIGN KEY (id_linea) REFERENCES linea(id);
ALTER TABLE material_promocional_productos ADD CONSTRAINT material_promocional_id_productos_FK FOREIGN KEY (id_material_promocional) REFERENCES material_promocional(id);
ALTER TABLE material_promocional_productos ADD CONSTRAINT material_promocional_productos_id_FK FOREIGN KEY (id_producto) REFERENCES familia_producto(id);

ALTER TABLE tag ADD PRIMARY KEY (id)
ALTER TABLE tag_doctor ADD CONSTRAINT tag_id_FK FOREIGN KEY (tag_id) REFERENCES tag(id);
GO