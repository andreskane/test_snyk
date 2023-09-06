if not exists (select * from sysobjects where name='cup_laboratorio' and xtype='U')
CREATE TABLE cup_laboratorio (
	id_laboratorio int NOT NULL,
	codigo_laboratorio nvarchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	descripcion_laboratorio nvarchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	UpdatedDtm nvarchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	codigo_corporacion nvarchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT PK_cup_laboratorio PRIMARY KEY (id_laboratorio)
);