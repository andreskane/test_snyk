ALTER PROCEDURE calcular_prescripciones_doctor
AS
BEGIN 

 BEGIN TRAN
   BEGIN TRY

	DELETE FROM prescripciones_doctor;

	INSERT INTO prescripciones_doctor(doctor_id, fecha, codigo_laboratorio, nombre_laboratorio,codigo_producto, nombre_producto, marca, prescripciones)
	SELECT DISTINCT [doctor].id AS doctor_id,
		CONVERT(smalldatetime,[01_ELE_prescricao].DATA+'01') AS fecha, 
		[01_ELE_prescricao].CDGLAB AS codigo_laboratorio, 
		[cup_laboratorio].descripcion_laboratorio AS nombre_laboratorio,
		[01_ELE_prescricao].CDGPRO codigo_producto,
		[cup_producto].NOME AS nombre_producto, 
		[cup_producto].MARCA AS marca, 
	COALESCE(CAST([01_ELE_prescricao].PX1 AS INT), 0) AS prescripciones
	FROM [doctor]
	LEFT JOIN [01_ELE_medico] ON
		TRIM([01_ELE_medico].CRM) = CONCAT('P', REPLICATE('0', 7 - LEN(TRIM([doctor].matriculaProvincial))), TRIM([doctor].matriculaProvincial))
		OR TRIM([01_ELE_medico].CRM) = CONCAT('N', REPLICATE('0', 7 - LEN(TRIM([doctor].matriculaNacional))), TRIM([doctor].matriculaNacional))
		AND [01_ELE_medico].TIPO_DOM = 'C'
	LEFT JOIN [01_ELE_prescricao] ON
		[01_ELE_prescricao].CDGMED = [01_ELE_medico].CDGMED
	LEFT JOIN [cup_laboratorio] ON TRIM([cup_laboratorio].codigo_laboratorio) = TRIM([01_ELE_prescricao].CDGLAB)
	LEFT JOIN [cup_producto] ON TRIM([cup_producto].CODIGO) = TRIM([01_ELE_prescricao].CDGPRO)
	LEFT JOIN [cartera_medica] ON 
		[cartera_medica].doctor_id = [doctor].id
	LEFT JOIN [apm] ON 
		[apm].id = [cartera_medica].apm_id
	LEFT JOIN [cartera_medica_estado] ON
		[cartera_medica_estado].id = [cartera_medica].cartera_medica_estado_id
	WHERE [apm].inactivo = 0
	 	AND [01_ELE_prescricao].TIPO_DOM = 'C'
		AND [doctor].inactivo = 0
		AND [cartera_medica].inactivo = 0 
		AND ([cartera_medica_estado].estado = 'ALTA_APROBADA'
			OR [cartera_medica_estado].estado = 'ACTIVACION_APROBADA'
			OR [cartera_medica_estado].estado = 'BAJA_RECHAZADA'
			OR [cartera_medica_estado].estado = 'BAJA_SOLICITADA')
		AND [01_ELE_prescricao].CDGLAB IS NOT NULL 
		AND [01_ELE_prescricao].CDGPRO IS NOT NULL
		AND [cup_producto].MARCA IS NOT NULL
	ORDER BY
			[doctor].id, fecha;

 	COMMIT TRANSACTION
   END TRY
       BEGIN CATCH
              -- if error, roll back any chanegs done by any of the sql statements
              ROLLBACK TRANSACTION
       END CATCH

END