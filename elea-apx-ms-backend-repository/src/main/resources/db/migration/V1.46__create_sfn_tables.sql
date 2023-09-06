create table SFN_audit_category
(
    cdg_periodo	varchar(50),
    cdg_medico	varchar(50),
    cdg_mercado	varchar(50),
    cdg_region	varchar(50),
    categoria	varchar(50),
    cant_px	    varchar(50),
    cant_px_lab	varchar(50),
    part_medico_mercado	varchar(50)
)
go

create table SFN_audit_class
(
    cdg_mercado	varchar(50),
    descripcion	varchar(50),
    abreviatura	varchar(50)
)
go

create table SFN_audit_classes
(
    cdg_medico	varchar(50),
    cdg_periodo	varchar(50),
    cdg_region	varchar(50),
    cant_px	    varchar(50),
    cant_px_lab	varchar(50)
)
go

create table SFN_audit_customer
(
    cdg_medico	varchar(50),
    matricula	varchar(50),
    nombre	    varchar(50),
    en_blanco	varchar(50),
    cdg_especialidad1	varchar(50),
    cdg_especialidad2	varchar(50),
    cdg_region	varchar(50),
    domicilio	varchar(50),
    localidad	varchar(50),
    cdg_postal	varchar(50),
    cdg_lider	varchar(50)
)
go


create table SFN_audit_customer_prescription
(
    cdg_periodo	varchar(50),
    cdg_medico	varchar(50),
    cdg_mercado	varchar(50),
    cdg_region	varchar(50),
    cdg_raiz	varchar(50),
    cant_px	    varchar(50),
    part_producto_medico varchar(50)
)
go

create index SFN_audit_customer_prescription_cdg_medico
    on SFN_audit_customer_prescription (cdg_medico)
go


create table SFN_audit_institution
(
    cdg_origen	varchar(50),
    descripcion	varchar(50)
)
go


create table SFN_audit_institution_prescription
(
    cdg_medico	varchar(50),
    cdg_origen	varchar(50),
    cdg_region	varchar(50),
    part_origen_medico	varchar(50)
)
go


create table SFN_audit_laboratory
(
    cdg_laboratorio	varchar(50),
    descripcion	    varchar(50),
    es_lab_cliente	varchar(50)
)
go


create table SFN_audit_period
(
    cdg_periodo	varchar(50),
    descripcion	varchar(50),
    fecha	    varchar(50)
)
go

create index SFN_audit_period_fecha
    on SFN_audit_period (fecha)
go


create table SFN_audit_product
(
    cdg_raiz	varchar(50),
    descripcion	varchar(50),
    abreviatura	varchar(50),
    cdg_laboratorio	varchar(50)
)
go


create table SFN_audit_product_class
(
    cdg_raiz	varchar(50),
    cdg_mercado	varchar(50)
)
go

create index SFN_audit_product_class_cdg_mercado_index
    on SFN_audit_product_class (cdg_mercado)
go


create table SFN_audit_region
(
    cdg_region	varchar(50),
    nombre	varchar(50)
)
go


create table SFN_audit_speciality
(
    cdg_especialidad	varchar(50),
    descripcion	varchar(50),
    abreviatura	varchar(50)
)
go