-- Tabla Localidad

create table localidad (
    id int identity,
    nombre varchar(100) not null,
    id_provincia int
        constraint localidad_provincia_id_fk
            references provincia
) go

create unique index localidad_id_uindex on localidad (id) go
alter table localidad add constraint localidad_pk primary key nonclustered (id) go

-- Tabla Ciudad

create table ciudad (
    id int identity,
    nombre varchar(100) not null,
    id_localidad int
       constraint ciudad_localidad_id_fk
           references localidad
) go

create unique index ciudad_id_uindex on ciudad (id) go
alter table ciudad add constraint ciudad_pk primary key nonclustered (id) go

-- Tabla Tipo Calle

create table tipo_calle (
    id int not null ,
    nombre varchar(100) not null
) go

create unique index tipo_calle_id_uindex on tipo_calle (id) go
alter table tipo_calle add constraint tipo_calle_pk primary key nonclustered (id) go


insert into tipo_calle(id, nombre)
values
    (5, 'ACCESO'),
    (6, 'ALAMEDA'),
    (7, 'ARROYO'),
    (8, 'AU'),
    (9, 'AU AUSOL PRIMER'),
    (10, 'AUTOPISTA'),
    (11, 'AV'),
    (13, 'AVDA CIRCU'),
    (14, 'AVENIDA'),
    (15, 'AVENIDA CO'),
    (16, 'BOULEVARD'),
    (17, 'CALLE'),
    (18, 'CALLE ACCESO'),
    (19, 'CALLE PEAT'),
    (20, 'CALLEAV'),
    (21, 'CALLEJON'),
    (22, 'CAMINO'),
    (23, 'CAMINO DE'),
    (24, 'CANAL'),
    (25, 'CARRIL'),
    (26, 'COLAU'),
    (27, 'COLF'),
    (28, 'COLF GRAL PAZ'),
    (29, 'CORTADA'),
    (30, 'DIAGONAL'),
    (31, 'EMBARCADERO'),
    (32, 'LIMITE'),
    (33, 'MACROCALLE'),
    (34, 'PASAJE'),
    (35, 'PASAJE PEA'),
    (36, 'PASEO'),
    (37, 'PEATONAL'),
    (38, 'RIO'),
    (39, 'ROTONDA'),
    (40, 'RUTA'),
    (41, 'RUTA L'),
    (42, 'RUTA NACIO'),
    (43, 'RUTA PROVI');
go
-- Tabla Calle
create table calle (
                           id int identity,
                           nombre varchar(100) not null,
                           id_tipo_calle int constraint calle_tipo_calle_id_fk references tipo_calle,
                           id_ciudad int constraint calle_ciudad_id_fk references ciudad
) go

create unique index calle_id_uindex on calle (id) go
alter table calle add constraint calle_pk primary key nonclustered (id) go