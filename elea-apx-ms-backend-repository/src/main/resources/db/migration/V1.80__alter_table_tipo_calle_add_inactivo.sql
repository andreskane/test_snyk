alter table tipo_calle add inactivo bit not null default 0;
go

update tipo_calle set inactivo = 0 where inactivo is null;
go


