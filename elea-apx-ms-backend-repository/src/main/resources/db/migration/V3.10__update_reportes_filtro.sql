UPDATE REPORTE SET filtro = 1 where nombre not like 'Listado de APMS';
UPDATE REPORTE SET filtro = 0 where nombre like 'Listado de APMS';
GO