-- Create Tabla Pais
create table pais
(
    id int NOT NULL constraint pais_pk primary key nonclustered,
    nombre varchar(255) NULL
)
go

insert into pais(id, nombre) values (1, 'ARGENTINA');
go

alter table provincia add id_pais int constraint provincia_pais_id_fk foreign key (id_pais) references pais(id)
go

update provincia set id_pais = 1 where id_pais is null;