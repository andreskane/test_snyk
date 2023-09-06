EXEC sp_rename 'doctor.categoria_id', 'loyalty_id', 'COLUMN';

ALTER TABLE doctor ADD categoria_id int NULL;

ALTER TABLE doctor ADD CONSTRAINT doctor_categoria_id_FK FOREIGN KEY (categoria_id) REFERENCES categoria_doctor(id);

EXEC sp_rename 'doctor_categoria_FK', 'doctor_loyalty_FK';

