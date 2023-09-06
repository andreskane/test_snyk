alter table provincia add inactivo bit not null default 0;
go
alter table localidad add inactivo bit not null default 0;
go
alter table ciudad add inactivo bit not null default 0;
go
alter table calle add inactivo bit not null default 0;
go

update provincia set inactivo = 0 where inactivo is null;
go

update localidad set inactivo = 0 where inactivo is null;
go

update ciudad set inactivo = 0 where inactivo is null;
go

update calle set inactivo = 0 where inactivo is null;
go
