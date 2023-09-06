package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.CarteraMedicaEstado;
import ar.elea.apx.backend.projection.CarteraMedicaEstadosProjection;
import ar.elea.apx.backend.projection.KpiSolicitudesProjection;
import ar.elea.apx.backend.projection.ReporteAltasBajasExcelProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @author Guillermo Nasi
 */
public interface CarteraMedicaEstadoRepository extends JpaRepository<CarteraMedicaEstado, Long>, JpaSpecificationExecutor<CarteraMedicaEstado> {

   @Query(value = "SELECT estado from cartera_medica_estado estado  " +
            " where estado.apm_id = :apmId and estado.doctor_id = :doctorId and  " +
            " estado.id = (SELECT top 1 id  from cartera_medica_estado where apm_id = :apmId and doctor_id = :doctorId order by  fecha_solicitud desc )", nativeQuery = true)
    CarteraMedicaEstado findLastStatus(Long apmId, Long doctorId);

    @Modifying
    @Query(value = "update cartera_medica_estado set doctor_id = :newDoctorId where doctor_id = :oldDoctorId ", nativeQuery = true)
    void updateDoctorId(Long oldDoctorId, Long newDoctorId);

    @Query(value = "SELECT COUNT(cm.doctor_id) as cantidad, cme.estado " +
            "FROM cartera_medica_estado cme " +
            "JOIN apm a on a.id=cme .apm_id " +
            "JOIN cartera_medica cm ON cm.cartera_medica_estado_id = cme.id " +
            "JOIN usuario u ON u.id = a.gerente_regional_id " +
            "WHERE a.inactivo = 0 " +
            "AND cm.inactivo =0 " +
            "AND u.inactivo = 0 " +
            "AND u.rol = 'GERENTE_REGIONAL' " +
            "AND (:gerenteId is null or a.gerente_regional_id = :gerenteId) " +
            "AND (:apmId is null or cm.apm_id  = :apmId) " +
            "AND (:inicioCiclo is null or :finCiclo is null or cme.fecha_solicitud BETWEEN :inicioCiclo AND :finCiclo) " +
            "GROUP BY FORMAT(cme.fecha_solicitud, 'yyyy-MM-dd'), cme.estado", nativeQuery = true)
    List<KpiSolicitudesProjection> getSolicitudes(Long gerenteId, Long apmId, LocalDate inicioCiclo, LocalDate finCiclo);

    @Query(value =
            "SELECT cme.id, d.primerApellido ApellidoDoctor, d.primerNombre NombreDoctor, e.nombre Especialidad, " +
                    " CASE cme.estado " +
                    "   when 'BAJA_SOLICITADA' THEN 'PENDIENTE' " +
                    "   when 'ALTA_SOLICITADA' THEN 'PENDIENTE' " +
                    "   when 'ACTIVACION_SOLICITADA' THEN 'PENDIENTE' " +
                    "   when 'BAJA_RECHAZADA' THEN 'RECHAZADA' " +
                    "   when 'ALTA_RECHAZADA' THEN 'RECHAZADA' " +
                    "   when 'ACTIVACION_RECHAZADA' THEN 'RECHAZADA' " +
                    "   when 'BAJA_APROBADA' THEN 'APROBADA' " +
                    "  END Tipo, cme.estado, cme.fecha_solicitud FechaSolicitud" +
                    " from cartera_medica_estado cme " +
                    " join doctor d   " +
                    " on ( " +
                    "    cme.estado not like 'ALTA_APROBADA' AND " +
                    "    cme.estado not like 'ACTIVACION_APROBADA' AND " +
                    "    d.inactivo = 0 and " +
                    "    cme.doctor_id = d.id " +
                    "  ) " +
                    " JOIN especialidad e on (d.especialidad_id = e.id) " +
                    " JOIN cartera_medica cms " +
                    "  ON (cms.cartera_medica_estado_id = cme.id and cms.inactivo = 0 and cms.inactivo = 0 and cms.apm_id = cme.apm_id) " +
                    " JOIN apm ap " +
                    "  on(ap.id = cme.apm_id and ap.inactivo= 0) " +
                    " WHERE  " +
                    "  cme.apm_id = :apmId",
            countQuery = "" +
                    "SELECT count(1)" +
                    " from cartera_medica_estado cme " +
                    " join doctor d   " +
                    " on ( " +
                    "    cme.estado not like 'ALTA_APROBADA' AND " +
                    "    cme.estado not like 'ACTIVACION_APROBADA' AND " +
                    "    d.inactivo = 0 and " +
                    "    cme.doctor_id = d.id " +
                    "  ) " +
                    " JOIN especialidad e on (d.especialidad_id = e.id) " +
                    " JOIN cartera_medica cms " +
                    "  ON (cms.cartera_medica_estado_id = cme.id and cms.inactivo = 0 and cms.inactivo = 0 and cms.apm_id = cme.apm_id) " +
                    " JOIN apm ap " +
                    "  on(ap.id = cme.apm_id and ap.inactivo= 0) " +
                    " WHERE  " +
                    "  cme.apm_id = :apmId",
            nativeQuery = true)
    Page<CarteraMedicaEstadosProjection> getSolicitudes(Long apmId, Pageable pageable);

    @Query(value =
            "select ctm from CarteraMedicaEstado ctm " +
                    "    INNER join Doctor doc on ctm.doctor.id=doc.id " +
                    "    INNER join cartera_medica cm on ctm.id = cm.estado.id " +
                    "    INNER join DatosVisita dv on cm.datosVisita.id = dv.id " +
                    "    INNER join Apm ap on (cm.apm.id = ap.id AND ap.id = ctm.apm.id)" +
                    "    LEFT join Institucion i on i.id = dv.institucion.id " +
                    "    LEFT join Direccion dir on (dv.direccion.id = dir.id or i.direccion.id = dir.id) " +
                    "    INNER join CodigoPostal codpost on dir.codigoPostal.id=codpost.id " +
                    "    where cm.inactivo = 0 AND " +
                    "    (:primerNombre is null or doc.primerNombre like %:primerNombre%) and " +
                    "    (:primerApellido is null or doc.primerApellido like %:primerApellido%) and  " +
                    "    (:idGerenteReginal is null or ap.gerenteRegional.id = :idGerenteReginal) and " +
                    "    (:idApm is null or ctm.apm.id = :idApm) and " +
                    "    (:idEspecialidad is null or doc.especialidad.id = :idEspecialidad) and " +
                    "    (:fechaCargaDesde is null or ctm.fechaSolicitud >= :fechaCargaDesde) and " +
                    "    (:fechaCargaHasta is null or ctm.fechaSolicitud <= :fechaCargaHasta) and " +
                    "    (:fechaConfirmacionDesde is null or ctm.fechaConfirmacion >= :fechaConfirmacionDesde) and " +
                    "    (:fechaConfirmacionHasta is null or ctm.fechaConfirmacion <= :fechaConfirmacionHasta) and " +
                    "    (:provinciaId is null or codpost.provincia.id = :provinciaId) and " +
                    "    (:localidadId is null or codpost.localidad.id = :localidadId) and " +
                    "    (:ciudadId is null or codpost.ciudad.id = :ciudadId) and " +
                    "    (:matriculaNacional is null or doc.matriculaNacional like %:matriculaNacional%) and " +
                    "    (:matriculaProvincial is null or doc.matriculaProvincial like %:matriculaProvincial%) and  " +
                    "    (:tipo is null or ctm.estado =  " +
                    "       CASE " +
                    "           WHEN :tipo is not null      " +
                    "           and :tipo = 'BAJA' THEN 'BAJA_SOLICITADA'      " +
                    "           WHEN :tipo is not null      " +
                    "           and :tipo = 'ALTA' THEN 'ALTA_SOLICITADA'      " +
                    "           WHEN :tipo is not null      " +
                    "           and :tipo = 'ACTIVACION' THEN 'ACTIVACION_SOLICITADA'      " +
                    "       END) " +
                    "       AND (:tipo is not null " +
                    "       or (ctm.estado = 'ALTA_SOLICITADA' or " +
                    "                 ctm.estado = 'BAJA_SOLICITADA' or " +
                    "                 ctm.estado = 'ACTIVACION_SOLICITADA')) ",

            countQuery =  "select count(1) from CarteraMedicaEstado ctm " +
                    "    INNER join Doctor doc on ctm.doctor.id=doc.id " +
                    "    INNER join cartera_medica cm on ctm.id = cm.estado.id " +
                    "    INNER join DatosVisita dv on cm.datosVisita.id = dv.id " +
                    "    INNER join Apm ap on (cm.apm.id = ap.id AND ap.id = ctm.apm.id) " +
                    "    LEFT join Institucion i on i.id = dv.institucion.id " +
                    "    LEFT join Direccion dir on (dv.direccion.id = dir.id or i.direccion.id = dir.id) " +
                    "    INNER join CodigoPostal codpost on dir.codigoPostal.id=codpost.id " +
                    "    where cm.inactivo = 0 AND" +
                    "    (:primerNombre is null or doc.primerNombre like %:primerNombre%) and " +
                    "    (:primerApellido is null or doc.primerApellido like %:primerApellido%) and  " +
                    "    (:idGerenteReginal is null or ap.gerenteRegional.id = :idGerenteReginal) and " +
                    "    (:idApm is null or ctm.apm.id = :idApm) and " +
                    "    (:idEspecialidad is null or doc.especialidad.id = :idEspecialidad) and " +
                    "    (:fechaCargaDesde is null or ctm.fechaSolicitud >= :fechaCargaDesde) and " +
                    "    (:fechaCargaHasta is null or ctm.fechaSolicitud <= :fechaCargaHasta) and " +
                    "    (:fechaConfirmacionDesde is null or ctm.fechaConfirmacion >= :fechaConfirmacionDesde) and " +
                    "    (:fechaConfirmacionHasta is null or ctm.fechaConfirmacion <= :fechaConfirmacionHasta) and " +
                    "    (:provinciaId is null or codpost.provincia.id = :provinciaId) and " +
                    "    (:localidadId is null or codpost.localidad.id = :localidadId) and " +
                    "    (:ciudadId is null or codpost.ciudad.id = :ciudadId) and " +
                    "    (:matriculaNacional is null or doc.matriculaNacional like %:matriculaNacional%) and " +
                    "    (:matriculaProvincial is null or doc.matriculaProvincial like %:matriculaProvincial%) and  " +
                    "    (:tipo is null or ctm.estado =  " +
                    "       CASE " +
                    "           WHEN :tipo is not null      " +
                    "           and :tipo = 'BAJA' THEN 'BAJA_SOLICITADA'      " +
                    "           WHEN :tipo is not null      " +
                    "           and :tipo = 'ALTA' THEN 'ALTA_SOLICITADA'      " +
                    "           WHEN :tipo is not null      " +
                    "           and :tipo = 'ACTIVACION' THEN 'ACTIVACION_SOLICITADA'      " +
                    "       END) " +
                    "       AND (:tipo is not null " +
                    "       or (ctm.estado = 'ALTA_SOLICITADA' or " +
                    "                 ctm.estado = 'BAJA_SOLICITADA' or " +
                    "                 ctm.estado = 'ACTIVACION_SOLICITADA')) ")
    Page<CarteraMedicaEstado> getSolicitudesAllPendiente(Long idGerenteReginal, Long idApm, Long idEspecialidad,
                                                         String primerNombre,
                                                         String primerApellido,
                                                         Date fechaCargaDesde, Date fechaCargaHasta,
                                                         Date fechaConfirmacionDesde, Date fechaConfirmacionHasta ,
                                                         Long provinciaId, Long localidadId, Long ciudadId,
                                                         Integer matriculaProvincial, Integer matriculaNacional,
                                                         String tipo,
                                                         Pageable pageable);

    @Query(value = "EXEC Reporte_Aprobacion_Altas_Bajas @ciclo=:ciclo", nativeQuery = true)
    List<ReporteAltasBajasExcelProjection> getReporteSolicitudes(@Param("ciclo") Integer ciclo);

}
