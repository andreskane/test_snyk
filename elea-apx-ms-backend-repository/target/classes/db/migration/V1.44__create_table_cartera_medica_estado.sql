create table cartera_medica_estado
(
    id int identity,
    apm_id int NOT NULL,
    doctor_id int NOT NULL,
    gerente_id int NOT NULL,
    aprobador_id int,
    motivo_estado_id int,
    estado varchar(100) NOT NULL,
    motivo_rechazo varchar(100),
    fecha_solicitud datetime NOT NULL,
    fecha_confirmacion datetime
)
go

create unique index cartera_medica_estado_id_uindex
    on cartera_medica_estado (id)
go

alter table cartera_medica_estado
    add constraint cartera_medica_estado_pk
        primary key nonclustered (id)
go

ALTER TABLE cartera_medica ADD cartera_medica_estado_id int NULL,
    CONSTRAINT cartera_medica_estado_FK FOREIGN KEY (cartera_medica_estado_id) REFERENCES cartera_medica_estado (id)
GO