package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.Transferencia;
import ar.elea.apx.backend.projection.TranferenciaProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TransfersCustomRepository extends JpaRepository<Transferencia, Long>, JpaSpecificationExecutor<Transferencia> {

    @Query(value = "select t.id as id, " +
            "                       d.primerNombre            as doctorNombre, " +
            "                       d.primerApellido          as doctorApellido, "+
            "                       apmOrigen.primerNombre    as nombreApmOrigen, "+
            "                       apmOrigen.primerApellido  as apellidoApmOrigen, " +
            "                       apmDestino.primerNombre   as nombreApmDestino, " +
            "                       apmDestino.primerApellido as apellidoApmDestino, " +
            "                       u.nombre                  as nombreUsuario, " +
            "                       u.apellido                as apellidoUsuario, " +
            "                       t.fecha                          as fecha, " +
            "                       t.fechaProgramada                as fechaProgramada, " +
            "                       t.tipo                           as tipo, " +
            "                       t.comentario                     as comentario " +
            "                from transferencia t " +
            "                         left join cartera_medica cm on t.id_cartera_medica = cm.id " +
            "                         left join usuario u on t.id_usuario = u.id " +
            "                         left join doctor d on cm.doctor_id = d.id " +
            "                         left join apm apmOrigen on cm.apm_id = apmOrigen.id " +
            "                         left join apm apmDestino on t.id_apm_destino = apmDestino.id " +
            "where t.fecha is not null and " +
            "                      (:doctorNombre is null or d.primerNombre like concat(:doctorNombre, '%')) and " +
            "                      (:doctorApellido is null or d.primerApellido like concat(:doctorApellido, '%')) and " +
            "                      (:apmOrigenId is null or cm.apm_id = :apmOrigenId) and " +
            "                      (:apmDestinoId is null or t.id_apm_destino = :apmDestinoId) and " +
            "                      (:usuarioId is null or t.id_usuario = :usuarioId) and " +
            "                      (:fecha is null or t.fecha = Convert(date, :fecha)) and " +
            "                      (:programada is null or (:programada = 1 and t.fechaProgramada is not null) or (:programada = 0 and t.fechaProgramada is null)) and " +
            "                      (:tipo is null or t.tipo like :tipo)",
            countQuery = "select count(t.id) " +
                    "                from transferencia t " +
                    "                         left join cartera_medica cm on t.id_cartera_medica = cm.id " +
                    "                         left join usuario u on t.id_usuario = u.id " +
                    "                         left join doctor d on cm.doctor_id = d.id " +
                    "                         left join apm apmOrigen on cm.apm_id = apmOrigen.id " +
                    "                         left join apm apmDestino on t.id_apm_destino = apmDestino.id " +
                    "where t.fecha is not null and " +
                    "                      (:doctorNombre is null or d.primerNombre like concat(:doctorNombre, '%')) and " +
                    "                      (:doctorApellido is null or d.primerApellido like concat(:doctorApellido, '%')) and " +
                    "                      (:apmOrigenId is null or cm.apm_id = :apmOrigenId) and " +
                    "                      (:apmDestinoId is null or t.id_apm_destino = :apmDestinoId) and " +
                    "                      (:usuarioId is null or t.id_usuario = :usuarioId) and " +
                    "                      (:fecha is null or t.fecha = Convert(date, :fecha)) and " +
                    "                      (:programada is null or (:programada = 1 and t.fechaProgramada is not null) or (:programada = 0 and t.fechaProgramada is null)) and " +
                    "                      (:tipo is null or t.tipo like :tipo)",
            nativeQuery = true)
    Page<TranferenciaProjection> getTransferencias(String doctorNombre, String doctorApellido, Long apmOrigenId, Long apmDestinoId, Long usuarioId, LocalDate fecha, Boolean programada, String tipo, Pageable pageable);
}
