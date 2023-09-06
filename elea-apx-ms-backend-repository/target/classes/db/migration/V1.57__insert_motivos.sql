alter table agenda drop constraint if exists agenda_motivo_fallida_FK;

alter table estado_doctor drop constraint if exists motivo_baja_FK;

truncate table motivo;

DBCC CHECKIDENT ('motivo', RESEED, 1);

insert into motivo(id_tipo, nombre)
values
    (1, 'APM-Capacitación'),
    (1, 'APM-Congreso'),
    (1, 'APM-Convención'),
    (1, 'APM-Enfermedad'),
    (1, 'APM-Falta de Tiempo'),
    (1, 'APM-Gira Suspendida'),
    (1, 'APM-Vacaciones'),
    (1, 'Medico-Ausente'),
    (1, 'Medico-Licencia'),
    (1, 'Medico-Vacaciones'),
    (2, 'Visita a medicas/os'),
    (2, 'Visita a Farmacia'),
    (2, 'Acciones Especiales'),
    (2, 'Asueto'),
    (2, 'Capacitación Herramientas'),
    (2, 'Capacitación Productos'),
    (2, 'Convención'),
    (2, 'Eventos / Congreso'),
    (2, 'Feriado Nacional'),
    (2, 'Feriado Provincial / Local'),
    (2, 'Gestión Comercial'),
    (2, 'Licencia Médica'),
    (2, 'Licencia por Maternidad'),
    (2, 'Licencia por Paternidad'),
    (2, 'Reunión con Gerente de Distrito'),
    (2, 'Ruenión de Ciclo/Equipo'),
    (2, 'Tareas Gremiales'),
    (2, 'Tramites / Permisos Personales'),
    (2, 'Trámites Administrativos'),
    (2, 'Tratamiento Compartido'),
    (2, 'Vacaciones'),
    (2, 'Viaje / Viaje por Gira'),
    (3, 'Bajo Potencial'),
    (3, 'Duplicado'),
    (3, 'Fallecimiento'),
    (3, 'Jubilación'),
    (3, 'Licencia'),
    (3, 'Mudanza'),
    (3, 'No Atiende Más'),
    (3, 'No Recibe APM'),
    (3, 'Rezonificación');
GO

alter table agenda
    add constraint agenda_motivo_fallida_FK
        foreign key (agenda_motivo_fallida_id) references motivo(id)
go