CREATE VIEW catalogo_productos_muestras_view
AS
select 
ct.cod_familia as codigoProducto,
ct.familia_producto as nombreProducto,
ct.cod_grupo codigoPresentaciones,
ct.productos  as presentaciones,
ct.cod_linea as codigoLinea,
ct.linea_negocio as linea
from catalogo_productos_staging ct
where 
ct.familia_producto <> 'Servicios' and 
ct.unidad_negocio <> 'EXPORTACIONES' and
ct.unidad_negocio <> 'Discontinuado' and 
ct.productos like '%MM';