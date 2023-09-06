alter table material_promocional drop constraint material_promocional_especialidad_FK;

truncate table especialidad;
DBCC CHECKIDENT ('especialidad', RESEED, 1);

insert into especialidad(nombre)
values ('Alergia'),
       ('Algologia'),
       ('Angio-flebologia'),
       ('Cardiologia'),
       ('Cirugia'),
       ('Cirujano cardio vascular'),
       ('Clinica medica'),
       ('Clinica medica guardia hosp'),
       ('Dermatologia'),
       ('Diabetes nutricion'),
       ('Endocrinologia'),
       ('Fertilidad'),
       ('Flebologia'),
       ('Gastroenterologia'),
       ('Geriatria'),
       ('Ginecologia'),
       ('Hematologia'),
       ('Hemodinamista'),
       ('Hepatologia'),
       ('Infectologia'),
       ('Nefrologia'),
       ('Neumo tisiologo'),
       ('Neurocirugia'),
       ('Neurologia'),
       ('Neurologos pediatras'),
       ('Obstetricia'),
       ('Odontologia'),
       ('Oftalmologia'),
       ('Oftalmopediatria'),
       ('Oncologia'),
       ('Otorrinolaringologia'),
       ('Pediatria'),
       ('Psiquiatria'),
       ('Reumatologia'),
       ('Terapia intensiva'),
       ('Traumatologia'),
       ('Unidad coronaria'),
       ('Urologia');

alter table material_promocional
    add constraint material_promocional_especialidad_FK
        foreign key (especialidad_id) references especialidad(id)
go