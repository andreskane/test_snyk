alter table agenda
    add direccion_id int
go

alter table agenda
    add constraint agenda_direccion_id_fk
        foreign key (direccion_id) references direccion
go
