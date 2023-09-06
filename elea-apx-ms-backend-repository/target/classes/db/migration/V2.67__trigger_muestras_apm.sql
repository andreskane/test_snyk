CREATE TRIGGER trg_notificaciones_muestras
ON muestras_apm
AFTER INSERT
AS
BEGIN
	SET NOCOUNT ON;
    DECLARE
	@idApm varchar(10),
	@idNotificacion varchar(10)
	
	SET @idApm = null;
	
	SELECT 
	@idApm  = ap.id
	FROM inserted, apm ap 
	where 
	CONCAT(primerApellido , ' ' , primerNombre, ' ' , segundoNombre) like CONCAT('%',inserted.apm,'%') and 
	inserted.Producto like '%MM%' 

	
	IF @idApm is not null
	BEGIN
		INSERT INTO notificacion_push (titulo,topico,estado,tipo,created_date, modified_date) 
		VALUES ('Tenés un nuevo envío de muestras médicas por gestionar','MUESTRAS','enviado','push', GETDATE(), GETDATE());
	    SELECT @idNotificacion = MAX(id) FROM notificacion_push; 
		INSERT INTO notificacion_push_apm (id_notificacion, id_apm) VALUES (@idNotificacion, @idApm)
	END
END
GO