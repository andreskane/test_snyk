ALTER PROCEDURE [Buscar_Direccion]
@id_pais INT,
@provincia VARCHAR(100),
@localidad VARCHAR(100),
@ciudad VARCHAR(100),
@calle VARCHAR(100),
@cpa VARCHAR(100),
@tipo_calle INT,
@numero INT,
@detalle VARCHAR(100),
@id_dir INT OUTPUT
AS

DECLARE
@id_provincia int,
@id_localidad int,
@id_ciudad int,
@id_calle int,
@estado int,
@id_cp int

BEGIN
	BEGIN TRY
		set @estado=0
		BEGIN TRANSACTION T1

		set @estado=0
		IF NOT EXISTS (select TOP 1 id from provincia where nombre COLLATE SQL_Latin1_General_CP1253_CI_AI = @provincia COLLATE SQL_Latin1_General_CP1253_CI_AI)
		BEGIN
		    insert into provincia (nombre, id_pais, inactivo)
		    values (@provincia, @id_pais, @estado)
		END

		SELECT TOP 1 @id_provincia = id FROM provincia WHERE nombre COLLATE SQL_Latin1_General_CP1253_CI_AI = @provincia COLLATE SQL_Latin1_General_CP1253_CI_AI

		IF NOT EXISTS (select TOP 1 id from localidad where nombre COLLATE SQL_Latin1_General_CP1253_CI_AI = @localidad COLLATE SQL_Latin1_General_CP1253_CI_AI AND id_provincia=@id_provincia)
		BEGIN
		    insert into localidad (nombre, id_provincia, inactivo)
		    values (@localidad, @id_provincia, @estado)
		END

		SELECT TOP 1 @id_localidad = id FROM localidad WHERE nombre COLLATE SQL_Latin1_General_CP1253_CI_AI = @localidad COLLATE SQL_Latin1_General_CP1253_CI_AI AND id_provincia=@id_provincia

		IF NOT EXISTS (select TOP 1 id from ciudad where nombre COLLATE SQL_Latin1_General_CP1253_CI_AI = @ciudad COLLATE SQL_Latin1_General_CP1253_CI_AI AND id_localidad=@id_localidad)
		BEGIN
		    insert into ciudad (nombre, id_localidad, inactivo)
		    values (@ciudad, @id_localidad, @estado)
		END

		SELECT TOP 1 @id_ciudad = id FROM ciudad WHERE nombre COLLATE SQL_Latin1_General_CP1253_CI_AI = @ciudad COLLATE SQL_Latin1_General_CP1253_CI_AI AND id_localidad=@id_localidad

		IF NOT EXISTS (select TOP 1 id from calle where nombre COLLATE SQL_Latin1_General_CP1253_CI_AI = @calle COLLATE SQL_Latin1_General_CP1253_CI_AI AND id_ciudad = @id_ciudad)
		BEGIN
		    insert into calle (nombre, id_tipo_calle, id_ciudad, inactivo)
		    values (@calle, @tipo_calle, @id_ciudad, @estado)
		END

		SELECT TOP 1 @id_calle = id FROM calle WHERE nombre COLLATE SQL_Latin1_General_CP1253_CI_AI = @calle COLLATE SQL_Latin1_General_CP1253_CI_AI AND id_ciudad = @id_ciudad

		IF NOT EXISTS (select TOP 1 id from codigo_postal where cpa = @cpa and id_provincia=@id_provincia and id_localidad = @id_localidad and id_ciudad = @id_ciudad and id_calle = @id_calle)
		BEGIN
		    insert into codigo_postal (cpa, id_provincia, id_localidad, id_ciudad, id_calle)
		    values (@cpa, @id_provincia, @id_localidad, @id_ciudad, @id_calle)
		END

		SELECT TOP 1 @id_cp = id from codigo_postal where cpa = @cpa and id_provincia=@id_provincia and id_localidad = @id_localidad and id_ciudad = @id_ciudad and id_calle = @id_calle

		IF NOT EXISTS (SELECT TOP 1 id FROM direccion where id_codigo_postal = @id_cp and numero = @numero and detalle LIKE TRIM(@detalle))
		BEGIN
			INSERT INTO direccion (id_codigo_postal, numero, detalle)
			VALUES(@id_cp, @numero, @detalle )
		END
		COMMIT TRANSACTION T1
		SELECT @id_dir = id FROM direccion where id_codigo_postal = @id_cp and numero = @numero and detalle LIKE TRIM(@detalle)

		END TRY
	BEGIN CATCH
       ROLLBACK TRANSACTION T1
    END CATCH

END