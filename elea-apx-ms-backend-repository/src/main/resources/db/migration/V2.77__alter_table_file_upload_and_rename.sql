alter table file_upload
    drop constraint file_upload_producto_id_fk
go

exec sp_rename 'file_upload.producto_id', codigo_id, 'COLUMN'
go

