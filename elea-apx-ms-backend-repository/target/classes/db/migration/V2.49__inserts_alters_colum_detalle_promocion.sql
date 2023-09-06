ALTER TABLE detalle_promocion_producto ADD estado_ciclo int NULL;
ALTER TABLE detalle_promocion_producto ADD estado_grilla varchar(50) NULL;
ALTER TABLE detalle_promocion_producto ALTER COLUMN id_familia_producto int NULL;
GO