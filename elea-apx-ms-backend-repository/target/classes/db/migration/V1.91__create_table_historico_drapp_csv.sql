CREATE TABLE historico_drapp_csv (
    fecha_actualizacion datetime NULL,
	cantidad_registros int NULL,
	id int IDENTITY(0,1) NOT NULL,
	CONSTRAINT historico_drapp_csv_PK PRIMARY KEY (id)
)
go