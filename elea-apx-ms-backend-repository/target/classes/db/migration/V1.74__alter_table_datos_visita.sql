-- Add datos de dirección para datos visita

alter table datos_visita add id_calle int null constraint datos_visita_calle_FK references calle(id)
alter table datos_visita add numero_puerta int null
go

