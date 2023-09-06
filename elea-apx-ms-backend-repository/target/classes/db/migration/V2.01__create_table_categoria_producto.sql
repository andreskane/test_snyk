create table categoria_producto (
	id int IDENTITY(1,1) NOT NULL,
	descripcion varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT categoria_producto_PK PRIMARY KEY (id)
)GO


insert into categoria_producto(descripcion)
values ('Nuevo Producto'),
       ('HiperFoco'),
       ('Foco'),
       ('Recordatorio'),
       ('Sin Categoria')

