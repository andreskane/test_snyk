DROP VIEW catalogo_productos_muestras_view;
go

CREATE VIEW catalogo_productos_muestras_view
AS
select
REPLACE(LTRIM(REPLACE(ct.cod_sap,'0',' ')),' ','0') as codigoSap,
ct.cod_familia as codigoProducto,
ct.familia_producto as nombreProducto,
REPLACE(LTRIM(REPLACE(ct.cod_sap,'0',' ')),' ','0') as codigoPresentaciones,
ct.productos  as presentaciones,
ct.cod_linea as codigoLinea,
ct.linea_negocio as linea
from catalogo_productos_staging ct
where
ct.familia_producto <> 'Servicios' and
ct.unidad_negocio <> 'EXPORTACIONES' and
ct.unidad_negocio <> 'Discontinuado' and
ct.productos like '%MM';
go