ALTER TABLE doctor ADD EntidadMatriculaNacional_id int NULL;
ALTER TABLE doctor ADD EntidadMatriculaProvincial_id int NULL;
ALTER TABLE provincia ADD EsEntidadMatriculanteProvincial bit NULL;
ALTER TABLE doctor ADD CONSTRAINT doctor_ent_mat_nacional_FK FOREIGN KEY (EntidadMatriculaNacional_id) REFERENCES provincia(id);
ALTER TABLE doctor ADD CONSTRAINT doctor_ent_mat_provincial_FK FOREIGN KEY (EntidadMatriculaProvincial_id) REFERENCES provincia(id);