ALTER PROCEDURE [dbo].[Reporte_Transferencias_Pendientes] @ciclo int
AS
BEGIN

select
       CONCAT(gteOrigen.nombre, ' ', gteOrigen.apellido) gerenteOrigen,
       d.id codMedico,
       apmOrigen.primerNombre    as nombreApmOrigen,
       apmOrigen.primerApellido  as apellidoApmOrigen,
       apmOrigen.legajo legajoApmOrigen,
       CONCAT(gteDestino.nombre, ' ', gteDestino.apellido) gerenteDestino,
       apmDestino.primerNombre   as nombreApmDestino,
       apmDestino.primerApellido as apellidoApmDestino,
       apmDestino.legajo legajoApmDestino,
       d.primerNombre            as nombreDoctor,
       d.primerApellido          as apellidoDoctor,
       d.matriculaNacional,
       d.matriculaProvincial,
       e.nombre especialidad,
       CONCAT(u.apellido , ', ', u.nombre) usuario,
       t.tipo                    as tipoTransferencia,
       t.fechaProgramada         as fechaProgramada,
       p.nombre as provincia,
       l.nombre as localidad,
       ci.nombre as ciudad,
       cp.cpa as cp,
       i.nombre as institucion,
       cd.nombre as categoria,
       t.id as id
from transferencia t
         left join cartera_medica cm on t.id_cartera_medica = cm.id
         left join usuario u on t.id_usuario = u.id
         left join doctor d on cm.doctor_id = d.id
         left join apm apmOrigen on cm.apm_id = apmOrigen.id
         left join apm apmDestino on t.id_apm_destino = apmDestino.id
         join usuario gteOrigen on gteOrigen.id=apmOrigen.gerente_regional_id
         join usuario gteDestino on gteDestino.id=apmDestino.gerente_regional_id
         join especialidad e on e.id=d.especialidad_id
         left join datos_visita dv on dv.id = cm.datos_visita_id
         left join direccion d2 on d2.id=dv.id_direccion
		 LEFT JOIN codigo_postal cp on cp.id = d2.id_codigo_postal
		 LEFT JOIN provincia p on p.id = cp.id_provincia
		 LEFT JOIN localidad l on l.id = cp.id_localidad
		 LEFT JOIN ciudad ci on ci.id = cp.id_ciudad
		 LEFT JOIN calle c ON c.id = cp.id_calle
		 LEFT JOIN institucion i on i.id=dv.institucion_id
		 JOIN categoria_doctor cd on cd.id=d.categoria_id
		 INNER JOIN ciclo ci2 ON ci2.id = @ciclo
         where (t.fecha is null and fechaProgramada is null)
         or (t.fecha is null and programada_confirmada = 0)
         OR (t.fecha is not null AND fechaProgramada IS NULL)
         and (t.fecha BETWEEN ci2.inicio AND ci2.fin OR t.fecha IS NULL);

END