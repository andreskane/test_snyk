CREATE PROCEDURE [dbo].[Reporte_Apm]
AS
BEGIN

SELECT
d.id,
TRIM(CONCAT(trim(a.primerNombre), ' ',  TRIM(a.segundoNombre))) nombre,
TRIM(CONCAT(TRIM(a.primerApellido), ' ', trim(a.segundoApellido))) apellido,
a.legajo,
SUBSTRING(a.email, 0, CHARINDEX('@', a.email)) nombreUsuario,
TRIM(CONCAT(TRIM(u.nombre), ' ',  TRIM(u.apellido))) gerenteRegional,
STRING_AGG(li.nombre, ', ') lineas,
a.fecha_nacimiento fechaNacimiento,
a.inactivo,
CONCAT(p.nombre, ' ',
l.nombre, ' ',
ci.nombre, ' ',
c.nombre, ' ',
d.numero) direccionParticular,
CONCAT(p1.nombre, ' ',
l1.nombre, ' ',
ci1.nombre, ' ',
c1.nombre, ' ',
d1.numero) direccionEntrega,
a.telefono,
a.email,
a.modified_date ultActualizacion
FROM APM a
JOIN usuario u ON u.id = a.gerente_regional_id
LEFT JOIN linea_apm la ON la.id_apm = a.id
LEFT JOIN linea li ON li.id=la.id_linea
LEFT JOIN direccion d ON d.id = a.id_direccion_particular
LEFT JOIN codigo_postal cp on cp.id = d.id_codigo_postal
LEFT JOIN provincia p on p.id = cp.id_provincia
LEFT JOIN localidad l on l.id = cp.id_localidad
LEFT JOIN ciudad ci on ci.id = cp.id_ciudad
LEFT JOIN calle c ON c.id = cp.id_calle
LEFT JOIN direccion d1 ON d1.id = a.id_direccion_particular
LEFT JOIN codigo_postal cp1 on cp1.id = d1.id_codigo_postal
LEFT JOIN provincia p1 on p1.id = cp1.id_provincia
LEFT JOIN localidad l1 on l1.id = cp1.id_localidad
LEFT JOIN ciudad ci1 on ci1.id = cp1.id_ciudad
LEFT JOIN calle c1 ON c1.id = cp1.id_calle
GROUP BY a.primerNombre, a.segundoNombre, a.primerApellido, a.segundoApellido, a.legajo,
a.email, u.nombre, u.apellido, a.fecha_nacimiento, a.inactivo, p.nombre,
l.nombre, ci.nombre, c.nombre, d.numero , d.id, p1.nombre, l1.nombre, ci1.nombre, c1.nombre, d1.numero,a.telefono,
a.email, a.modified_date ;

END