ALTER TABLE material_promocional ADD inactivo bit NULL
GO

UPDATE material_promocional SET inactivo = 0 WHERE inactivo IS NULL
GO