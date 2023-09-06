CREATE PROCEDURE calcular_prescripciones_doctor (@Meses int)
AS
BEGIN 

 BEGIN TRAN
   BEGIN TRY

	DELETE FROM prescripciones_doctor;

	INSERT INTO prescripciones_doctor(doctor_id, fecha, codigo_laboratorio, nombre_laboratorio,codigo_producto, nombre_producto, marca, prescripciones)
	SELECT DISTINCT [doctor].id AS doctor_id, CONVERT(smalldatetime,[01_ELE_prescricao].DATA+'01') AS fecha, [01_ELE_prescricao].CDGLAB AS codigo_laboratorio, 
	[cup_laboratorio].descripcion_laboratorio AS nombre_laboratorio, [01_ELE_prescricao].CDGPRO codigo_producto, [cup_producto].NOME AS nombre_producto, [cup_producto].MARCA AS marca, 
	CAST([01_ELE_prescricao].PX1 AS INT) AS prescripciones
	  FROM [01_ELE_prescricao]
	  JOIN [cup_medico] on [01_ELE_prescricao].CDGMED = [cup_medico].CDGMED
	  JOIN [doctor] on [cup_medico].CRM LIKE '%'+[doctor].matriculaProvincial OR [cup_medico].CRM LIKE '%'+[doctor].matriculaNacional AND [cup_medico].TIPO_DOM='C'
	  JOIN [cup_producto] on [cup_producto].CODIGO = [01_ELE_prescricao].CDGPRO
	  LEFT JOIN [cup_laboratorio] on [cup_laboratorio].codigo_laboratorio = [01_ELE_prescricao].CDGLAB
	  WHERE [doctor].id IN (SELECT TOP(10) DOCTOR.id FROM DOCTOR)
	  AND DATEFROMPARTS(SUBSTRING(DATA,1,4), SUBSTRING(DATA,5,2), '20' ) BETWEEN DATEADD(MM, @Meses, iif(DAY(GETDATE()) < 20, DATEFROMPARTS(YEAR(DATEADD(MONTH, -1, GETDATE())), MONTH (DATEADD(MONTH, -1, GETDATE())), 20), GETDATE()))
	  AND iif(DAY(GETDATE()) < 20, DATEFROMPARTS(YEAR(DATEADD(MONTH, -1, GETDATE())), MONTH (DATEADD(MONTH, -1, GETDATE())), 20), GETDATE())  
	  ORDER BY [doctor].id, fecha;

 	COMMIT TRANSACTION
   END TRY
       BEGIN CATCH
              -- if error, roll back any chanegs done by any of the sql statements
              ROLLBACK TRANSACTION
       END CATCH

END