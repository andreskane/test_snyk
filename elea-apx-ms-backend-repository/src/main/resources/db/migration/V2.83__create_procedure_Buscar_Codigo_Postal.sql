CREATE PROCEDURE [Buscar_Direccion]
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
		IF NOT EXISTS (select id from provincia where nombre = @provincia)
		BEGIN
		    insert into provincia (nombre, id_pais, inactivo)
		    values (@provincia, @id_pais, @estado)
		END

		SELECT @id_provincia = id FROM provincia WHERE nombre = @provincia

		IF NOT EXISTS (select id from localidad where nombre = @localidad)
		BEGIN
		    insert into localidad (nombre, id_provincia, inactivo)
		    values (@localidad, @id_provincia, @estado)
		END

		SELECT @id_localidad = id FROM localidad WHERE nombre = @localidad

		IF NOT EXISTS (select id from ciudad where nombre = @ciudad)
		BEGIN
		    insert into ciudad (nombre, id_localidad, inactivo)
		    values (@ciudad, @id_localidad, @estado)
		END

		SELECT @id_ciudad = id FROM ciudad WHERE nombre = @ciudad

		IF NOT EXISTS (select id from calle where nombre = @calle)
		BEGIN
		    insert into calle (nombre, id_tipo_calle, id_ciudad, inactivo)
		    values (@calle, @tipo_calle, @id_ciudad, @estado)
		END

		SELECT @id_calle = id FROM calle WHERE nombre = @calle

		IF NOT EXISTS (select id from codigo_postal where cpa = @cpa and id_provincia=@id_provincia and id_localidad = @id_localidad and id_ciudad = @id_ciudad and id_calle = @id_calle)
		BEGIN
		    insert into codigo_postal (cpa, id_provincia, id_localidad, id_ciudad, id_calle)
		    values (@cpa, @id_provincia, @id_localidad, @id_ciudad, @id_calle)
		END

		SELECT @id_cp = id from codigo_postal where cpa = @cpa and id_provincia=@id_provincia and id_localidad = @id_localidad and id_ciudad = @id_ciudad and id_calle = @id_calle

		IF NOT EXISTS (SELECT id FROM direccion where id_codigo_postal = @id_cp and numero = @numero and detalle LIKE TRIM(@detalle))
		BEGIN
			INSERT INTO direccion (id_codigo_postal, numero, detalle)
			VALUES(@id_cp, @numero, @detalle )
		END
		COMMIT TRANSACTION T1
		SELECT @id_dir =  id FROM direccion where id_codigo_postal = @id_cp and numero = @numero and detalle LIKE TRIM(@detalle)

		END TRY
	BEGIN CATCH
       ROLLBACK TRANSACTION T1
    END CATCH

END
