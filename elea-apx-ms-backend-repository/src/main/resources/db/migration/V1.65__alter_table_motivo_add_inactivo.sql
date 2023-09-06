ALTER TABLE motivo ADD inactivo bit default 0;

GO

update motivo set inactivo = 0 where inactivo is null;

GO