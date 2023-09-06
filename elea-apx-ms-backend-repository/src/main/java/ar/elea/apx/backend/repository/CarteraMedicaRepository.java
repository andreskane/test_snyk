package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.CarteraMedica;
import ar.elea.apx.backend.entity.Doctor;
import ar.elea.apx.backend.projection.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @author Felipe Jimenez
 */
@Repository
public interface CarteraMedicaRepository extends JpaRepository<CarteraMedica, Long> {

    @Query("select cm.doctor from cartera_medica cm where cm.apm.id = :apmId")
    List<Doctor> findCarteraMedicaApm(Long apmId);

    CarteraMedica findByApmIdAndDoctorIdAndInactivoIsFalse(Long apmId, Long doctorId);

    @Query(value = "SELECT cm.id, cm.apm_id, cm.doctor_id , cm.datos_visita_id," +
            "cm.approved, cm.created_date , cm.modified_by , cm.modified_date , cm.inactivo , cartera_medica_estado_id " +
            "FROM cartera_medica cm " +
            "LEFT JOIN cartera_medica_estado cme ON cme.id= cm.cartera_medica_estado_id " +
            "LEFT JOIN doctor d ON d.id = cm.doctor_id " +
            "LEFT JOIN apm a ON a.id = cm.apm_id " +
            "LEFT JOIN datos_visita dv ON dv.id = cm.datos_visita_id " +
            "LEFT JOIN institucion i ON i.id = dv.institucion_id " +
            "WHERE cm.apm_id = :id " +
            "AND cm.inactivo = 0 " +
            "AND d.inactivo = 0 " +
            "AND (cme.estado = 'ALTA_APROBADA' " +
            "OR cme.estado = 'BAJA_RECHAZADA ' " +
            "OR cme.estado = 'ACTIVACION_APROBADA' " +
            "OR cme.estado = 'BAJA_SOLICITADA' " +
            "OR (cme.estado = 'BAJA_APROBADA' AND cme.fecha_confirmacion BETWEEN :inicio AND :fin) " +
            "OR (cme.estado = 'ALTA_SOLICITADA' OR cme.estado = 'ACTIVACION_SOLICITADA') ) " +
            "GROUP BY cm.id, cm.apm_id, cm.doctor_id , cm.datos_visita_id, cm.datos_visita_id," +
            "cm.approved, cm.created_date, cm.modified_by, cm.modified_date , cm.inactivo , cartera_medica_estado_id, d.primerApellido, d.primerNombre ",
            countQuery = "WITH total as (SELECT cm.id, cm.apm_id, cm.doctor_id , cm.datos_visita_id, " +
                    "cm.approved, cm.created_date , cm.modified_by , cm.modified_date , cm.inactivo , cartera_medica_estado_id " +
                    "FROM cartera_medica cm " +
                    "LEFT JOIN cartera_medica_estado cme ON cme.id= cm.cartera_medica_estado_id " +
                    "LEFT JOIN doctor d ON d.id = cm.doctor_id " +
                    "LEFT JOIN apm a ON a.id = cm.apm_id " +
                    "LEFT JOIN datos_visita dv ON dv.id = cm.datos_visita_id " +
                    "LEFT JOIN institucion i ON i.id = dv.institucion_id " +
                    "WHERE cm.apm_id = :id " +
                    "AND cm.inactivo = 0 " +
                    "AND d.inactivo = 0 " +
                    "AND (cme.estado = 'ALTA_APROBADA' " +
                    "OR cme.estado = 'BAJA_RECHAZADA ' " +
                    "OR cme.estado = 'ACTIVACION_APROBADA' " +
                    "OR cme.estado = 'BAJA_SOLICITADA' " +
                    "OR (cme.estado = 'BAJA_APROBADA' AND cme.fecha_confirmacion BETWEEN :inicio AND :fin) " +
                    "OR (cme.estado = 'ALTA_SOLICITADA' OR cme.estado = 'ACTIVACION_SOLICITADA') ) " +
                    "GROUP BY cm.id, cm.apm_id, cm.doctor_id , cm.datos_visita_id, cm.datos_visita_id," +
                    "cm.approved, cm.created_date, cm.modified_by, cm.modified_date , cm.inactivo , cartera_medica_estado_id, d.primerApellido, d.primerNombre  )" +
                    "SELECT COUNT(*) FROM total",
            nativeQuery = true
            )
    Page<CarteraMedica> findActiveCarteraMedicaForApm(Long id, Pageable pageable, Date inicio, Date fin);

    @Query(value = "SELECT distinct cm from cartera_medica cm join fetch cm.datosVisita join fetch cm.doctor join fetch cm.apm " +
            "join fetch cm.estado left join cm.datosVisita.horarios WHERE cm.apm.id = :id  AND cm.inactivo = 0  " +
            "AND (cm.estado.estado = 'ALTA_APROBADA' " +
            "OR cm.estado.estado = 'ACTIVACION_APROBADA' " +
            "OR cm.estado.estado = 'BAJA_SOLICITADA' " +
            "OR cm.estado.estado = 'BAJA_RECHAZADA' " +
            "OR (cm.estado.estado = 'BAJA_APROBADA' AND cm.estado.fechaConfirmacion BETWEEN :inicio AND :fin) " +
            "OR (cm.estado.estado = 'ALTA_SOLICITADA' OR cm.estado.estado = 'ACTIVACION_SOLICITADA') )")
    List<CarteraMedica> findCarteraById(Long id, Date inicio, Date fin);

    Page<CarteraMedica> findAllCarteraMedicaByApmIdAndInactivoIsFalse(Long id,Pageable pageable);

    @Query(value = "WITH total as (SELECT cm.id, cm.apm_id, cm.doctor_id , cm.datos_visita_id, " +
            "cm.approved, cm.created_date , cm.modified_by , cm.modified_date , cm.inactivo , cartera_medica_estado_id " +
            "FROM cartera_medica cm " +
            "LEFT JOIN cartera_medica_estado cme ON cme.id= cm.cartera_medica_estado_id " +
            "LEFT JOIN agenda a ON a.doctor_id = cm.doctor_id and  a.apm_id=cm.apm_id " +
            "WHERE cm.apm_id = :id " +
            "AND cm.inactivo = 0 " +
            "AND (cme.estado = 'ALTA_APROBADA' " +
            "OR cme.estado = 'ACTIVACION_APROBADA' " +
            "OR cme.estado = 'BAJA_SOLICITADA' " +
            "OR cme.estado = 'BAJA_RECHAZADA' " +
            "OR (cme.estado = 'BAJA_APROBADA' AND cme.fecha_confirmacion BETWEEN :inicio AND :fin) " +
            "OR ((cme.estado = 'ALTA_SOLICITADA' OR cme.estado = 'ACTIVACION_SOLICITADA') AND a.inicio is null ) ) " +
            "GROUP BY cm.id, cm.apm_id, cm.doctor_id , cm.datos_visita_id, cm.datos_visita_id, " +
            "cm.approved, cm.created_date, cm.modified_by, cm.modified_date , cm.inactivo , cartera_medica_estado_id ) " +
            "SELECT COUNT(*) FROM total;",
            nativeQuery = true
    )
    Integer countActiveCarteraMedicaForApm(Long id, Date inicio, Date fin);

    List<CarteraMedica> findAllByDoctorIdAndInactivoIsFalse(Long id);

    @Modifying
    @Query(value = "update cartera_medica set doctor_id = :newDoctorId where doctor_id = :oldDoctorId ", nativeQuery = true)
    void updateDoctorId(Long oldDoctorId, Long newDoctorId);


    @Query(value =
            "SELECT cm.* FROM cartera_medica cm " +
                    "inner join apm a on cm.apm_id = a.id " +
                    "inner join doctor d on cm.doctor_id = d.id " +
                    "inner join cartera_medica_estado cme on cm.cartera_medica_estado_id = cme.id " +
                    "inner join datos_visita dv on cm.datos_visita_id = dv.id " +
                    "inner join usuario u on u.id = a.gerente_regional_id " +
                    "left join direccion dir on dv.id_direccion = dir.id " +
                    "left join codigo_postal cp on dir.id_codigo_postal = cp.id " +
                    "where (cme.estado <>  'BAJA_APROBADA'" +
                    "and cme.estado <>  'ALTA_RECHAZADA' )" +
                    "and (:gerenteRegionalId is null or u.id = :gerenteRegionalId) " +
                    "and (:apmId is null or cm.apm_id = :apmId) " +
                    "and (:doctorId is null or cm.doctor_id = :doctorId) " +
                    "and (:provinciaId is null or cp.id_provincia = :provinciaId) "
            , nativeQuery = true)
    List<CarteraMedica> findCarteraMedicaByCriteria(
            @Param("gerenteRegionalId") Long gerenteRegionalId,
            @Param("apmId") Long apmId,
            @Param("doctorId") Long doctorId,
            @Param("provinciaId") Long provinciaId);

    @Query(value = "SELECT cm.* FROM cartera_medica cm " +
            "JOIN doctor d ON (d.id=cm.doctor_id) " +
            "JOIN apm a ON (a.id=cm.apm_id) " +
            "WHERE cm.inactivo =0 " +
            "AND d.inactivo = 0 " +
            "AND a.inactivo =0 " +
            "AND FORMAT( CAST (d.fechaNacimiento as date), 'MM-dd') " +
            " = FORMAT(CAST(:fecha as date), 'MM-dd')", nativeQuery = true)
    List<CarteraMedica> findCarteraMedicaCumpleanos(Date fecha);

    @Query(value = "SELECT a.id AS apmId, a.gerente_regional_id AS gerenteId, " +
            "COUNT(d.id) AS totalDoctores, " +
            "STRING_AGG(d.id, ',') AS doctoresId, " +
            "SUM(dv.frecuencia) AS contactosObjetivos " +
            "FROM cartera_medica cm " +
            "JOIN cartera_medica_estado cme ON cme.id =cm.cartera_medica_estado_id " +
            "LEFT JOIN apm a on (a.id = cm.apm_id) " +
            "LEFT JOIN doctor d on (d.id=cm.doctor_id) " +
            "LEFT JOIN datos_visita dv on (dv.id=cm.datos_visita_id) " +
            "LEFT JOIN usuario u on u.id=a.gerente_regional_id " +
            "WHERE (:gerenteId is null or a.gerente_regional_id =:gerenteId) " +
            "AND cm.inactivo = 0 " +
            "AND d.inactivo =0 " +
            "AND a.inactivo =0 " +
            "AND u.inactivo =0 " +
            "AND u.rol ='GERENTE_REGIONAL' " +
            " AND (cme.estado = 'ALTA_APROBADA'  " +
            "  OR cme.estado = 'ACTIVACION_APROBADA'  " +
            "  OR cme.estado = 'BAJA_RECHAZADA'  " +
            "  OR cme.estado IS NULL  " +
            "  OR (cme.estado = 'BAJA_APROBADA'  " +
            "   AND (cme.fecha_confirmacion BETWEEN (  " +
            "   SELECT  " +
            "    c.inicio  " +
            "   from  " +
            "    ciclo c  " +
            "   WHERE  " +
            "    c.id = :cicloId) AND (  " +
            "   SELECT  " +
            "    c.fin  " +
            "   from  " +
            "    ciclo c  " +
            "   WHERE  " +
            "    c.id = :cicloId))))   " +
            "GROUP by a.id, a.gerente_regional_id",
            nativeQuery = true)
    List<CarteraMedicaApmProjection> findCarteraMedicaApmKpi(Long gerenteId, Long cicloId);


    @Query(value ="WITH visitas_apm_ciclo as ( " +
            "SELECT apm_id, FORMAT(ag.inicio, 'yyyy-MM-dd') fecha, COUNT(*) totalVisitasCiclo, ag.visita_tipo, ag.visita_exitosa " +
            "FROM agenda ag " +
            "JOIN apm on (apm.id = ag.apm_id) " +
            "WHERE apm.inactivo = 0 " +
            "AND agenda_tipo_id = 0 " +
            "AND (:exitosa is null or ag.visita_exitosa = :exitosa ) " +
            "AND (:gerenteId is null or CAST(apm.gerente_regional_id as varchar) in (SELECT value FROM STRING_SPLIT(convert(varchar(max), :gerenteId), ','))) " +
            "AND (:apmId is null or apm.id = :apmId) " +
            "AND ag.inicio >= :inicio " +
            "AND ag.inicio <= :fin " +
            "AND ag.inactivo = 0 " +
            "GROUP BY apm_id, FORMAT (ag.inicio, 'yyyy-MM-dd'), ag.visita_tipo, ag.visita_exitosa) " +
            "SELECT cm.apm_id as id, " +
            "       visitas_apm_ciclo.totalVisitasCiclo as totalVisitasCiclo, " +
            "       visitas_apm_ciclo.fecha as fecha, " +
            "       visitas_apm_ciclo.visita_tipo as visitaTipo, " +
            "       visitas_apm_ciclo.visita_exitosa as visitaExitosa , a.gerente_regional_id as gerenteId " +
            "FROM visitas_apm_ciclo " +
            "JOIN apm a ON(a.id = visitas_apm_ciclo.apm_id) " +
            "JOIN cartera_medica cm ON(cm.apm_id=visitas_apm_ciclo.apm_id) " +
            "JOIN cartera_medica_estado cme ON cme.id = cartera_medica_estado_id " +
            "JOIN datos_visita dv ON(dv.id=cm.datos_visita_id) " +
            "JOIN usuario u ON u.id=a.gerente_regional_id " +
            "WHERE a.inactivo = 0 " +
            "AND cm.inactivo = 0 " +
            "AND u.inactivo = 0 " +
            "AND u.rol= 'GERENTE_REGIONAL' " +
            "AND (cme.estado = 'ALTA_APROBADA' OR cme.estado = 'ACTIVACION_APROBADA' OR cme.estado = 'BAJA_RECHAZADA' OR cme.estado IS NULL " +
            "  OR (cme.estado = 'BAJA_APROBADA' AND (cme.fecha_confirmacion BETWEEN :inicio AND :fin ))) " +
            "and (:gerenteId is null or CAST(a.gerente_regional_id as varchar) in (SELECT value FROM STRING_SPLIT(convert(varchar(max), :gerenteId), ','))) " +
            "AND (:apmId is null or a.id = :apmId) " +
            "GROUP BY cm.apm_id, " +
            "      visitas_apm_ciclo.totalVisitasCiclo, " +
            "      visitas_apm_ciclo.fecha, " +
            "      visitas_apm_ciclo.visita_tipo, " +
            "      visitas_apm_ciclo.visita_exitosa, a.gerente_regional_id ",
            nativeQuery = true)
    List<KpiVisitasCoberturaProjection> findCoberturaVisitasByGerenteByCiclo(String gerenteId, Boolean exitosa, Long apmId, LocalDate inicio, LocalDate fin);

    List<CarteraMedica> findByEstado_id(Long id);

    @Query(value = "SELECT a.id as apmId, a.primerNombre as primerNombreApm, a.primerApellido as primerApellidoApm, a.gerente_regional_id AS gerenteId, " +
            "SUM(case when cm.inactivo = 0 then 1 else 0 end) AS totalMedicos, " +
            "COALESCE(SUM(case when cm.inactivo = 0 then dv.frecuencia else 0 end ), 0) AS contactosTarget, " +
            "a.profile_image as imagen " +
            "FROM apm a " +
            "LEFT JOIN cartera_medica cm on (cm.apm_id = a.id) " +
            "LEFT JOIN cartera_medica_estado cme ON (cme.id = cm.cartera_medica_estado_id) " +
            "LEFT JOIN doctor d on (d.id = cm.doctor_id) " +
            "LEFT JOIN datos_visita dv on (dv.id = cm.datos_visita_id) " +
            "WHERE " +
            "(a.inactivo = 0 or a.inactivo IS NULL) " +
            "AND d.inactivo = 0 " +
            "AND (cme.estado = 'ALTA_APROBADA' " +
            "OR cme.estado = 'ACTIVACION_APROBADA' " +
            "OR cme.estado = 'BAJA_RECHAZADA' " +
            "OR (cme.estado = 'BAJA_APROBADA' AND cme.fecha_confirmacion BETWEEN (SELECT c.inicio from ciclo c WHERE c.id = :cicloId) AND (SELECT c.fin from ciclo c WHERE c.id = :cicloId)) " +
            "OR cme.estado IS NULL )" +
            "AND a.gerente_regional_id =:gerenteId " +
            "AND (:nombre IS NULL OR :nombre = '' OR a.primerNombre COLLATE Latin1_general_CI_AI like concat('%',:nombre,'%')) " +
            "AND (:apellido IS NULL OR :apellido = '' OR a.primerApellido COLLATE Latin1_general_CI_AI like :apellido) " +
            "AND (:nombreCompleto IS NULL OR :nombreCompleto = '' OR CONCAT(upper(a.primerNombre), upper(a.primerApellido)) COLLATE Latin1_general_CI_AI like CONCAT('%',upper(:nombreCompleto),'%')) " +
            "GROUP BY a.id, a.primerNombre, a.primerApellido, a.gerente_regional_id, a.profile_image ",
            countQuery = "SELECT COUNT(*) FROM ( " +
                    "SELECT a.id as apmId, a.primerNombre as primerNombreApm, a.primerApellido as primerApellidoApm, a.gerente_regional_id AS gerenteId, " +
                    "SUM(case when cm.inactivo = 0 then 1 else 0 end) AS totalMedicos, " +
                    "COALESCE(SUM(case when cm.inactivo = 0 then dv.frecuencia else 0 end ), 0) AS contactosTarget, " +
                    "a.profile_image as imagen " +
                    "FROM apm a " +
                    "LEFT JOIN cartera_medica cm on (cm.apm_id = a.id) " +
                    "LEFT JOIN cartera_medica_estado cme ON (cme.id = cm.cartera_medica_estado_id) " +
                    "LEFT JOIN doctor d on (d.id = cm.doctor_id) " +
                    "LEFT JOIN datos_visita dv on (dv.id = cm.datos_visita_id) " +
                    "WHERE " +
                    "(a.inactivo = 0 or a.inactivo IS NULL) " +
                    "AND d.inactivo = 0 " +
                    "AND (cme.estado = 'ALTA_APROBADA' " +
                    "OR cme.estado = 'ACTIVACION_APROBADA' " +
                    "OR cme.estado = 'BAJA_SOLICITADA' " +
                    "OR cme.estado = 'BAJA_RECHAZADA' " +
                    "OR (cme.estado = 'BAJA_APROBADA' AND cme.fecha_confirmacion BETWEEN (SELECT c.inicio from ciclo c WHERE c.id = :cicloId) AND (SELECT c.fin from ciclo c WHERE c.id = :cicloId)) " +
                    "OR (cme.estado = 'ALTA_SOLICITADA' OR cme.estado = 'ACTIVACION_SOLICITADA' ) OR cme.estado IS NULL )" +
                    "AND a.gerente_regional_id =:gerenteId " +
                    "AND (:nombre IS NULL OR :nombre = '' OR a.primerNombre COLLATE Latin1_general_CI_AI like concat('%',:nombre,'%')) " +
                    "AND (:apellido IS NULL OR :apellido = '' OR a.primerApellido COLLATE Latin1_general_CI_AI like :apellido) " +
                    "AND (:nombreCompleto IS NULL OR :nombreCompleto = '' OR CONCAT(upper(a.primerNombre), upper(a.primerApellido)) COLLATE Latin1_general_CI_AI like CONCAT('%',upper(:nombreCompleto),'%')) " +
                    "GROUP BY a.id, a.primerNombre, a.primerApellido, a.gerente_regional_id, a.profile_image) resultado",
            nativeQuery = true)

    Page<CarteraMedicaKpiLoyaltyProjection> findCarteraMedicaKpiLoyalty(Long gerenteId, String nombre, String apellido, String nombreCompleto, Long cicloId, Pageable page);

    @Query(value = "SELECT usuario.id as gerenteId, usuario.nombre as nombre, usuario.apellido as apellido, COUNT(d.id) AS totalMedicos, COALESCE(SUM(dv.frecuencia), 0) AS contactosTarget " +
            "FROM usuario " +
            "LEFT JOIN apm a on (a.gerente_regional_id = usuario.id) " +
            "LEFT JOIN cartera_medica cm on (cm.apm_id = a.id) " +
            "LEFT JOIN cartera_medica_estado cme ON (cme.id = cm.cartera_medica_estado_id) " +
            "LEFT JOIN doctor d on (d.id = cm.doctor_id) " +
            "LEFT JOIN datos_visita dv on (dv.id = cm.datos_visita_id) " +
            "WHERE " +
            "(a.inactivo = 0 or a.inactivo IS NULL) " +
            "AND usuario.rol = 'GERENTE_REGIONAL'  " +
            "AND (usuario.inactivo = 0 or usuario.inactivo IS NULL) " +
            "AND (cm.inactivo = 0 or cm.inactivo IS NULL) " +
            "AND (cme.estado = 'ALTA_APROBADA' OR cme.estado = 'ACTIVACION_APROBADA' OR cme.estado = 'BAJA_RECHAZADA' OR cme.estado IS NULL " +
            "OR (cme.estado = 'BAJA_APROBADA' AND (cme.fecha_confirmacion BETWEEN  (SELECT c.inicio from ciclo c WHERE c.id = :cicloId) AND (SELECT c.fin from ciclo c WHERE c.id = :cicloId)))) " +
            "AND (d.inactivo = 0 or d.inactivo IS NULL) " +
            "AND (:nombre IS NULL OR :nombre = '' OR usuario.nombre COLLATE Latin1_general_CI_AI like %:nombre%) " +
            "AND (:apellido IS NULL OR :apellido = '' OR usuario.apellido COLLATE Latin1_general_CI_AI like :apellido) " +
            " AND (:nombreCompleto IS NULL OR :nombreCompleto = '' OR CONCAT(upper(usuario.nombre), upper(usuario.apellido)) COLLATE Latin1_general_CI_AI like CONCAT('%',upper(:nombreCompleto),'%'))  " +
            "GROUP BY usuario.id, usuario.nombre, usuario.apellido ",
            countQuery = "SELECT COUNT(*) FROM ( " +
                    " SELECT usuario.id as gerenteId, usuario.nombre as nombre, usuario.apellido as apellido, COUNT(d.id) AS totalMedicos, COALESCE(SUM(dv.frecuencia), 0) AS contactosTarget  " +
                    " FROM usuario  " +
                    " LEFT JOIN apm a on (a.gerente_regional_id = usuario.id)  " +
                    " LEFT JOIN cartera_medica cm on (cm.apm_id = a.id)  " +
                    " LEFT JOIN cartera_medica_estado cme ON (cme.id = cm.cartera_medica_estado_id)  " +
                    " LEFT JOIN doctor d on (d.id = cm.doctor_id)  " +
                    " LEFT JOIN datos_visita dv on (dv.id = cm.datos_visita_id)  " +
                    " WHERE  " +
                    " (a.inactivo = 0 or a.inactivo IS NULL)  " +
                    " AND usuario.rol = 'GERENTE_REGIONAL'  " +
                    " AND (usuario.inactivo = 0 or usuario.inactivo IS NULL)  " +
                    " AND (cm.inactivo = 0 or cm.inactivo IS NULL)  " +
                    " AND (cme.estado = 'ALTA_APROBADA' OR cme.estado = 'ACTIVACION_APROBADA' OR cme.estado = 'BAJA_RECHAZADA' OR cme.estado IS NULL  " +
                    " OR (cme.estado = 'BAJA_APROBADA' AND (cme.fecha_confirmacion BETWEEN  (SELECT c.inicio from ciclo c WHERE c.id = :cicloId) AND (SELECT c.fin from ciclo c WHERE c.id = :cicloId))))  " +
                    " AND (d.inactivo = 0 or d.inactivo IS NULL)  " +
                    " AND (:nombre IS NULL OR :nombre = '' OR usuario.nombre COLLATE Latin1_general_CI_AI like %:nombre%)  " +
                    " AND (:apellido IS NULL OR :apellido = '' OR usuario.apellido COLLATE Latin1_general_CI_AI like :apellido)  " +
                    " AND (:nombreCompleto IS NULL OR :nombreCompleto = '' OR CONCAT(upper(usuario.nombre), upper(usuario.apellido)) COLLATE Latin1_general_CI_AI like CONCAT('%',upper(:nombreCompleto),'%'))  " +
                    " GROUP BY usuario.id, usuario.nombre, usuario.apellido  " +
                    ") resultado ",
            nativeQuery = true)

    Page<CarteraMedicaKpiLoyaltyAdminProjection> findCarteraMedicaKpiLoyaltyAdmin(String nombre, String apellido, String nombreCompleto, Long cicloId, Pageable page);

    @Query(value = "WITH visitas_medico as ( " +
            "SELECT doctor_id doctorId, COUNT(id) AS totalVisitas " +
            "FROM agenda a " +
            "WHERE " +
            " agenda_tipo_id = 0 " +
            "AND visita_exitosa = 1 " +
            "AND inactivo = 0 " +
            "GROUP BY doctor_id) " +
            "SELECT " +
            "a.id as doctorId, a.primerNombre as nombreMedico, a.primerApellido as apellidoMedico, e.nombre AS especialidad, a.profile_image imagen, " +
            "SUM(dv.frecuencia) AS contactosTarget, " +
            "COUNT(ap.id) totalApms, " +
            "vm.totalVisitas, " +
            "CONVERT(decimal(2,1), vm.totalVisitas*1.0/SUM(dv.frecuencia))*100 cobertura " +
            "FROM " +
            "visitas_medico vm " +
            "RIGHT JOIN doctor a ON a.id=vm.doctorId " +
            "RIGHT JOIN especialidad e ON e.id=a.especialidad_id " +
            "RIGHT JOIN cartera_medica cm ON cm.doctor_id =a.id " +
            "RIGHT JOIN datos_visita dv ON dv.id =cm.datos_visita_id " +
            "RIGHT JOIN apm ap ON ap.id =cm.apm_id " +
            "WHERE (:apmId IS NULL OR :apmId = '' OR ap.id = :apmId) " +
            "AND cm.inactivo = 0 " +
            "AND a.inactivo =0 " +
            "AND (:nombre IS NULL OR :nombre = '' OR a.primerNombre COLLATE Latin1_general_CI_AI like %:nombre%) " +
            "AND (:apellido IS NULL OR :apellido = '' OR a.primerApellido  COLLATE Latin1_general_CI_AI like :apellido) " +
            "AND (:nombreCompleto IS NULL OR :nombreCompleto = '' OR CONCAT(a.primerNombre, a.primerApellido) COLLATE Latin1_general_CI_AI like %:nombreCompleto%) " +
            "GROUP BY a.id, a.primerNombre, a.primerApellido, e.nombre, vm.totalVisitas, a.profile_image",
            countQuery = "WITH visitas_medico as ( " +
                    "SELECT doctor_id doctorId, COUNT(id) AS totalVisitas " +
                    "FROM agenda a " +
                    "WHERE " +
                    " agenda_tipo_id =0 " +
                    "AND visita_exitosa = 1 " +
                    "AND inactivo = 0 " +
                    "GROUP BY doctor_id) " +
                    "SELECT " +
                    "COUNT(*) " +
                    "FROM " +
                    "visitas_medico vm " +
                    "RIGHT JOIN doctor a ON a.id=vm.doctorId " +
                    "RIGHT JOIN especialidad e ON e.id=a.especialidad_id " +
                    "RIGHT JOIN cartera_medica cm ON cm.doctor_id =a.id " +
                    "RIGHT JOIN datos_visita dv ON dv.id =cm.datos_visita_id " +
                    "RIGHT JOIN apm ap ON ap.id =cm.apm_id " +
                    "WHERE (:apmId IS NULL OR :apmId = '' OR ap.id = :apmId) " +
                    "AND cm.inactivo = 0 " +
                    "AND a.inactivo =0 " +
                    "AND (:nombre IS NULL OR :nombre = '' OR a.primerNombre COLLATE Latin1_general_CI_AI like %:nombre%) " +
                    "AND (:apellido IS NULL OR :apellido = '' OR a.primerApellido  COLLATE Latin1_general_CI_AI like :apellido) " +
                    "AND (:nombreCompleto IS NULL OR :nombreCompleto = '' OR CONCAT(a.primerNombre, a.primerApellido) COLLATE Latin1_general_CI_AI like %:nombreCompleto%)",
            nativeQuery = true)
    Page<KpiMedicosApmProjection> findKpiMedicosApm(Long apmId, String nombre, String apellido, String nombreCompleto, Pageable page);

    @Query(value = "SELECT cm FROM cartera_medica cm WHERE cm.apm.gerenteRegional.id = :gerenteId")
    List<CarteraMedica> findCarteraMedicaByGerenteId(Long gerenteId);

    @Query(value = "WITH doctores_especialidad AS ( " +
            " SELECT a.id id_apm, " +
            "            a.primerNombre nombre, " +
            "            a.primerApellido apellidos, " +
            "            COALESCE(SUM(case when cm.inactivo = 0 then dv.frecuencia else 0 end ), 0) contactos_target, " +
            "            especialidad.id id_especialidad, " +
            "            especialidad.nombre nombre_especialidad, " +
            "            SUM(case when cm.inactivo = 0 then 1 else 0 end) medicos, " +
            "            a.profile_image imagen " +
            " FROM apm a " +
            " LEFT JOIN cartera_medica cm ON (cm.apm_id = a.id) " +
            " LEFT JOIN cartera_medica_estado cme ON (cme.id = cm.cartera_medica_estado_id) " +
            " LEFT JOIN doctor d ON (d.id = cm.doctor_id) " +
            " LEFT JOIN datos_visita dv ON (dv.id = cm.datos_visita_id) " +
            " LEFT JOIN especialidad ON (especialidad.id = d.especialidad_id) " +
            " WHERE (a.inactivo = 0 OR a.inactivo IS NULL) " +
            " AND d.inactivo = 0 " +
            " AND (cme.estado = 'ALTA_APROBADA' OR cme.estado = 'ACTIVACION_APROBADA' OR cme.estado = 'BAJA_RECHAZADA' OR cme.estado IS NULL " +
            " OR (cme.estado = 'BAJA_APROBADA' AND (cme.fecha_confirmacion BETWEEN  (SELECT c.inicio from ciclo c WHERE c.id = :cicloId) AND (SELECT c.fin from ciclo c WHERE c.id = :cicloId)))) " +
            " AND (especialidad.inactivo = 0 OR especialidad.inactivo IS NULL) " +
            " AND a.gerente_regional_id = :gerenteId " +
            " AND (:nombreCompleto IS NULL OR :nombreCompleto = '' OR CONCAT(a.primerNombre, a.segundoNombre, a.primerApellido, a.segundoApellido) COLLATE Latin1_general_CI_AI like %:nombreCompleto%) " +
            " AND (d.deleted = 0 OR d.deleted is null) " +
            " GROUP BY " +
            "            a.id, " +
            "            a.primerNombre, " +
            "            a.primerApellido, " +
            "            especialidad.id, " +
            "            especialidad.nombre, a.profile_image " +
            ") " +
            "SELECT  id_apm as apmId, " +
            "        nombre as nombre, " +
            "        apellidos, " +
            "        SUM ( medicos ) OVER ( " +
            "            PARTITION BY " +
            "                id_apm " +
            "        ) as totalMedicos, " +
            "        contactos_target as contactosTarget, " +
            "        id_especialidad as especialidadId, " +
            "        nombre_especialidad as especialidad, " +
            "        medicos as totalMedicosEspecialidad, " +
            "        imagen  " +
            "FROM    doctores_especialidad",
            countQuery = "WITH doctores_especialidad AS ( " +
                    " SELECT a.id id_apm, " +
                    "            a.primerNombre nombre, " +
                    "            a.primerApellido apellidos, " +
                    "            COALESCE(SUM(case when cm.inactivo = 0 then dv.frecuencia else 0 end ), 0) contactos_target, " +
                    "            especialidad.id id_especialidad, " +
                    "            especialidad.nombre nombre_especialidad, " +
                    "            SUM(case when cm.inactivo = 0 then 1 else 0 end) medicos, " +
                    "            a.profile_image imagen " +
                    " FROM apm a " +
                    " LEFT JOIN cartera_medica cm ON (cm.apm_id = a.id) " +
                    " LEFT JOIN cartera_medica_estado cme ON (cme.id = cm.cartera_medica_estado_id) " +
                    " LEFT JOIN doctor d ON (d.id = cm.doctor_id) " +
                    " LEFT JOIN datos_visita dv ON (dv.id = cm.datos_visita_id) " +
                    " LEFT JOIN especialidad ON (especialidad.id = d.especialidad_id) " +
                    " WHERE (a.inactivo = 0 OR a.inactivo IS NULL) " +
                    " AND d.inactivo = 0 " +
                    " AND (cme.estado = 'ALTA_APROBADA' OR cme.estado = 'ACTIVACION_APROBADA' OR cme.estado = 'BAJA_RECHAZADA' OR cme.estado IS NULL " +
                    " OR (cme.estado = 'BAJA_APROBADA' AND (cme.fecha_confirmacion BETWEEN  (SELECT c.inicio from ciclo c WHERE c.id = :cicloId) AND (SELECT c.fin from ciclo c WHERE c.id = :cicloId)))) " +
                    " AND (especialidad.inactivo = 0 OR especialidad.inactivo IS NULL) " +
                    " AND a.gerente_regional_id = :gerenteId " +
                    " AND (:nombreCompleto IS NULL OR :nombreCompleto = '' OR CONCAT(a.primerNombre, a.segundoNombre, a.primerApellido, a.segundoApellido) COLLATE Latin1_general_CI_AI like %:nombreCompleto%) " +
                    " AND (d.deleted = 0 OR d.deleted is null) " +
                    " GROUP BY " +
                    "            a.id, " +
                    "            a.primerNombre, " +
                    "            a.primerApellido, " +
                    "            especialidad.id, " +
                    "            especialidad.nombre, a.profile_image " +
                    ") " +
                    "SELECT COUNT(*) FROM doctores_especialidad "
            , nativeQuery = true)
    List<KpiMedicosApmEspecialidadProjection> getMedicosEspecialidadByGerenteId(Long gerenteId, String nombreCompleto, Long cicloId);


    @Query(value = "SELECT COUNT(d.id) AS total, u.id as gerenteId " +
            "FROM cartera_medica cm " +
            "JOIN apm on apm.id=cm.apm_id " +
            "JOIN doctor d on d.id =cm.doctor_id " +
            "JOIN cartera_medica_estado cme ON cme.id = cartera_medica_estado_id " +
            "JOIN usuario u ON u.id=apm.gerente_regional_id " +
            "where cm.inactivo =0 " +
            "and apm.inactivo =0 " +
            "and d.inactivo =0 " +
            "and u.inactivo =0 " +
            "and u.rol = 'GERENTE_REGIONAL' " +
            "and (cme.estado = 'ALTA_APROBADA' OR cme.estado = 'ACTIVACION_APROBADA' OR cme.estado = 'BAJA_RECHAZADA' OR cme.estado IS NULL " +
            "OR (cme.estado = 'BAJA_APROBADA' AND (cme.fecha_confirmacion BETWEEN  (SELECT c.inicio from ciclo c WHERE c.id = :cicloId) AND (SELECT c.fin from ciclo c WHERE c.id = :cicloId)))) " +
            "and (:gerenteId is null or CAST(u.id as varchar) in (SELECT value FROM STRING_SPLIT(convert(varchar(max), :gerenteId), ','))) " +
            "AND (:apmId is null or apm.id =:apmId) group by u.id"
            , nativeQuery = true)
    List<TotalesPorGerenteProjection> getTotalMedicosPorGerenteApm(String gerenteId, Long apmId, Long cicloId);

    @Query(value = "SELECT COUNT(DISTINCT(d.id)) AS total, apm.id as id " +
            "FROM cartera_medica cm " +
            "JOIN apm on apm.id=cm.apm_id " +
            "JOIN doctor d on d.id =cm.doctor_id " +
            "JOIN cartera_medica_estado cme ON cme.id = cartera_medica_estado_id " +
            "JOIN usuario u ON u.id=apm.gerente_regional_id " +
            "where cm.inactivo =0 " +
            "and apm.inactivo =0 " +
            "and d.inactivo =0 " +
            "and u.inactivo =0 " +
            "and u.rol = 'GERENTE_REGIONAL' " +
            "and (cme.estado = 'ALTA_APROBADA' OR cme.estado = 'ACTIVACION_APROBADA' OR cme.estado = 'BAJA_RECHAZADA' OR cme.estado IS NULL " +
            "OR (cme.estado = 'BAJA_APROBADA' AND (cme.fecha_confirmacion BETWEEN  (SELECT c.inicio from ciclo c WHERE c.id = :cicloId) AND (SELECT c.fin from ciclo c WHERE c.id = :cicloId)))) " +
            "and (:apmId is null or CAST(apm.id as varchar) in (SELECT value FROM STRING_SPLIT(convert(varchar(max), :apmId), ','))) " +
            "group by apm.id"
            , nativeQuery = true)
    List<TotalesProjection> getTotalMedicosApm(String apmId, Long cicloId);


    @Query(value = "SELECT COUNT(DISTINCT(d.id)) AS total " +
            "FROM cartera_medica cm " +
            "JOIN apm on apm.id=cm.apm_id " +
            "JOIN doctor d on d.id =cm.doctor_id " +
            "JOIN cartera_medica_estado cme ON cme.id = cartera_medica_estado_id " +
            "JOIN usuario u ON u.id=apm.gerente_regional_id " +
            "where cm.inactivo =0 " +
            "and apm.inactivo =0 " +
            "and d.inactivo =0 " +
            "and u.inactivo =0 " +
            "and u.rol = 'GERENTE_REGIONAL' " +
            "and (cme.estado = 'ALTA_APROBADA' OR cme.estado = 'ACTIVACION_APROBADA' OR cme.estado = 'BAJA_RECHAZADA' OR cme.estado IS NULL " +
            "OR (cme.estado = 'BAJA_APROBADA' AND (cme.fecha_confirmacion BETWEEN  (SELECT c.inicio from ciclo c WHERE c.id = :cicloId) AND (SELECT c.fin from ciclo c WHERE c.id = :cicloId)))) " +
            "and (:gerenteId is null or CAST(u.id as varchar) in (SELECT value FROM STRING_SPLIT(convert(varchar(max), :gerenteId), ','))) " +
            "AND (:apmId is null or apm.id =:apmId)"
            , nativeQuery = true)
    Long getTotalMedicosPorGerente(String gerenteId, Long apmId, Long cicloId);

    @Query(value = "SELECT COUNT(d.id) AS total " +
            "FROM cartera_medica cm " +
            "JOIN apm on apm.id=cm.apm_id " +
            "JOIN doctor d on d.id =cm.doctor_id " +
            "JOIN cartera_medica_estado cme ON cme.id = cartera_medica_estado_id " +
            "JOIN usuario u ON u.id=apm.gerente_regional_id " +
            "where cm.inactivo =0 " +
            "and apm.inactivo =0 " +
            "and d.inactivo =0 " +
            "and u.inactivo =0 " +
            "and u.rol = 'GERENTE_REGIONAL' " +
            "and (cme.estado = 'ALTA_APROBADA' OR cme.estado = 'ACTIVACION_APROBADA' OR cme.estado = 'BAJA_RECHAZADA' OR cme.estado IS NULL " +
            "OR (cme.estado = 'BAJA_APROBADA' AND (cme.fecha_confirmacion BETWEEN  (SELECT c.inicio from ciclo c WHERE c.id = :cicloId) AND (SELECT c.fin from ciclo c WHERE c.id = :cicloId)))) " +
            "and (:gerenteId is null or CAST(u.id as varchar) in (SELECT value FROM STRING_SPLIT(convert(varchar(max), :gerenteId), ','))) "
            , nativeQuery = true)
    Long getTotalMedicosCartera(String gerenteId, Long cicloId);

    @Query(value = "SELECT COALESCE(SUM(dv.frecuencia), 0) AS totalRequeridas, a2.gerente_regional_id  as gerenteId " +
            "FROM cartera_medica cm " +
            "JOIN datos_visita dv ON (cm.datos_visita_id=dv.id) " +
            "JOIN doctor d ON d.id =cm.doctor_id " +
            "JOIN apm a2 ON (a2.id = cm.apm_id) " +
            "JOIN cartera_medica_estado cme ON cme.id = cartera_medica_estado_id " +
            "JOIN usuario u on u.id =a2.gerente_regional_id " +
            "WHERE cm.inactivo=0 " +
            "AND a2.inactivo =0 " +
            "AND d.inactivo =0 " +
            "and u.inactivo = 0 " +
            "and u.rol = 'GERENTE_REGIONAL' " +
            "and (cme.estado = 'ALTA_APROBADA' OR cme.estado = 'ACTIVACION_APROBADA' OR cme.estado = 'BAJA_RECHAZADA' " +
            "OR (cme.estado = 'BAJA_APROBADA' AND (cme.fecha_confirmacion BETWEEN  (SELECT c.inicio from ciclo c WHERE c.id = :cicloId) AND (SELECT c.fin from ciclo c WHERE c.id = :cicloId)))) " +
            "AND (:gerenteId is null or a2.gerente_regional_id =:gerenteId) " +
            "AND (:apmId is null OR a2.id = :apmId) GROUP BY a2.gerente_regional_id",
            nativeQuery = true)
    List<VisitasPorGerenteProjection> getTotalVisitasByGerenteByApm(Long gerenteId, Long apmId, Long cicloId);
    
    @Query(value = "SELECT COUNT(DISTINCT(a.id)) AS totalApms " +
            "FROM apm a " +
            "JOIN usuario u ON u.id = a.gerente_regional_id " +
            "WHERE a.inactivo =0 " +
            "AND u.inactivo =0 " +
            "AND u.rol = 'GERENTE_REGIONAL' " +
            "AND (:gerenteId IS NULL OR a.gerente_regional_id = :gerenteId)", nativeQuery = true)
    Integer getTotalApmByGerente(Long gerenteId);

    /**
     * Find CarteraMedica list by doctorId
     * @param doctorId
     * @return List of CarteraMedica
     */
    List<CarteraMedica> findCarteraByDoctor_id(Long doctorId);


    /**
     * Find CarteraMedica list by doctorId
     * @param doctorId
     * @return List of CarteraMedica
     */
    List<CarteraMedica> findCarteraByDoctor_idIn(List<Long> doctorId);

    @Query(value = "WITH doctores_especialidad AS (  " +
            "SELECT  " +
            " u.id as gerenteId,  " +
            " u.nombre nombre,  " +
            " u.apellido apellidos,  " +
            " SUM(datos_visita.frecuencia) contactos_target,  " +
            " especialidad.id id_especialidad,  " +
            " especialidad.nombre nombre_especialidad,  " +
            " COUNT(cartera_medica.doctor_id) medicos  " +
            "FROM  " +
            " usuario u  " +
            "LEFT JOIN apm ON  " +
            " apm.gerente_regional_id = u.id  " +
            "LEFT JOIN cartera_medica ON  " +
            " cartera_medica.apm_id = apm.id  " +
            "LEFT JOIN datos_visita ON  " +
            " datos_visita.id = cartera_medica.datos_visita_id  " +
            "LEFT JOIN doctor ON  " +
            " doctor.id = cartera_medica.doctor_id  " +
            "LEFT JOIN especialidad ON  " +
            " especialidad.id = doctor.especialidad_id  " +
            " AND apm.inactivo = 0  " +
            " AND cartera_medica.inactivo = 0  " +
            " AND doctor.inactivo = 0  " +
            "LEFT JOIN cartera_medica_estado cme on  " +
            " cartera_medica.cartera_medica_estado_id = cme.id  " +
            "WHERE  " +
            " (:gerenteId is null  " +
            "  or u.id = :gerenteId )  " +
            " AND u.rol = 'GERENTE_REGIONAL'  " +
            " AND u.inactivo = 0  " +
            " AND (cme.estado = 'ALTA_APROBADA'  " +
            "  OR cme.estado = 'ACTIVACION_APROBADA'  " +
            "  OR cme.estado = 'BAJA_RECHAZADA'  " +
            "  OR cme.estado IS NULL  " +
            "  OR (cme.estado = 'BAJA_APROBADA'  " +
            "   AND (cme.fecha_confirmacion BETWEEN (  " +
            "   SELECT  " +
            "    c.inicio  " +
            "   from  " +
            "    ciclo c  " +
            "   WHERE  " +
            "    c.id = :cicloId) AND (  " +
            "   SELECT  " +
            "    c.fin  " +
            "   from  " +
            "    ciclo c  " +
            "   WHERE  " +
            "    c.id = :cicloId))))  " +
            " AND (:nombreCompleto IS NULL  " +
            "  OR :nombreCompleto = ''  " +
            "  OR CONCAT(u.nombre, ' ', u.apellido) COLLATE Latin1_general_CI_AI like %:nombreCompleto%)  " +
            " AND (doctor.deleted = 0 OR doctor.deleted is null) " +
            "GROUP BY  " +
            " u.id,  " +
            " u.nombre,  " +
            " u.apellido ,  " +
            " especialidad.id,  " +
            " especialidad.nombre )  " +
            "SELECT  " +
            " gerenteId as gerenteId,  " +
            " nombre as nombre,  " +
            " apellidos,  " +
            " SUM (medicos) OVER ( PARTITION BY gerenteId ) as totalMedicos,  " +
            " count (gerenteId) OVER ( PARTITION BY gerenteId ) as totalApms,  " +
            " contactos_target as contactosTarget,  " +
            " id_especialidad as especialidadId,  " +
            " nombre_especialidad as especialidad,  " +
            " medicos as totalMedicosEspecialidad  " +
            "FROM  " +
            " doctores_especialidad", nativeQuery = true)
    List<KpiMedicosApmEspecialidadProjection> getMedicosEspecialidadByGerenteIdAdmin(Long gerenteId, String nombreCompleto, Long cicloId);




    @Query(value = "WITH doctores_categoria AS (  " +
            "SELECT  " +
            " u.id as gerenteId,  " +
            " u.nombre nombre,  " +
            " u.apellido apellidos,  " +
            " SUM(datos_visita.frecuencia) contactos_target,  " +
            " categoria_doctor.id id_categoria_doctor,  " +
            " categoria_doctor.nombre nombre_categoria_doctor,  " +
            " COUNT(cartera_medica.doctor_id) medicos  " +
            "FROM  " +
            " usuario u  " +
            "LEFT JOIN apm ON  " +
            " apm.gerente_regional_id = u.id  " +
            "LEFT JOIN cartera_medica ON  " +
            " cartera_medica.apm_id = apm.id  " +
            "LEFT JOIN datos_visita ON  " +
            " datos_visita.id = cartera_medica.datos_visita_id  " +
            "LEFT JOIN doctor ON  " +
            " doctor.id = cartera_medica.doctor_id  " +
            "LEFT JOIN categoria_doctor ON  " +
            " categoria_doctor.id = doctor.categoria_id  " +
            "LEFT JOIN cartera_medica_estado cme on  " +
            " cartera_medica.cartera_medica_estado_id = cme.id  " +
            "WHERE  " +
            " u.rol = 'GERENTE_REGIONAL'  " +
            " AND apm.inactivo = 0  " +
            " AND cartera_medica.inactivo = 0  " +
            " AND doctor.inactivo = 0  " +
            " AND u.inactivo = 0  " +
            " AND (cme.estado = 'ALTA_APROBADA'  " +
            "  OR cme.estado = 'ACTIVACION_APROBADA'  " +
            "  OR cme.estado = 'BAJA_RECHAZADA'  " +
            "  OR cme.estado IS NULL  " +
            "  OR (cme.estado = 'BAJA_APROBADA'  " +
            "   AND (cme.fecha_confirmacion BETWEEN (  " +
            "   SELECT  " +
            "    c.inicio  " +
            "   from  " +
            "    ciclo c  " +
            "   WHERE  " +
            "    c.id = :cicloId) AND (  " +
            "   SELECT  " +
            "    c.fin  " +
            "   from  " +
            "    ciclo c  " +
            "   WHERE  " +
            "    c.id = :cicloId))))  " +
            " AND (:nombreCompleto IS NULL  " +
            "  OR :nombreCompleto = ''  " +
            "  OR CONCAT(u.nombre, ' ', u.apellido) COLLATE Latin1_general_CI_AI like %:nombreCompleto%)  " +
            " AND (doctor.deleted = 0 OR doctor.deleted is null) " +
            "GROUP BY  " +
            " u.id,  " +
            " u.nombre,  " +
            " u.apellido ,  " +
            " categoria_doctor.id,  " +
            " categoria_doctor.nombre )  " +
            "SELECT  " +
            " gerenteId as gerenteId,  " +
            " nombre as nombre,  " +
            " apellidos,  " +
            " SUM (medicos) OVER ( PARTITION BY gerenteId ) as totalMedicos,  " +
            " count (gerenteId) OVER ( PARTITION BY gerenteId ) as totalApms,  " +
            " contactos_target as contactosTarget,  " +
            " id_categoria_doctor as categoriaDoctorId,  " +
            " nombre_categoria_doctor as categoriaDoctor,  " +
            " medicos as totalMedicosCategoriaDoctor  " +
            "FROM  " +
            " doctores_categoria", nativeQuery = true)
    List<KpiMedicosApmCategoriaDoctorProjection> getMedicosCategoriaDoctorByGerenteIdAdmin(String nombreCompleto, Long cicloId);


    @Query(value = "WITH doctores_categoria AS (  " +
            "SELECT  " +
            " apm.id as apmId,  " +
            " CONCAT(apm.primerNombre, ' ', apm.segundoNombre) nombre,  " +
            " CONCAT(apm.primerApellido, ' ', apm.segundoApellido) apellidos,  " +
            " SUM(datos_visita.frecuencia) contactos_target,  " +
            " CASE WHEN categoria_doctor.id IS NULL THEN 0 ELSE categoria_doctor.id END  id_categoria_doctor,  " +
            " CASE WHEN categoria_doctor.nombre IS NULL THEN 'Sin categoría' ELSE categoria_doctor.nombre END  nombre_categoria_doctor,  " +
            " COUNT(cartera_medica.doctor_id) medicos  " +
            "FROM  " +
            " usuario u  " +
            "LEFT JOIN apm ON apm.gerente_regional_id = u.id  " +
            "LEFT JOIN cartera_medica ON cartera_medica.apm_id = apm.id  " +
            "LEFT JOIN datos_visita ON datos_visita.id = cartera_medica.datos_visita_id  " +
            "LEFT JOIN doctor ON doctor.id = cartera_medica.doctor_id  " +
            "LEFT JOIN categoria_doctor ON categoria_doctor.id = doctor.categoria_id  " +
            "LEFT JOIN cartera_medica_estado cme on  " +
            " cartera_medica.cartera_medica_estado_id = cme.id  " +
            "WHERE  " +
            " (:gerenteId is null  " +
            "  or u.id = :gerenteId )  " +
            " AND apm.inactivo = 0  " +
            " AND cartera_medica.inactivo = 0  " +
            " AND doctor.inactivo = 0  " +
            " AND u.rol = 'GERENTE_REGIONAL'  " +
            " AND u.inactivo = 0  " +
            " AND (cme.estado = 'ALTA_APROBADA'  " +
            "  OR cme.estado = 'ACTIVACION_APROBADA'  " +
            "  OR cme.estado = 'BAJA_RECHAZADA'  " +
            "  OR cme.estado IS NULL  " +
            "  OR (cme.estado = 'BAJA_APROBADA'  " +
            "   AND (cme.fecha_confirmacion BETWEEN (  " +
            "   SELECT  " +
            "    c.inicio  " +
            "   from  " +
            "    ciclo c  " +
            "   WHERE  " +
            "    c.id = :cicloId) AND (  " +
            "   SELECT  " +
            "    c.fin  " +
            "   from  " +
            "    ciclo c  " +
            "   WHERE  " +
            "    c.id = :cicloId))))  " +
            " AND (:nombreCompleto IS NULL  " +
            "  OR :nombreCompleto = ''  " +
            "  OR CONCAT(u.nombre, ' ', u.apellido) COLLATE Latin1_general_CI_AI like %:nombreCompleto%)  " +
            " AND (doctor.deleted = 0 OR doctor.deleted is null) " +
            "GROUP BY  " +
            " apm.id,  " +
            " apm.primerNombre," +
            " apm.segundoNombre," +
            " apm.primerApellido," +
            " apm.segundoApellido," +
            " categoria_doctor.id,  " +
            " categoria_doctor.nombre )  " +
            "SELECT  " +
            " apmId as apmId,  " +
            " nombre as nombre,  " +
            " apellidos,  " +
            " SUM (medicos) OVER ( PARTITION BY apmId ) as totalMedicos,  " +
            " count (apmId) OVER ( PARTITION BY apmId ) as totalApms,  " +
            " contactos_target as contactosTarget,  " +
            " id_categoria_doctor as categoriaDoctorId,  " +
            " nombre_categoria_doctor as categoriaDoctor,  " +
            " medicos as totalMedicosCategoriaDoctor  " +
            "FROM  " +
            " doctores_categoria", nativeQuery = true)
    List<KpiMedicosApmCategoriaDoctorProjection> getMedicosCategoriaDoctorByGerenteId(Long gerenteId, String nombreCompleto, Long cicloId);



    @Query(value = "SELECT COALESCE(SUM(dv.frecuencia), 0) AS totalRequeridas " +
            "FROM cartera_medica cm " +
            "JOIN datos_visita dv ON (cm.datos_visita_id=dv.id) " +
            "JOIN doctor d ON d.id =cm.doctor_id " +
            "JOIN apm a2 ON (a2.id = cm.apm_id) " +
            "JOIN cartera_medica_estado cme ON cme.id = cartera_medica_estado_id " +
            "JOIN usuario u on u.id =a2.gerente_regional_id " +
            "WHERE cm.inactivo=0 " +
            "AND a2.inactivo =0 " +
            "AND d.inactivo =0 " +
            "and u.inactivo = 0 " +
            "and u.rol = 'GERENTE_REGIONAL' " +
            "and (cme.estado = 'ALTA_APROBADA' OR cme.estado = 'ACTIVACION_APROBADA' OR cme.estado = 'BAJA_RECHAZADA' " +
            "OR (cme.estado = 'BAJA_APROBADA')) " +
            "AND (:gerenteId is null or a2.gerente_regional_id =:gerenteId) " ,
            nativeQuery = true)
    Double getContactosObjetivosByGerenteByApm(Long gerenteId);


    @Query(value = "select di.id, CONCAT(a.primerNombre,' ',a.primerApellido) as nombreApm from cartera_medica cm " +
            "INNER JOIN datos_visita dv ON cm.datos_visita_id = dv.id  " +
            "INNER JOIN doctor d ON cm.doctor_id = d.id " +
            "INNER JOIN direcciones_medicos dm ON dm.id_medico = d.id and dm.id_direccion = dv.id_direccion " +
            "INNER JOIN direccion di ON di.id = dm.id_direccion " +
            "INNER JOIN apm a ON a.id = cm.apm_id " +
            "WHERE  " +
            "d.id = :idDoctor ",
            nativeQuery = true)
    List<DireccionMedicosProjection> findCarteraMedicaDoctorDireccion(Long idDoctor);


    @Query(value = "select i.id, CONCAT(a.primerNombre,' ',a.primerApellido) as nombreApm from cartera_medica cm " +
            "INNER JOIN datos_visita dv ON cm.datos_visita_id = dv.id  " +
            "INNER JOIN doctor d ON cm.doctor_id = d.id " +
            "INNER JOIN instituciones_medicos im ON im.id_medico = d.id and im.id_institucion = dv.institucion_id  " +
            "INNER JOIN institucion i  ON i.id = im.id_institucion  " +
            "INNER JOIN apm a ON a.id = cm.apm_id " +
            "WHERE  " +
            "d.id = :idDoctor ",
            nativeQuery = true)
    List<DireccionMedicosProjection> findCarteraMedicaDoctorInstitucion(Long idDoctor);
    List<CarteraMedica> findAllByApmIdAndDoctorIdAndInactivoIsFalse(Long apmId, Long doctorId);

    @Query(value = "SELECT COUNT(cm.doctor_id) cantidad " +
            "FROM cartera_medica cm " +
            "JOIN apm a ON (a.id =cm.apm_id) " +
            "JOIN doctor d ON d.id=cm.doctor_id  " +
            "JOIN cartera_medica_estado cme ON cme.id= cm.cartera_medica_estado_id " +
            "JOIN usuario u ON u.id = a.gerente_regional_id " +
            "WHERE d.inactivo = 0 " +
            "AND cm.inactivo = 0 " +
            "AND a.inactivo = 0 " +
            "AND u.inactivo = 0 " +
            "AND u.rol = 'GERENTE_REGIONAL' " +
            "AND (cme.estado = 'ALTA_APROBADA' OR cme.estado = 'ACTIVACION_APROBADA' OR cme.estado = 'BAJA_RECHAZADA' OR cme.estado IS NULL " +
            "  OR (cme.estado = 'BAJA_APROBADA' AND (cme.fecha_confirmacion BETWEEN :inicio AND :fin ))) " +
            "AND (:apmId IS NULL OR a.id = :apmId) " +
            "AND (:gerenteId IS NULL OR a.gerente_regional_id = :gerenteId) " +
            "AND (d.deleted = 0 OR d.deleted IS NULL) " +
            "AND d.loyalty_id = :loyaltyId", nativeQuery = true)
    Long totalMedicosLoyalty(Long gerenteId, Long apmId, Long loyaltyId, Date inicio, Date fin);

    @Query(value = "SELECT COUNT(cm.doctor_id) cantidad " +
            "FROM cartera_medica cm " +
            "JOIN doctor d ON d.id=cm.doctor_id " +
            "JOIN apm a ON (a.id =cm.apm_id) " +
            "LEFT JOIN cartera_medica_estado cme ON cme.id= cm.cartera_medica_estado_id " +
            "JOIN usuario u ON u.id = a.gerente_regional_id " +
            "WHERE d.inactivo = 0 " +
            "AND cm.inactivo = 0 " +
            "AND u.inactivo = 0 " +
            "AND a.inactivo = 0 " +
            "AND u.rol = 'GERENTE_REGIONAL' " +
            "AND (cme.estado = 'ALTA_APROBADA' OR cme.estado = 'ACTIVACION_APROBADA' OR cme.estado = 'BAJA_RECHAZADA' " +
            "  OR cme.estado IS NULL " +
            "  OR (cme.estado = 'BAJA_APROBADA' AND (cme.fecha_confirmacion BETWEEN :inicio AND :fin ))) " +
            "AND (:apmId IS NULL OR a.id = :apmId) " +
            "AND (:gerenteId IS NULL OR a.gerente_regional_id = :gerenteId) " +
            "AND d.categoria_id = :categoriaId", nativeQuery = true)
    Long totalMedicosCategoria(Long gerenteId, Long apmId, Long categoriaId, Date inicio, Date fin);

    @Transactional
    @Modifying
    @Query(value = "UPDATE cartera_medica cm SET cm.inactivo = TRUE, modified= :fecha WHERE cm.apm.id = :apmId and cm.doctor.id = :doctorId")
    void updateInactiveByApmAndDoctor(Long apmId, Long doctorId, Date fecha);

    @Query(value = "EXEC Reporte_Cartera_Medica @ciclo=:ciclo", nativeQuery = true)
    List<ReporteCarteraMedicaExcelProjection> getReporteCarteraMedicaExcel(@Param("ciclo") Long ciclo);

}
