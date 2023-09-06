-- Add datos de dirección para insticution

alter table institucion add id_calle int null constraint institucion_calle_FK references calle(id)
alter table institucion add numero_puerta int null
go

