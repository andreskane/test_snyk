ALTER PROCEDURE [dbo].[Reporte_TiempoFueraTerritorio_por_Ciclo_V2] @ciclo int
AS
BEGIN

	SELECT
		(CASE WHEN u.[apellido] is not null OR u.[nombre] is not null 			THEN CONCAT(u.[apellido], ', ', u.[nombre]) 			ELSE null		END) [supervisor],
		a.[legajo] as [legajoApm],
		a.[primerApellido] as [apellidoApm],
		a.[primerNombre] as [nombrApm],
		ag.[inicio] as [fechaInicio],
		ag.[fin] as [fechaFin],
		m.[nombre] as [motivo],
		SUM  ((ag.[porcentaje_turno_manana]) + (ag.[porcentaje_turno_tarde])) as [porcentajeTotal],
	 SUM	(ag.[porcentaje_turno_manana]) as [porcentajeManana],
SUM		(ag.[porcentaje_turno_tarde]) as [porcentajeTarde],
		(CASE WHEN ag.[turno] = 'MANANA' 			THEN 'MAÑANA'			ELSE ag.[turno]		END) [turno],
		ci.[nombre] as [ciclo],
		ci.[inicio] as [cicloIncio],
		ci.[fin] as [cicloFin],
		ag.[lastUpdated] as [ultActualizacion]
	FROM [dbo].[agenda] ag
		INNER JOIN [dbo].[apm] a
			ON ag.[apm_id] = a.[id]
		LEFT JOIN [dbo].[doctor] d
			ON ag.[doctor_id] = d.[id]
		LEFT JOIN [dbo].[especialidad] e
			ON d.[especialidad_id] = e.[id]
		LEFT JOIN [institucion] i
			ON ag.[institucion_id] = i.[id]
		LEFT JOIN [dbo].[motivo] m
			ON ag.[agenda_motivo_fallida_id] = m.[id]
		LEFT JOIN [usuario] u
			ON a.[gerente_regional_id] = u.[id]
		INNER JOIN [ciclo] ci
			ON ci.id = @ciclo
	WHERE ag.inicio >= ci.[inicio]
	AND	ag.inicio <= ci.[fin]
	AND ag.[agenda_tipo_id] = 1 -- partes diarios


 GROUP BY
 	(CASE WHEN u.[apellido] is not null OR u.[nombre] is not null 			THEN CONCAT(u.[apellido], ', ', u.[nombre]) 			ELSE null		END) ,
		a.[legajo]  ,
		a.[primerApellido]  ,
		a.[primerNombre]  ,
		ag.[inicio]  ,
		ag.[fin] ,
		m.[nombre]  ,
		ci.[nombre]  ,
		ci.[inicio] ,
		ci.[fin],
		ag.[lastUpdated],
        ag.[turno]

	ORDER BY [fechaInicio]

END