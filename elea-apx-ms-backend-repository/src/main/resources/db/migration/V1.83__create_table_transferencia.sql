create table transferencia
(
    id                int identity,
    id_cartera_medica int     not null
        constraint transferencia_cartera_medica_id_fk
            references cartera_medica (id),
    id_apm_destino    int     not null
        constraint transferencia_apm_id_fk
            references apm(id),
    id_usuario        int     not null
        constraint transferencia_usuario_id_fk
            references usuario(id),
    tipo              varchar(20) not null,
    fecha             date,
    fechaProgramada   date
)
go

create unique index transferencia_id_uindex
    on transferencia (id)
go

alter table transferencia
    add constraint transferencia_pk
        primary key nonclustered (id)
go