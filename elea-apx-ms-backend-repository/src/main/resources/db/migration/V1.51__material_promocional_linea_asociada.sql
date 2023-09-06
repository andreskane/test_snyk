create table material_promocional_linea
(
    id int identity,
    nombre varchar(120) not null
)
go

create unique index material_promocional_linea_id_uindex
    on material_promocional_linea (id)
go

alter table material_promocional_linea
    add constraint material_promocional_linea_pk
        primary key nonclustered (id)
go

INSERT INTO material_promocional_linea (nombre)
VALUES  ('Cardiometabolismo'),
        ('Neurología'),
        ('Respiratorio'),
        ('Urología'),
        ('Salud Femenina'),
        ('General'),
        ('Biotecnología');

create table material_promocional_linea_asociada
(
    id_material_promocional int,
    id_linea int
)