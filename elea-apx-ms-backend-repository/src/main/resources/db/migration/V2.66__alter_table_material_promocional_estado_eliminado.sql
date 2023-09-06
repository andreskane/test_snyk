ALTER TABLE material_promocional ADD estado_eliminado bit NULL;
GO

UPDATE material_promocional SET estado_eliminado = 0;
GO