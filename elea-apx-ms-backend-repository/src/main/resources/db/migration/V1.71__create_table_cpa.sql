-- Create Tabla Codigo Postal
create table codigo_postal
(
    id int identity constraint codigo_postal_pk primary key nonclustered,
    cpa varchar(8) not null,
    par_impar varchar(1),
    id_provincia int not null constraint codigo_postal_provincia_id_fk references provincia,
    id_localidad int not null constraint codigo_postal_localidad_id_fk references localidad(id),
    id_ciudad int not null constraint codigo_postal_ciudad_id_fk references ciudad(id),
    id_calle int constraint codigo_postal_calle_id_fk references calle (id),
    desde int,
    hasta int
)
go

