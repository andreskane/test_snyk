ALTER TABLE agenda_producto DROP CONSTRAINT agenda_producto_producto_FK;
ALTER TABLE agenda_producto ADD CONSTRAINT agenda_producto_familia_FK FOREIGN KEY (id_producto) REFERENCES familia_producto(id);