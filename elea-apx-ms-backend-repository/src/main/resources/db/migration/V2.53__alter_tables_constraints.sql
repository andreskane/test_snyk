ALTER TABLE apm_caja_recepcion ADD CONSTRAINT apm_caja_recepcion_FK FOREIGN KEY (id_apm) REFERENCES apm(id);
ALTER TABLE apm ADD CONSTRAINT apm_email_legajo_UN UNIQUE (email,legajo);

ALTER TABLE cartera_medica_estado ADD CONSTRAINT cartera_medica_estado_apm_FK FOREIGN KEY (apm_id) REFERENCES apm(id);
ALTER TABLE cartera_medica_estado ADD CONSTRAINT cartera_medica_estado_doctor_FK FOREIGN KEY (doctor_id) REFERENCES doctor(id);
ALTER TABLE cartera_medica_estado ADD CONSTRAINT cartera_medica_estado_usuario_FK FOREIGN KEY (gerente_id) REFERENCES usuario(id);
ALTER TABLE cartera_medica_estado ADD CONSTRAINT cartera_medica_estado_aprobador_FK FOREIGN KEY (aprobador_id) REFERENCES usuario(id);
ALTER TABLE cartera_medica_estado ADD CONSTRAINT cartera_medica_estado_motivo_FK FOREIGN KEY (motivo_estado_id) REFERENCES motivo(id);

DELETE FROM conexion_apm where not EXISTS (select * from apm a where a.id=conexion_apm.id_apm);

ALTER TABLE conexion_apm ADD CONSTRAINT conexion_apm_FK FOREIGN KEY (id_apm) REFERENCES apm(id);

ALTER TABLE conexion_gerente ADD CONSTRAINT conexion_gerente_FK FOREIGN KEY (id_gerente) REFERENCES usuario(id);

ALTER TABLE datos_visita ADD CONSTRAINT datos_visita_institucion_FK FOREIGN KEY (institucion_id) REFERENCES institucion(id);
ALTER TABLE datos_visita ADD CONSTRAINT datos_visita_asistente_FK FOREIGN KEY (asistente_id) REFERENCES asistente(id);

DELETE FROM datos_visita_horario_visita WHERE NOT EXISTS (SELECT * FROM datos_visita WHERE datos_visita.id=datos_visita_horario_visita.datos_visita_id);
ALTER TABLE datos_visita_horario_visita ADD CONSTRAINT datos_visita_horario_visita_FK FOREIGN KEY (datos_visita_id) REFERENCES datos_visita(id); --NO

ALTER TABLE feedback ADD CONSTRAINT feedback_FK FOREIGN KEY (idApm) REFERENCES apm(id);

ALTER TABLE localidad ADD CONSTRAINT localidad_FK FOREIGN KEY (id_provincia) REFERENCES provincia(id);
ALTER TABLE tag_doctor ADD CONSTRAINT tag_doctor_FK FOREIGN KEY (doctor_id) REFERENCES doctor(id);
GO