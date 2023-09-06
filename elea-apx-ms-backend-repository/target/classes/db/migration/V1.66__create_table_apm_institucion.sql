create table apm_institucion
(
    id_apm int
        constraint apm_institucion_apm_id__fk
            references apm,
    id_institucion int
        constraint apm_institucion_institucion_id__fk
            references institucion
)
go

create unique index apm_institucion_id_apm_id_institucion_uindex
    on apm_institucion (id_apm, id_institucion)
go

