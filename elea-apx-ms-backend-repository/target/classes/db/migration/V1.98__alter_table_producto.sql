ALTER TABLE producto ADD id_linea int NULL GO
ALTER TABLE producto ADD id_especialidad int NULL GO
ALTER TABLE producto ADD CONSTRAINT linea_producto_FK FOREIGN KEY (id_linea) REFERENCES linea (id) GO
ALTER TABLE producto ADD CONSTRAINT especialidad_producto_FK FOREIGN KEY (id_especialidad) REFERENCES especialidad (id) GO
