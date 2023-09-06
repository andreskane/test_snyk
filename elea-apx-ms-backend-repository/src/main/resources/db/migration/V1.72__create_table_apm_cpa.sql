-- Create Tabla Codigo Postal
create table apm_cpa
(
    id_apm int not null constraint apm_cpa_apm_id_fk references apm(id),
    id_cpa int not null constraint apm_cpa_codigo_postal_id_fk references codigo_postal(id)
)
go

