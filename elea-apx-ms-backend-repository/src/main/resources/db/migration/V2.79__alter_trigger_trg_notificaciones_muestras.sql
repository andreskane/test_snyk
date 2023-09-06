ALTER TRIGGER trg_notificaciones_muestras
ON muestras_apm
AFTER INSERT
AS
BEGIN
	SET NOCOUNT ON;
    DECLARE
	@idApm varchar(10),
	@idNotificacion varchar(10),
	@totalNotificaciones integer

	SET @idApm = null;
    SET @totalNotificaciones = 0;

	SELECT
	@idApm  = ap.id
	FROM inserted, apm ap
	where
	CONCAT(primerApellido , ' ' , primerNombre, ' ' , segundoNombre) like CONCAT('%',inserted.apm,'%') and
	inserted.Producto like '%MM%'

	IF @idApm is not NULL
	BEGIN
		SELECT @totalNotificaciones = COUNT(1) FROM notificacion_push np
		JOIN notificacion_push_apm npa ON np.id =npa.id_notificacion
		WHERE np.topico = 'MUESTRAS'
		AND npa.id_apm = @idApm
		AND FORMAT(created_date, 'dd-MM-yyyy') = FORMAT(GETDATE(), 'dd-MM-yyyy');

		IF @totalNotificaciones = 0
		BEGIN
			INSERT INTO notificacion_push (titulo,topico,estado,tipo,created_date, modified_date)
			VALUES ('Tenés un nuevo envío de muestras médicas por gestionar','MUESTRAS','enviado','push', GETDATE(), GETDATE());
		    SELECT @idNotificacion = MAX(id) FROM notificacion_push;
			INSERT INTO notificacion_push_apm (id_notificacion, id_apm) VALUES (@idNotificacion, @idApm)
		END
  END
END