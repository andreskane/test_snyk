create table conexion_apm
(
    id int identity,
    id_apm int not null,
    fecha datetime not null
)
go

create unique index conexion_apm_id_uindex
    on conexion_apm (id)
go

alter table conexion_apm
    add constraint conexion_apm_pk
        primary key nonclustered (id)
go

