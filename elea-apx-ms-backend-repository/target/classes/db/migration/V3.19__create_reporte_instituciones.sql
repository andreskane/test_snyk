CREATE PROCEDURE [dbo].[Reporte_Instituciones] AS
BEGIN
	SELECT
	i.nombre,
	razon_social razonSocial,
	p.nombre provincia,
	l.nombre localidad,
	c.nombre ciudad,
	cp.cpa,
	c2.nombre calle,
	d.numero
	FROM institucion i
	JOIN direccion d on d.id=i.id_direccion
	JOIN codigo_postal cp ON cp.id = d.id_codigo_postal
	JOIN provincia p ON p.id = cp.id_provincia
	JOIN localidad l ON l.id = cp.id_localidad
	JOIN ciudad c ON c.id = cp.id_ciudad
	JOIN calle c2 ON c2.id = cp.id_calle
	WHERE i.inactivo  = 0;
END