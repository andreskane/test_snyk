CREATE TABLE [sociedad_cientifica](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](255) NOT NULL,
	[siglas] [varchar](255) NULL,
	[inactivo] [bit] NOT NULL,
 CONSTRAINT [PK_sociedad_cientifica] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [sociedad_cientifica] ADD  CONSTRAINT [DF_sociedad_cientifica_inactivo]  DEFAULT ((0)) FOR [inactivo]
GO

INSERT INTO [sociedad_cientifica] ([siglas], [nombre])
     VALUES
('SAGE','Sociedad Argentina de Gastro'),
('AAOT','Asociación Argentina de Traumatología'),
('SAGG','Sociedad Argentina de Gerontología y Geriatría'),
('AAMR','Asociación Argentina de Medicina Respiratoria'),
('SAP','Sociedad Argentina de Pediatria'),
('FASO','Federación Argentina de sociedades de Otorrinolaringologia'),
('SMIBA','Sociedad de medicina Interna de Buenos Aires'),
('STNBA','Sociedad de tisioneumonologia de Bs As'),
('CAO','Consejo Argentino de oftalmología'),
('SAO','Sociedad Argentina de oftalmología'),
('Faco','Faco Extrema'),
('Saryv','Sociedad Argentina de retina y vitreo'),
('Sacryc','Sociedad Argentina de cirugía refractiva y cataratas'),
('Asag','Asociación Argentina de Glaucoma'),
('SAHA','Sociedad Argentina de Hipertensión Arterial'),
('SAD','Sociedad Argentina de Diabetes'),
('SAEM','Sociedad Argentina de Endocrinología y Metabolismo'),
('SOGIBA','Sociedad de obstetricia y ginecología de Buenos Aires'), 
('FASGO','Federación Argentina de sociedades de ginecología y obstetricia'),
('SOGBA','Sociedad de obstetricia y ginecología de la provincia de Buenos Aires'),
('AMADA','Asociación médica argentina de anticoncepción'),
('SAGIJ','Sociedad argentina de ginecología infanto juvenil'), 
('AAPEC','Asociación argentina para el estudio del climaterio'),
('SAU','Sociedad Argentina de Urología'),
('APSA','Asociación Arg Psiquiatría , Jorge Tagliero'),
('AAP','Asociación Argentina de Psiquiatría, Liliana Cecchin'),
('SNA','Soc Neurológica Argentina,  Romina Gori'),
('SANI','Sociedad Arg Neurología infantil'),
('AAPI','Asociación Argentina de Psiquiatría Infantojuvenil, Mónica Gonzalez'),
('LACE','Liga argentina contra epilepsia'),
('ANA','Asociación neuropsiquiátrica Argentina, Lic Cecilia Graves Ozán')
GO