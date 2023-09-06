CREATE TABLE provincia
(
    id int IDENTITY (1,1) NOT NULL,
    nombre varchar(100)       NULL
)GO

INSERT INTO provincia (nombre)
VALUES  ('Buenos Aires'),
        ('Catamarca'),
        ('Chaco'),
        ('Chubut'),
        ('Córdoba'),
        ('Corrientes'),
        ('Entre Ríos'),
        ('Formosa'),
        ('Jujuy'),
        ('La Pampa'),
        ('La Rioja'),
        ('Mendoza'),
        ('Misiones'),
        ('Neuquén'),
        ('Río Negro'),
        ('Salta'),
        ('San Juan'),
        ('San Luis'),
        ('Santa Cruz'),
        ('Santa Fe'),
        ('Santiago del Estero'),
        ('Tierra del Fuego'),
        ('Tucumán');