package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.Recordatorio;
import ar.elea.apx.backend.projection.RecordatorioProjection;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * @author Felipe Jimenez
 * @author Guillermo Nasi
 */
public interface RecordatorioRepository extends CrudRepository<Recordatorio, Long> {

    List<Recordatorio> findAllByAgenda_IdAndInactivoIsFalse(Long agendaId);

    List<Recordatorio> findAllByApm_IdOrAgenda_Apm_IdAndInactivoIsFalse(Long apmId, Long idApm);

    @Query(value = "select r.id            as id, " +
            "       r.id_agenda            as agendaId, " +
            "       r.fecha_completado     as fechaCompletado, " +
//            "       r.fechaRecordatorio     as fechaRecordatorio, " +
            "       r.id_doctor            as doctorId, " +
            "       r.id_institucion       as institucionId, " +
            "       r.id_agenda_completado as agendaCompletadoId, " +
            "       r.descripcion          as descripcion, " +
            "       recordatorio_item.id   as materialPromocionalId, " +
            "       r.inactivo             as inactivo " +
            "from recordatorio r " +
            "         left join recordatorio_item recordatorio_item on r.id = recordatorio_item.id_recordatorio " +
            "         left join agenda a on r.id_agenda = a.id " +
            "where (r.id_apm = :apmId or a.apm_id = :apmId) " +
            "  and r.inactivo = 0"
            , nativeQuery = true)
    List<RecordatorioProjection> findRecordatoriosForApm(Long apmId);

    List<Recordatorio> findAllByAgenda_Apm_IdAndAgendaInicioBeforeAndAgendaFinAfter(Long apmId, Date inicio, Date fin);

    List<Recordatorio> findAllByIdIn(List<Long> recordatoriosId);

    @Modifying
    @Query(value = "UPDATE Recordatorio r SET r.inactivo = TRUE, r.modifiedBy =  :modifiedBy, r.modified = :modifiedDate WHERE r.id in :recordatoriosId")
    void disableRecordatorioWithIdIn(List<Long> recordatoriosId, String modifiedBy, Date modifiedDate);

    @Modifying
    @Query(value = "update recordatorio set id_doctor = :newDoctorId where id_doctor = :oldDoctorId ", nativeQuery = true)
    void updateDoctorId(Long oldDoctorId, Long newDoctorId);

    List<Recordatorio> findRecordatorioByApm_Id(Long id);

}
