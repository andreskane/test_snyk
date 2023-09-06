IF NOT EXISTS (
 SELECT *
  FROM   sys.columns
  WHERE  object_id = OBJECT_ID(N'[familia_producto]')
         AND name = 'principio_activo'
)
BEGIN
   ALTER TABLE familia_producto ADD principio_activo varchar(2048) NULL
END
GO

IF NOT EXISTS (
 SELECT *
  FROM   sys.columns
  WHERE  object_id = OBJECT_ID(N'[familia_producto]')
         AND name = 'muestraMedica'
)
BEGIN
   ALTER TABLE familia_producto ADD muestraMedica bit DEFAULT 0 NOT NULL
END
GO

IF NOT EXISTS (
 SELECT *
  FROM   sys.columns
  WHERE  object_id = OBJECT_ID(N'[familia_producto]')
         AND name = 'obra_social'
)
BEGIN
   ALTER TABLE familia_producto ADD obra_social bit DEFAULT 0 NOT NULL
END
GO

IF NOT EXISTS (
 SELECT *
  FROM   sys.columns
  WHERE  object_id = OBJECT_ID(N'[familia_producto]')
         AND name = 'prepaga'
)
BEGIN
   ALTER TABLE familia_producto ADD prepaga bit DEFAULT 0 NOT NULL
END
GO

IF NOT EXISTS (
 SELECT *
  FROM   sys.columns
  WHERE  object_id = OBJECT_ID(N'[familia_producto]')
         AND name = 'en_stock'
)
BEGIN
   ALTER TABLE familia_producto ADD en_stock bit DEFAULT 0 NOT NULL
END
GO

IF NOT EXISTS (
 SELECT *
  FROM   sys.columns
  WHERE  object_id = OBJECT_ID(N'[familia_producto]')
         AND name = 'linea'
)
BEGIN
   ALTER TABLE familia_producto ADD linea varchar(2048) NULL
END
GO


IF NOT EXISTS (
 SELECT *
  FROM   sys.columns
  WHERE  object_id = OBJECT_ID(N'[familia_producto]')
         AND name = 'url_prospecto'
)
BEGIN
   ALTER TABLE familia_producto ADD url_prospecto varchar(2048) NULL
END
GO

IF NOT EXISTS (
 SELECT *
  FROM   sys.columns
  WHERE  object_id = OBJECT_ID(N'[familia_producto]')
         AND name = 'nombre'
)
BEGIN
   ALTER TABLE familia_producto ADD nombre varchar(255) NULL
END
GO

IF NOT EXISTS (
 SELECT *
  FROM   sys.columns
  WHERE  object_id = OBJECT_ID(N'[familia_producto]')
         AND name = 'descripcion'
)
BEGIN
   ALTER TABLE familia_producto ALTER COLUMN descripcion varchar(2048) COLLATE SQL_Latin1_General_CP1_CI_AS NULL
END
GO