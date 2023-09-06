if not exists (select * from sysobjects where name='cup_medico' and xtype='U')
CREATE TABLE cup_medico (
	CDGREG nvarchar(MAX) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CDGREG_PMIX nvarchar(MAX) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CRM nvarchar(MAX) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	NOME nvarchar(MAX) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CDGMED nvarchar(MAX) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CDGMED_REG nvarchar(MAX) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	[LOCAL] nvarchar(MAX) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	BAIRRO nvarchar(MAX) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	ZONA nvarchar(MAX) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CEP nvarchar(MAX) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CDGESP1 nvarchar(MAX) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CDGESP2 nvarchar(MAX) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	TIPO_DOM nvarchar(MAX) COLLATE SQL_Latin1_General_CP1_CI_AS NULL
);