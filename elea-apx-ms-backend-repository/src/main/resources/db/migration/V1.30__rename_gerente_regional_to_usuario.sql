exec sp_rename 'gerente_regional', usuario, 'OBJECT'
go

ALTER TABLE usuario ADD rol varchar(100) NULL GO
ALTER TABLE usuario ADD created_date datetime NULL GO
ALTER TABLE usuario ADD modified_by varchar(100) NULL GO
ALTER TABLE usuario ADD modified_date datetime NULL GO
