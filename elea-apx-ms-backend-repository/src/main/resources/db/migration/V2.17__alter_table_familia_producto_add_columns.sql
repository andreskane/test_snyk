IF NOT EXISTS (
 SELECT *
  FROM   sys.columns
  WHERE  object_id = OBJECT_ID(N'[familia_producto]')
         AND name = 'nombre_corto'
)
BEGIN
   ALTER TABLE familia_producto ADD nombre_corto varchar(255) NULL
END
GO
