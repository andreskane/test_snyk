alter table producto
    drop constraint producto_tipo_FK
go

alter table producto drop column id_tipo go

alter table producto add abreviatura varchar (100) go

drop table producto_tipo go