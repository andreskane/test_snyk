ALTER PROCEDURE Reporte_Cartera_Medica @ciclo int
AS
BEGIN
	SET Language 'Spanish';
	SELECT
	ci.nombre ciclo,
	TRIM(CONCAT(TRIM(u.nombre), ' ',  TRIM(u.apellido))) gerenteRegional,
	TRIM(CONCAT(TRIM(a.primerApellido), ' ', trim(a.segundoApellido))) apellidoApm,
	TRIM(CONCAT(trim(a.primerNombre), ' ',  TRIM(a.segundoNombre))) nombreApm,
	a.legajo,
	STRING_AGG(li.nombre, ', ') lineas,
	cm.id codRelacion,
	cm.doctor_id codMedico,
	TRIM(CONCAT(TRIM(d.primerApellido), ' ', trim(d.segundoApellido))) apellidoDoctor,
	TRIM(CONCAT(trim(d.primerNombre), ' ',  TRIM(d.segundoNombre))) nombreDoctor,
	d.matriculaNacional,
	d.matriculaProvincial,
	e.nombre especialidad,
	e2.nombre subespecialidad,
	d.estancia estancia,
	(CASE WHEN dv.[turno] = 0 THEN 'MAÑANA'
		  WHEN dv.[turno] = 0 THEN 'TARDE'
		  ELSE 'TODO EL DIA' END) turno,
	i.nombre institucion,
	p.nombre provincia,
	l.nombre localidad,
	c.nombre ciudad,
	ca.nombre calle,
	d2.numero,
	cp.cpa,
	dv.frecuencia,
	cd.nombre categoria,
	ld.nombre loyalty,
	d.sexo genero,
	d.email,
	d.telefono,
	ci.inicio,
	ci.fin,
	cme.estado,
	cme.fecha_solicitud fechaSolicitud,
	cme.fecha_confirmacion fechaConfirmacion,
	cm.modified_date ultActualizacion,
	STRING_AGG(CONCAT(DATENAME(WEEKDAY, hv.dia),' ', hv.desde, ' - ', hv.hasta), ', ') WITHIN GROUP (ORDER BY hv.dia ASC) horarios
	FROM
	cartera_medica cm
	JOIN apm a ON a.id =cm.apm_id
	JOIN usuario u ON u.id=a.gerente_regional_id
	LEFT JOIN linea_apm la ON la.id_apm = a.id
	LEFT JOIN linea li ON li.id=la.id_linea
	JOIN doctor d ON d.id=cm.doctor_id
	JOIN especialidad e ON e.id=d.especialidad_id
	LEFT JOIN especiaSubespecialidad e2 ON e2.id=d.espeSubespecialidad_id
	JOIN datos_visita dv ON dv.id = cm.datos_visita_id
	JOIN institucion i ON i.id=dv.institucion_id
	JOIN direccion d2 ON d2.id = i.id_direccion
	JOIN codigo_postal cp ON cp.id = d2.id_codigo_postal
	JOIN provincia p ON p.id = cp.id_provincia
	JOIN localidad l ON l.id=cp.id_localidad
	JOIN ciudad c ON c.id=cp.id_ciudad
	LEFT JOIN calle ca ON ca.id= cp.id_calle
	LEFT JOIN categoria_doctor cd ON cd.id = d.categoria_id
	LEFT JOIN loyalty_doctor ld ON ld.id = d.loyalty_id
	LEFT JOIN datos_visita_horario_visita dvhv ON dvhv.datos_visita_id =dv.id
	LEFT JOIN horario_visita hv ON hv.id = dvhv.horario_visita_id
	LEFT JOIN cartera_medica_estado cme ON cme.id = cm.cartera_medica_estado_id
	INNER JOIN [ciclo] ci ON ci.id = @ciclo
	WHERE cm.inactivo = 0
	AND (
		((cme.estado = 'ALTA_APROBADA' 
			OR cme.estado = 'ACTIVACION_APROBADA' 
			OR cme.estado = 'BAJA_RECHAZADA') 
		AND cme.fecha_confirmacion BETWEEN ci.inicio AND ci.fin)
		OR (cme.estado = 'BAJA_SOLICITADA' AND cme.fecha_solicitud BETWEEN ci.inicio AND ci.fin)
	)
	GROUP BY ci.nombre, u.nombre, u.apellido, a.primerApellido, a.segundoApellido, a.primerNombre, a.segundoNombre,
	a.legajo, cm.id, cm.doctor_id, d.primerApellido, d.segundoApellido, d.primerNombre, d.segundoNombre,
	d.matriculaNacional, d.matriculaProvincial, e.nombre, e2.nombre, d.estancia, dv.turno, i.nombre, p.nombre,
	l.nombre, ci.nombre, ca.nombre, d2.numero, cp.cpa, dv.frecuencia, cd.nombre, ld.nombre, d.sexo, d.email,
	d.telefono, ci.inicio, ci.fin, cme.estado, cme.fecha_solicitud, cme.fecha_confirmacion, cm.modified_date, c.nombre
END