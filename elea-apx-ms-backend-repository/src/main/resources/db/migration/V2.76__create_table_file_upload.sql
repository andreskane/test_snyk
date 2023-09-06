create table file_upload
(
    id           bigint identity
        constraint file_upload_pk
            primary key,
    filename     varchar(250) not null,
    storage_path varchar(300) not null,
    date_upload  datetime,
    apm_id       int          not null
        constraint file_upload_apm_id_fk
            references apm,
    doctor_id    int
        constraint file_upload_doctor_id_fk
            references doctor,
    producto_id  int
        constraint file_upload_producto_id_fk
            references producto,
    comments     varchar(500)
)
go

exec sp_addextendedproperty 'MS_Description', 'This table allows you to leave a record of the files saved in the cloud',
     'SCHEMA', 'dbo', 'TABLE', 'file_upload'
go

