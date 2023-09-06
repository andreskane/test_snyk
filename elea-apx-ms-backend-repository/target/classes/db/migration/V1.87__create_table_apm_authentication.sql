create table apm_authentication
(
    id_apm int not null
        constraint apm_authentication_pk
            primary key nonclustered,
    token varchar(255)
)
go

