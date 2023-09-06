ALTER PROCEDURE [dbo].[Reporte_Visita_por_Ciclo_V3] @ciclo int
AS
BEGIN

	-- Obtiene el listado de productos por agenda
	SELECT
		ag.[doctor_id] as [codigoMed],
		cm.[id] as [codCartera],
		(CASE WHEN u.[apellido] is not null OR u.[nombre] is not null
			THEN CONCAT(u.[apellido], ', ', u.[nombre])
			ELSE null
		END) [supervisor],
		a.[primerApellido] as [apellidoApm],
		a.[primerNombre] as [nombreApm],
		a.[legajo] as [legajoApm],
		ci.[nombre] as [ciclo],
		ag.[inicio] as [fechaInicio],
		ag.[fin] as [fechaFin],
		ag.[turno],
		d.[primerApellido] as [apellidoMedico],
		d.[primerNombre] as [nombreMedico],
		d.[matriculaNacional] as [matriculaNac],
		d.[matriculaProvincial] as [matriculaProv],
		CASE
			WHEN [agenda_tipo_id] = 0 THEN 'Visita'
			WHEN [agenda_tipo_id] = 1 THEN 'Parte Diario'
			WHEN [agenda_tipo_id] = 2 THEN 'Planificacion'
			ELSE '(no identificado)'
		END as [tipoAccion],
		CASE
			WHEN ag.[visita_exitosa] = 1 THEN 'Si'
			WHEN ag.[visita_exitosa] = 0 THEN 'No'
		END [visitaExitosa],
		CASE WHEN VDAG.PROVINCIA  IS NULL THEN VDAG2.PROVINCIA   ELSE  VDAG.PROVINCIA END  provincia, --VDAG.provincia,
	CASE WHEN VDAG.ciudad  IS NULL THEN VDAG2.ciudad   ELSE  VDAG.ciudad END  ciudad,--	VDAG.ciudad,
	CASE WHEN VDAG.calle  IS NULL THEN VDAG2.calle   ELSE  VDAG.calle END  calle,--	VDAG.calle,
	CASE WHEN VDAG.numero  IS NULL THEN VDAG2.numero   ELSE  VDAG.numero END  numero,--	VDAG.numero,
	CASE WHEN VDAG.zip  IS NULL THEN VDAG2.zip   ELSE  VDAG.zip END  zip,--	VDAG.zip,
		i.[nombre] as [institucion],
		dv.[frecuencia],
		ag.[visita_tipo] as [visitaTipo],
		vwpa.PRODUCTO1 as [producto1],
		vwpa.PRODUCTO2 as [producto2],
		vwpa.PRODUCTO3 as [producto3],
		vwpa.PRODUCTO4 as [producto4],
		vwpa.PRODUCTO5 as [producto5],
		vwpa.PRODUCTO6 as [producto6],
		vwpa.PRODUCTO7 as [producto7],
		vwpa.PRODUCTO8 as [producto8],
		vwpa.PRODUCTO9 as [producto9],
		vwpa.PRODUCTO10 as [producto10],
		ci.[inicio] as [cicloIncio],
		ci.[fin] as [cicloFin],
		ag.[lastUpdated] as UltActualizacion,
		e.[nombre] as [medicoEspecialidad]
	FROM [dbo].[agenda] ag
		INNER JOIN [apm] a
			ON ag.[apm_id] = a.[id]
		LEFT JOIN [doctor] d
			ON ag.[doctor_id] = d.[id]
		LEFT JOIN [especialidad] e
			ON d.[especialidad_id] = e.[id]
		LEFT JOIN [institucion] i
			ON ag.[institucion_id] = i.[id]
		LEFT JOIN [direccion] di
			ON i.id_direccion = di.id
		LEFT JOIN [codigo_postal] cp
			ON cp.id = di.id_codigo_postal
		LEFT JOIN [localidad] l
			ON l.id = cp.id_localidad
		LEFT JOIN [ciudad] c
			ON cp.id_ciudad = c.id
		LEFT JOIN [provincia] prov
			ON cp.id_provincia = prov.id
		LEFT JOIN [calle] st
			ON cp.id_calle = st.id
		LEFT JOIN [usuario] u
			ON a.[gerente_regional_id] = u.[id]
		LEFT JOIN [cartera_medica] cm
			ON cm.[apm_id] = a.[id] AND cm.[doctor_id] = d.[id]
		LEFT JOIN [datos_visita] dv
			ON cm.[datos_visita_id] = dv.[id]
		INNER JOIN [ciclo] ci
			ON ci.id = @ciclo
			LEFT JOIN vw_direccion_agenda AS VDAG ON VDAG.ID=AG.ID
			left join vw_direccion_cm_by_apm_by_doctor VDAG2 on VDAG2.apm_id=a.id and VDAG2.doctor_id=d.id
				left join vw_productos_by_agenda as vwpa on vwpa.id_agenda=AG.ID
	WHERE ag.inicio >= ci.[inicio]
	AND	ag.inicio <= ci.[fin]
	AND ag.[agenda_tipo_id] = 0 -- visitas
	ORDER BY [fechaInicio]

END