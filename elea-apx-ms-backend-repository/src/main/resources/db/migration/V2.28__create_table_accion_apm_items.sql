CREATE TABLE accion_apm_items (
	id_accion_apm int NOT NULL,
	id_material_promocional int NOT NULL,
	id int IDENTITY(0,1) NOT NULL,
	CONSTRAINT accion_apm_items_PK PRIMARY KEY (id)
);

ALTER TABLE accion_apm_items ADD CONSTRAINT accion_apm_items_mateial_FK FOREIGN KEY (id_material_promocional) REFERENCES material_promocional(id);
ALTER TABLE accion_apm_items ADD CONSTRAINT accion_apm_items_FK FOREIGN KEY (id_accion_apm) REFERENCES accion_apm(id);