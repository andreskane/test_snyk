if not exists (select * from sysobjects where name='csv_producto_promocional' and xtype='U')
    CREATE TABLE csv_producto_promocional (
	Id nvarchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	IdLinea nvarchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	Especialidad nvarchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	Medicamento nvarchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	Estado nvarchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	Mes nvarchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	DescMes nvarchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	Ano nvarchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	orden int NULL,
	CONSTRAINT PK_csv_producto_promocional PRIMARY KEY (Id)
)
go