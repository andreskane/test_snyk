CREATE TABLE material_promocional_adjunto (
	id int IDENTITY(0,1) NOT NULL,
	archivo varbinary(MAX) NULL,
	id_material int NULL,
	CONSTRAINT material_promocional_adjunto_PK PRIMARY KEY (id)
);
ALTER TABLE material_promocional_adjunto ADD CONSTRAINT material_promocional_adjunto_FK FOREIGN KEY (id_material) REFERENCES material_promocional(id);