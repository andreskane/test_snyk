package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.Transferencia;
import ar.elea.apx.backend.projection.ReporteTransferenciasExcelProjection;
import ar.elea.apx.backend.view.TransferenciaView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

    @Query(name = "TransferenciasPendientes", nativeQuery = true)
    List<TransferenciaView> findAllUserPending(Long idUsuario);

    @Query(value = "select t.* " +
            "from transferencia t " +
            "where " +
            "t.id_usuario = :idUsuario and " +
            "((t.fecha is null and fechaProgramada is null) or (t.fecha is null and programada_confirmada = 0))",
            nativeQuery = true)
    List<Transferencia> findAllPendingForUser(Long idUsuario);

    @Modifying
    @Query(value =
            "delete from transferencia " +
            "where " +
            "(fecha is null or (fecha is null and fechaProgramada is not null and programada_confirmada = 0)) " +
            "and id in :ids",
            nativeQuery = true)
    void deleteAllById(List<Long> ids);


    @Query(value = "select t.* " +
            "from transferencia t " +
            "where " +
            "t.programada_confirmada = 1 and fecha is null",
    nativeQuery = true)
    List<Transferencia> findAllProgrammedTransfersForToday();

    @Query(name = "TransferenciasProgramadas",
            countName = "TransferenciasProgramadasCount", nativeQuery = true)
    Page<TransferenciaView> programedTransfers(String doctorNombre, String doctorApellido, Long apmOrigenId, Long apmDestinoId, Long usuarioId, Date fechaProgramada, String tipo, Pageable pageable);

    List<Transferencia> findByCarteraMedicaIdAndApmDestinoId(Long idCartera, Long idApmDestino);

    @Query(value = "EXEC Reporte_Transferencias_Programadas @ciclo = :ciclo", nativeQuery = true)
    List<ReporteTransferenciasExcelProjection> getReporteTransferenciasProgramadas(@Param("ciclo") Long ciclo);

    @Query(value = "EXEC Reporte_Transferencias_Pendientes @ciclo = :ciclo", nativeQuery = true)
    List<ReporteTransferenciasExcelProjection> getReporteTransferenciasPendientes(@Param("ciclo") Long ciclo);

}
