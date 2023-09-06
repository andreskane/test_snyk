IF NOT EXISTS (
 SELECT *
  FROM   sys.columns
  WHERE  object_id = OBJECT_ID(N'[catalogo_productos]')
         AND name = 'grupo_familia'
)
BEGIN
   ALTER TABLE catalogo_productos ADD grupo_familia varchar(255) NULL
END
GO

IF NOT EXISTS (
 SELECT *
  FROM   sys.columns
  WHERE  object_id = OBJECT_ID(N'[catalogo_productos]')
         AND name = 'familia_producto'
)
BEGIN
   ALTER TABLE catalogo_productos ADD familia_producto varchar(255) NULL
END
GO

IF NOT EXISTS (
 SELECT *
  FROM   sys.columns
  WHERE  object_id = OBJECT_ID(N'[catalogo_productos]')
         AND name = 'unidad_negocio'
)
BEGIN
   ALTER TABLE catalogo_productos ADD unidad_negocio varchar(255) NULL
END
GO

IF NOT EXISTS (
 SELECT *
  FROM   sys.columns
  WHERE  object_id = OBJECT_ID(N'[catalogo_productos]')
         AND name = 'especialidad'
)
BEGIN
   ALTER TABLE catalogo_productos ADD especialidad varchar(255) NULL
END
GO

IF NOT EXISTS (
 SELECT *
  FROM   sys.columns
  WHERE  object_id = OBJECT_ID(N'[catalogo_productos]')
         AND name = 'precio_vigencia'
)
BEGIN
   ALTER TABLE catalogo_productos ADD precio_vigencia datetime NULL
END
GO
