ALTER PROCEDURE [dbo].[Reporte_Medico] @ciclo int
AS
BEGIN

SELECT d.id,
TRIM(CONCAT(trim(d.primerNombre), ' ',  TRIM(d.segundoNombre))) nombre,
TRIM(CONCAT(TRIM(d.primerApellido), ' ', trim(segundoApellido))) apellido,
d.matriculaNacional,
d.matriculaProvincial,
e.nombre AS especialidad,
e2.nombre AS subEspecialidad,
d.sexo genero,
d.estancia asientoGira,
cd.nombre AS categoria,
ld.nombre AS loyalty,
d.fechaNacimiento,
d.estado_civil AS estadoCivil,
d.flag_residente residente,
d.flag_jefe_servicio jefeServicio,
d.email,
d.telefono,
d.flagDocente,
d.lugarDocente,
d.flagCoordinador,
d.lugarCoordinador,
d.flagSocieCienti,
d.cargSocieCienti,
d.obsSocieCienti cuales,
sc.nombre nomSocieCienti,
d.flagLiderOpi,
d.ObsLiderOpi,
d.flagInteCienti,
STRING_AGG(t.nombre, ', ') deportes,
STRING_AGG(t1.nombre, ', ') hobbies,
STRING_AGG(t2.nombre, ', ') aficiones,
d.modified_date AS ultActualizacion
FROM doctor d
JOIN especialidad e ON e.id = d.especialidad_id
LEFT JOIN especiaSubespecialidad e2 ON e2.id = d.espeSubespecialidad_id
JOIN categoria_doctor cd ON cd.id =d.categoria_id
JOIN loyalty_doctor ld ON ld.id=d.loyalty_id
LEFT JOIN tag_doctor td on td.doctor_id = d.id
LEFT JOIN tag t on t.id = td.tag_id and t.tipo = 'DEPORTE'
LEFT JOIN tag t1 on t1.id = td.tag_id and t1.tipo = 'HOBBY'
LEFT JOIN tag t2 on t2.id = td.tag_id and t2.tipo = 'AFICION'
LEFT JOIN sociedad_cientifica sc ON sc.id = d.sociedad_cientifica_id
JOIN cartera_medica cm ON cm.doctor_id = d.id 
JOIN cartera_medica_estado cme ON cme.id =cm.cartera_medica_estado_id 
INNER JOIN ciclo ci ON ci.id = @ciclo
WHERE d.inactivo = 0
AND (
	((cme.estado = 'ALTA_APROBADA' 
		OR cme.estado = 'ACTIVACION_APROBADA' 
		OR cme.estado = 'BAJA_RECHAZADA') 
	AND cme.fecha_confirmacion BETWEEN ci.inicio AND ci.fin)
	OR (cme.estado = 'BAJA_SOLICITADA' AND cme.fecha_solicitud BETWEEN ci.inicio AND ci.fin)
)
GROUP BY d.id, d.primerNombre, d.segundoNombre, d.primerApellido, d.segundoApellido ,
d.matriculaNacional, d.matriculaProvincial , d.especialidad_id , d.espeSubespecialidad_id ,
e.nombre, e2.nombre, d.estancia, d.flag_jefe_servicio, d.flag_residente, d.inactivo, cd.nombre, ld.nombre, d.fechaNacimiento,
d.estado_civil, d.email,
d.telefono,
d.flagDocente,
d.lugarDocente,
d.flagCoordinador,
d.lugarCoordinador,
d.flagSocieCienti,
d.cargSocieCienti,
d.nomSocieCienti,
d.cargSocieCienti,
d.flagLiderOpi,
d.ObsLiderOpi,d.flagInteCienti, d.sexo,sc.nombre, d.modified_date, d.obsSocieCienti;

END