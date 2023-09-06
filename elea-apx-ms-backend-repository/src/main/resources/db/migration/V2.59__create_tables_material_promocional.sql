CREATE TABLE material_promocional_especialidades (
	id_material_promocional int NULL,
	id_especialidades int NULL
);
GO

CREATE TABLE material_promocional_productos (
	id_material_promocional int NULL,
	id_producto int NULL
);
GO

CREATE TABLE material_promocional_ciclos (
	id_material_promocional int NULL,
	id_ciclo int NULL
);
GO

ALTER TABLE material_promocional ADD tamanio varchar(25) NULL;
GO