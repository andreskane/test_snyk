-- create table direccion
create table direccion
(
    id int identity
        constraint direccion_pk
            primary key nonclustered,
    id_codigo_postal int not null constraint direccion_codigo_postal_fk foreign key (id_codigo_postal) references codigo_postal(id),
    numero int not null,
    detalle varchar(100)
)
go


--- Add direccion to institucion
alter table institucion drop constraint institucion_calle_FK;
alter table institucion drop column id_calle;
alter table institucion drop column numero_puerta;
alter table institucion drop column direccion;
go

alter table institucion add id_direccion int constraint institucion_direccion_fk foreign key (id_direccion) references direccion(id)
go


-- Add direccion to datos visita
alter table datos_visita drop constraint datos_visita_calle_FK;
alter table datos_visita drop column id_calle;
alter table datos_visita drop column numero_puerta;
alter table datos_visita drop column direccion;
go

alter table datos_visita add id_direccion int constraint datos_visita_direccion_fk foreign key (id_direccion) references direccion(id)
go