CREATE TABLE apm_caja_recepcion (
	id int IDENTITY(0,1) NOT NULL,
	id_apm int NOT NULL,
	fecha_envio date,
	comentarios varchar(600),
	estado_aprobacion varchar(250),
	fecha_aprobacion date
	CONSTRAINT apm_recepcion_caja_pk PRIMARY KEY (id)
);
go


CREATE TABLE apm_caja_recepcion_productos (
	id int IDENTITY(0,1) NOT NULL,
	id_caja_recepcion int NOT NULL,
	codigo_producto int NOT NULL,
	nombre_producto varchar(750) NOT NULL
	CONSTRAINT apm_caja_recepcion_productos_pk PRIMARY KEY (id),
	CONSTRAINT apm_producto_recepcion_caja FOREIGN KEY (id_caja_recepcion) REFERENCES apm_caja_recepcion (id)
);
go


CREATE TABLE apm_muestras_aprobacion (
	id int IDENTITY(0,1) NOT NULL,
	id_recepcion_productos int NOT NULL,
	codigo_muestra int NOT NULL,
	producto_muestra varchar(450) NOT NULL,
	lote varchar(200),
	vecimiento date,
	cantidad_enviada int,
	cantidad_aprobada int,
	fecha_envio date,
	justificacion varchar(150)
	CONSTRAINT apm_aprobacion_muestras_pk PRIMARY KEY (id),
	CONSTRAINT apm_recepcion_productos_muestras_aprobacion FOREIGN KEY (id_recepcion_productos) REFERENCES apm_caja_recepcion_productos (id)
);
go


CREATE TABLE apm_inventario_muestra (
	id int IDENTITY(0,1) NOT NULL,
	id_muestras_aprobacion int NOT NULL,
	cantidad int NOT NULL,
	estado varchar(250),
	fecha_creacion date
	CONSTRAINT apm_inventario_muestras_pk PRIMARY KEY (id),
	CONSTRAINT apm_aprobacion_inventario_muestras FOREIGN KEY (id_muestras_aprobacion) REFERENCES apm_muestras_aprobacion (id)
);
go