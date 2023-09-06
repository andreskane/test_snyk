ALTER TABLE producto ADD inactivo bit default 0;

GO

update producto set inactivo = 0 where inactivo is null;

GO
