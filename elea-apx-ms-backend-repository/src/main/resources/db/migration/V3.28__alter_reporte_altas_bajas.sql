ALTER PROCEDURE Reporte_Aprobacion_Altas_Bajas @ciclo int
AS
BEGIN
	SELECT
	(CASE WHEN u.apellido is not null OR u.nombre is not null
				THEN CONCAT(u.apellido, ', ', u.nombre)
				ELSE null
			END) [gerenteRegional],
	TRIM(CONCAT(TRIM(a.primerNombre), ' ',  TRIM(a.segundoNombre), ' ', CONCAT(TRIM(a.primerApellido), ' ', trim(a.segundoApellido)))) as apm,
	a.legajo,
	cme.estado,
	TRIM(CONCAT(trim(d.primerNombre), ' ',  TRIM(d.segundoNombre))) nombreMedico,
	TRIM(CONCAT(TRIM(d.primerApellido), ' ', trim(d.segundoApellido))) apellidoMedico,
	e.nombre as especialidad,
	d.matriculaNacional,
	d.matriculaProvincial,
	CASE  cme.estado  WHEN 'BAJA_APROBADA' THEN mt.nombre ELSE '' END as motivo,
	p.nombre as provincia,
	l.nombre as localidad,
	c.nombre as ciudad,
	c2.nombre as calle,
	cp.cpa as cp,
	d2.numero as numero,
	cme.comentarios,
	(CASE WHEN u2.apellido is not null OR u2.nombre is not null
				THEN CONCAT(u2.apellido, ', ', u2.nombre)
				ELSE null
			END) aprobador,
	cme.fecha_solicitud as fechaSolicitud,
	(SELECT STRING_AGG(TRIM(CONCAT(TRIM(a1.primerNombre), ' ',  TRIM(a1.segundoNombre),  ' ', CONCAT(TRIM(a1.primerApellido), ' ', trim(a1.segundoApellido)))), ', ') APM
	from apm a1
	JOIN cartera_medica cm2 ON cm2.apm_id = a1.id
	WHERE cm2.doctor_id = d.id and cm2.inactivo = 0 and cm2.apm_id <> a.id) apmsAsociados,
	(SELECT COUNT(*)
	from apm a1
	JOIN cartera_medica cm2 ON cm2.apm_id = a1.id
	WHERE cm2.doctor_id = d.id and cm2.inactivo = 0 and cm2.apm_id <> a.id) nroApmsAsociados
	FROM cartera_medica_estado cme
	JOIN cartera_medica cm ON cm.cartera_medica_estado_id = cme.id
	JOIN apm a ON a.id=cm.apm_id
	JOIN doctor d ON d.id=cm.doctor_id
	JOIN usuario u ON u.id = a.gerente_regional_id
	JOIN especialidad e ON e.id=d.especialidad_id
	LEFT JOIN motivo mt ON (mt.id = cme.motivo_estado_id )
	LEFT JOIN datos_visita dv ON dv.id=cm.datos_visita_id
	LEFT JOIN direccion d2 ON d2.id=dv.id_direccion
	LEFT JOIN codigo_postal cp ON cp.id = d2.id_codigo_postal
	LEFT JOIN provincia p ON p.id = cp.id_provincia
	LEFT JOIN localidad l ON l.id = cp.id_localidad
	LEFT JOIN ciudad c ON c.id = cp.id_ciudad
	LEFT JOIN calle c2 ON c2.id = cp.id_calle
	LEFT JOIN usuario u2 ON u2.id=cme.aprobador_id
	INNER JOIN [ciclo] ci ON ci.id = @ciclo
	WHERE cme.estado IN ('ALTA_APROBADA', 'BAJA_APROBADA','ACTIVACION_APROBADA')
	AND cme.fecha_solicitud BETWEEN ci.inicio AND ci.fin
	GROUP BY u.apellido, u.nombre , u2.apellido, u2.nombre , a.primerNombre, a.segundoNombre , a.primerApellido , a.segundoApellido, a.legajo, cme.estado ,
	d.primerNombre, d.segundoNombre, d.primerApellido, d.segundoApellido, e.nombre,d.matriculaNacional,
	d.matriculaProvincial, mt.nombre, p.nombre, l.nombre, c.nombre, cp.cpa, d2.numero, cme.comentarios, cme.fecha_solicitud,CM.ID,c2.nombre,d.id, a.id

END;
