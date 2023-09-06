package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.Agenda;
import ar.elea.apx.backend.entity.PerfilEnum;
import ar.elea.apx.backend.entity.TurnoEnum;
import ar.elea.apx.backend.projection.*;
import ar.elea.apx.backend.view.AgendaWithDatosVisitaView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author Felipe Jimenez
 */
@Repository
@Transactional
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    /**
     * Find all APM's agenda records
     * @param apmId
     * @return list of Agenda
     */
    @EntityGraph(attributePaths = {"apm", "doctor", "agendaProductos"})
    List<Agenda> findAllByApm_IdAndInactivoIsFalse(Long apmId);

    /**
     * Find all APM's agenda records by Doctor Id and AgendaTipo
     * @param apmId
     * @return list of Agenda
     */
    List<Agenda> findAllByApm_IdAndDoctor_IdAndAgendaTipo_NombreAndInactivoIsFalse(Long apmId, Long doctorId, String agendaTipoNombre);


    /**
     * Find all agendas by agendaTipo
     * @param agendaTipo AgendaTipoEnum value (VISITA, PARTE_DIARIO, PREVISTO)
     * @return list of Agenda
     */
    List<Agenda> findAllByAgendaTipo_Nombre(String agendaTipo);

    /**
     * Find all agendas by APM id and agendaTipo
     * @param apmId
     * @param agendaTipo AgendaTipoEnum value (VISITA, PARTE_DIARIO, PREVISTO)
     * @return list of Agenda
     */
    List<Agenda> findAllByApm_IdAndAgendaTipo_NombreAndInactivoIsFalse(Long apmId, String agendaTipo);

    /**
     * Find all agendas by Doctor id and agendaTipo
     * @param doctorId
     * @param agendaTipo AgendaTipoEnum value (VISITA, PARTE_DIARIO, PREVISTO)
     * @return list of Agenda
     */
    List<Agenda> findAllByDoctor_IdAndAgendaTipo_NombreAndInactivoIsFalse(Long doctorId, String agendaTipo);

    /**
     * Find all agenda records related to a doctor
     * @param doctorId
     * @return list of Agenda
     */
    List<Agenda> findAllByDoctor_Id(Long doctorId);

    /**
     * Find all APM's agenda records related to a Doctor
     * @param apmId
     * @param doctorId
     * @return list of Agenda
     */
    List<Agenda> findAllByApm_IdAndDoctor_Id(Long apmId, Long doctorId);



    @Query(value="with ciclo_actual as (select c.inicio, c.fin from ciclo c where c.inicio < GETDATE() and c.fin > GETDATE()) " +
            "select a.doctor_id as doctorId, count(a.id) as visitas from agenda a, ciclo_actual c " +
            "where a.apm_id = :apmId and a.agenda_tipo_id = (select at.id from agenda_tipo at where at.nombre = upper('VISITA')) " +
            "and a.inicio > c.inicio     and a.inicio < c.fin     and a.visita_exitosa is null or a.visita_exitosa = 'true'  " +
            "group by (a.doctor_id)", nativeQuery = true )
    Collection<VisitasPorDoctorProjection> getCountVisitasPorDoctorPorApmCiclo(@Param("apmId") Long apmId);


    @Query("FROM Agenda A WHERE A.doctor.id = :doctorId and A.agendaTipo.nombre = 'VISITA'")
    List<Agenda> getVisitasDoctor(Long doctorId, Pageable pageable);

    List<Agenda> findAllByIdIn(List<Long> ids);

    @Modifying
    @Query(value = "UPDATE Agenda a SET a.inactivo = TRUE, a.lastUpdated = :lastUpdated WHERE a.id in :agendasId")
    void markInactiveByIdIn(List<Long> agendasId, Date lastUpdated);

    @Modifying
    @Query(value = "UPDATE Agenda a SET a.inactivo = TRUE, a.lastUpdated = :lastUpdated WHERE a.apm.id = :apmId and a.doctor.id = :doctorId")
    void markInactiveByApmIdAndDoctorId(Long apmId, Long doctorId, Date lastUpdated);

    /**
     * Find all agenda records made by other APM than this one and to Doctors in this APM's cartera and AgendaTipo
     * @param excludedApmId Apm excluded
     * @param agendaTipo AgendaTipo (one of Visita, Previsto, Parte diario)
     * @return list of Agenda
     */
    @Query(value="select a from Agenda a " +
            "    inner join agenda_tipo t on a.agendaTipo.id = t.id " +
            "    where a.doctor in (select cm.doctor from cartera_medica cm where cm.apm.id = :excludedApmId and cm.approved = true) " +
            "    and a.apm <> :excludedApmId" +
            "    and t.nombre = :agendaTipo " +
            "    and a.inactivo = false")
    List<Agenda> getVisitasOtrosApmDoctoresEnCarteraMedica(Long excludedApmId, String agendaTipo);

    @Query(value =
            "select " +
            "       u.apellido as gerenteRegionalApellido, " +
            "       u.nombre as gerenteRegionalNombre, " +
            "       apm.primerApellido as apmApellido, " +
            "       apm.primerNombre as apmNombre, " +
            "       d.primerApellido as doctorApellido, " +
            "       d.primerNombre as doctorNombre, " +
            "       dv.frecuencia, " +
            "       a.inicio as fechaInicio, " +
            "       a.fin as fechaFin, " +
            "       a.visita_exitosa as visitaExitosa, " +
            "       t.id as tipoAgendaId, " +
            "       t.nombre as tipoAgendaNombre, " +
            "       m.nombre as motivo, " +
            "       a.visita_tipo as tipo, " +
            "       a.turno, " +
            "       a.lastUpdated as conexion, " +
            "       i.nombre as institucion, " +
            "       a.porcentaje_turno_manana as porcentajeTurnoManana, " +
            "       a.porcentaje_turno_tarde as porcentajeTurnoTarde " +
            "from agenda a " +
            "left join doctor d on a.doctor_id = d.id " +
            "left join apm on a.apm_id = apm.id " +
            "left join usuario u on apm.gerente_regional_id = u.id " +
            "left join cartera_medica cm on a.apm_id = cm.apm_id and a.doctor_id = cm.doctor_id " +
            "left join datos_visita dv on cm.datos_visita_id = dv.id " +
            "left join agenda_tipo t on a.agenda_tipo_id = t.id " +
            "left join motivo m on a.agenda_motivo_fallida_id = m.id " +
            "left join institucion i on a.institucion_id = i.id " +
            "where inicio >= Convert(datetime, :from) and inicio <= Convert(datetime, :to) " +
            "order by inicio", nativeQuery = true)
    List<ActividadProjection> getActividades(Date from, Date to);

    @Query(value =
            "select " +
                    "       u.apellido as gerenteRegionalApellido, " +
                    "       u.nombre as gerenteRegionalNombre, " +
                    "       apm.primerApellido as apmApellido, " +
                    "       apm.primerNombre as apmNombre, " +
                    "       d.primerApellido as doctorApellido, " +
                    "       d.primerNombre as doctorNombre, " +
                    "       dv.frecuencia, " +
                    "       a.inicio as fechaInicio, " +
                    "       a.fin as fechaFin, " +
                    "       a.visita_exitosa as visitaExitosa, " +
                    "       t.id as tipoAgendaId, " +
                    "       t.nombre as tipoAgendaNombre, " +
                    "       m.nombre as motivo, " +
                    "       a.visita_tipo as tipo, " +
                    "       a.turno, " +
                    "       a.lastUpdated as conexion, " +
                    "       i.nombre as institucion, " +
                    "       a.porcentaje_turno_manana as porcentajeTurnoManana, " +
                    "       a.porcentaje_turno_tarde as porcentajeTurnoTarde " +
                    "from agenda a " +
                    "left join doctor d on a.doctor_id = d.id " +
                    "left join apm on a.apm_id = apm.id " +
                    "left join usuario u on apm.gerente_regional_id = u.id " +
                    "left join cartera_medica cm on a.apm_id = cm.apm_id and a.doctor_id = cm.doctor_id " +
                    "left join datos_visita dv on cm.datos_visita_id = dv.id " +
                    "left join agenda_tipo t on a.agenda_tipo_id = t.id " +
                    "left join motivo m on a.agenda_motivo_fallida_id = m.id " +
                    "left join institucion i on a.institucion_id = i.id " +
                    "where " +
                    "inicio >= Convert(datetime, :from) and inicio <= Convert(datetime, :to) " +
                    "and a.apm_id in :apmsId " +
                    "order by inicio", nativeQuery = true)
    List<ActividadProjection> getActividades(Date from, Date to, List<Long> apmsId);

    @Modifying
    @Query(value = "update agenda set doctor_id = :newDoctorId where doctor_id = :oldDoctorId ", nativeQuery = true)
    void updateDoctorId(Long oldDoctorId, Long newDoctorId);


    @Query(value =
            "select a.* " +
            "from agenda a " +
            "left join apm on a.apm_id = apm.id " +
            "where requires_approval = 1 and approved = 0 and a.agenda_tipo_id = 2 and a.approved_by is null " +
            "and (:gerenteId is null or apm.gerente_regional_id = :gerenteId)"
            , nativeQuery = true)
    List<Agenda> getAgendasPendingApproval(Long gerenteId);


    @Query(value =
            "select new ar.elea.apx.backend.view.AgendaWithDatosVisitaView(a,  dv) " +
            "from Agenda a " +
            "         left join cartera_medica cm on a.apm.id = cm.apm.id and a.doctor.id = cm.doctor.id " +
            "         left join DatosVisita dv on cm.datosVisita = dv " +
            "where a.inactivo = false " +
            "  and a.apm.id = :apmId" +
            "  and (:from is null or a.lastUpdated >= :from)")
    List<AgendaWithDatosVisitaView> findAllActiveAgendasForApm(Long apmId, Date from);







    @Query(value = "select distinct " +
            "       a.id                                              as id,  " +
            "       apm.id                                            as apmId,  " +
            "       concat(apm.primerNombre, ' ', apm.primerApellido) as apmNombre,  " +
            "       a.inicio                                          as desde,  " +
            "       a.fin                                             as hasta,  " +
            "       a.acompanante                                     as acompanante,  " +
            "       a.direccion                                       as direccion,  " +
            "       a.observaciones                                   as observaciones,  " +
            "       a.turno                                           as turno,  " +
            "       agenda_tipo.id                                    as agendaTipoId,  " +
            "       agenda_tipo.nombre                                as agendaTipoNombre,  " +
            "       a.visita_tipo                                     as visitaTipo,  " +
            "       a.perfil                                          as perfil,  " +
            "       a.inactivo                                        as inactivo,  " +
            "       a.requires_approval                               as requiereAprobacion,  " +
            "       a.approved                                        as aprobado,  " +
            "       a.porcentaje_turno_manana                         as porcentajeTurnoManana,  " +
            "       a.porcentaje_turno_tarde                          as porcentajeTurnoTarde,  " +
            "       a.visita_exitosa                                  as visitaExitosa,  " +
            "       a.plataforma                                      as plataforma," +
            "       motivo.id                                         as motivoId,  " +
            "       motivo.nombre                                     as motivoNombre,  " +
            "       motivo.fecha_futuro                               as motivoFechaFuturo,  " +
            "       motivo.inactivo                                   as motivoInactivo,  " +
            "       motivo_tipo.id                                    as motivoTipoId,  " +
            "       a.lugar                                           as lugar, " +
            "  " +
            "       d.id                                              as doctorId,  " +
            "       d.primerNombre                                    as doctorNombre,  " +
            "       d.primerApellido                                  as doctorApellido,  " +
            "       d.fechaNacimiento                                 as doctorFechaNacimiento,  " +
            "       d.email                                           as doctorEmail,  " +
            "       d.doctor_app                                      as doctorDoctorApp,  " +
            "  " +
            "       dirVisita.id                                      as dirVisitaId,  " +
            "       cpVisita.id                                       as cpDirVisitaId,  " +
            "       cpVisita.cpa                                      as cpDirVisitaCPA,  " +
            "       prvVisita.id                                      as prDirVisitaId,  " +
            "       prvVisita.nombre                                  as prDirVisitaNombre,  " +
            "       paisVisita.id                                     as paisDirVisitaId,  " +
            "       paisVisita.nombre                                 as paisDirVisitaNombre,  " +
            "       lcldVisita.id                                     as lcldDirVisitaId,  " +
            "       lcldVisita.nombre                                 as lcldDirVisitaNombre,  " +
            "       ciudVisita.id                                     as ciudDirVisitaId,  " +
            "       ciudVisita.nombre                                 as ciudDirVisitaNombre,  " +
            "       calleVisita.id                                    as calleDirVisitaId,  " +
            "       calleVisita.nombre                                as calleDirVisitaNombre,  " +
            "       dirVisita.numero                                  as numeroDirVisita,  " +
            "       dirVisita.detalle                                 as detalleDirVisita,  " +
            "  " +
            "       institucion.id                                    as institucionId,  " +
            "       institucion.nombre                                as institucionNombre,  " +
            "       institucion.razon_social                          as institucionRazonSocial,  " +
            "  " +
            "       dirInstitucion.id                                 as dirInstitucionId,  " +
            "       cpInstitucion.id                                  as cpDirInstitucionId,  " +
            "       cpInstitucion.cpa                                 as cpDirInstitucionCPA,  " +
            "       prvInstitucion.id                                 as prDirInstitucionId,  " +
            "       prvInstitucion.nombre                             as prDirInstitucionNombre,  " +
            "       paisInstitucion.id                                as paisDirInstitucionId,  " +
            "       paisInstitucion.nombre                            as paisDirInstitucionNombre,  " +
            "       lcldInstitucion.id                                as lcldDirInstitucionId,  " +
            "       lcldInstitucion.nombre                            as lcldDirInstitucionNombre,  " +
            "       ciudInstitucion.id                                as ciudDirInstitucionId,  " +
            "       ciudInstitucion.nombre                            as ciudDirInstitucionNombre,  " +
            "       calleInstitucion.id                               as calleDirInstitucionId,  " +
            "       calleInstitucion.nombre                           as calleDirInstitucionNombre,  " +
            "       dirInstitucion.numero                             as numeroDirInstitucion,  " +
            "       dirInstitucion.detalle                            as detalleDirInstitucion,  " +
            "       (select count(*) from agenda_producto ap where ap.id_agenda = a.id) as productos," +
            "       (select count(*) from agenda_muestra am where am.id_agenda = a.id) as muestras " +
            "from agenda a  " +
            "         left join agenda_tipo on a.agenda_tipo_id = agenda_tipo.id  " +
            "         left join motivo on a.agenda_motivo_fallida_id = motivo.id  " +
            "         left join motivo_tipo on motivo.id_tipo = motivo_tipo.id  " +
            "         left join apm on a.apm_id = apm.id  " +
            "         left join doctor d on a.doctor_id = d.id  " +
            "         left join direccion dirVisita on a.direccion_id = dirVisita.id  " +
            "         left join codigo_postal cpVisita on dirVisita.id_codigo_postal = cpVisita.id  " +
            "         left join provincia prvVisita on cpVisita.id_provincia = prvVisita.id  " +
            "         left join pais paisVisita on prvVisita.id_pais = paisVisita.id  " +
            "         left join localidad lcldVisita on cpVisita.id_localidad = lcldVisita.id  " +
            "         left join ciudad ciudVisita on cpVisita.id_ciudad = ciudVisita.id  " +
            "         left join calle calleVisita on cpVisita.id_calle = calleVisita.id  " +
            "         left join institucion on a.institucion_id = institucion.id  " +
            "         left join direccion dirInstitucion on institucion.id_direccion = dirInstitucion.id  " +
            "         left join codigo_postal cpInstitucion on dirInstitucion.id_codigo_postal = cpInstitucion.id  " +
            "         left join provincia prvInstitucion on cpInstitucion.id_provincia = prvInstitucion.id  " +
            "         left join pais paisInstitucion on prvInstitucion.id_pais = paisInstitucion.id " +
            "         left join localidad lcldInstitucion on cpInstitucion.id_localidad = lcldInstitucion.id  " +
            "         left join ciudad ciudInstitucion on cpInstitucion.id_ciudad = ciudInstitucion.id  " +
            "         left join calle calleInstitucion on cpInstitucion.id_calle = calleInstitucion.id  " +
            "where a.apm_id = :apmId and (:from is null or a.lastUpdated >= :from) and a.inactivo = 0", nativeQuery = true)
    List<AgendaApmProjection> findAllActiveAgendasForApmWithProjection(Long apmId, Date from);

    @Query(value="select a.* " +
            "from agenda a " +
            "inner join doctor d on a.doctor_id = d.id " +
            "inner join cartera_medica cm on cm.apm_id = a.apm_id and cm.doctor_id = a.doctor_id " +
            "inner join datos_visita dv on cm.datos_visita_id = dv.id " +
            "inner join apm on apm.id = a.apm_id " +
            "inner join usuario u on u.id = apm.gerente_regional_id " +
            "left join direccion dir on dv.id_direccion = dir.id " +
            "left join codigo_postal cp on dir.id_codigo_postal = cp.id " +
            "where a.agenda_tipo_id = (select at.id from agenda_tipo at where at.nombre = upper('VISITA')) " +
            "and a.inicio > (select c.inicio from ciclo c where c.id = :cicloId) " +
            "and a.inicio < (select c.fin from ciclo c where c.id = :cicloId) " +
            "and (:gerenteRegionalId is null or u.id = :gerenteRegionalId) " +
            "and (:apmId is null or a.apm_id = :apmId) " +
            "and (:doctorId is null or a.doctor_id = :doctorId) " +
            "and (:provinciaId is null or cp.id_provincia = :provinciaId) "
            , nativeQuery = true )
    List<Agenda> findVisitasByCriteria(
            @Param("gerenteRegionalId") Long gerenteRegionalId,
            @Param("apmId") Long apmId,
            @Param("doctorId") Long doctorId,
            @Param("provinciaId") Long provinciaId,
            @Param("cicloId") Long cicloId);

    @Query(value =
            "select a.id                                                                            as id, " +
            "       apm.legajo                                                                      as legajo, " +
            "       apm.primerNombre                                                         as apmNombre, " +
            "       apm.primerApellido                                                       as apmApellido, " +
            "       u.nombre                                                                 as gerenteNombre, " +
            "       u.apellido                                                               as gerenteApellido, " +
            "       motivo.nombre                                                            as motivo, " +
            "       cast(a.inicio as date)                                                          as fecha, " +
            "       a.turno                                                                         as turno, " +
            "       iif(a.turno like 'MANANA', a.porcentaje_turno_manana, a.porcentaje_turno_tarde) as porcentaje, " +
            "       a.lastUpdated                                                                   as conexion " +
            " " +
            "from agenda a " +
            "         left join apm on apm.id = a.apm_id " +
            "         left join motivo on a.agenda_motivo_fallida_id = motivo.id " +
            "         left join usuario u on u.id = apm.gerente_regional_id " +
            "where a.agenda_tipo_id = 1 " +
            "  and (:fechaInicio is null or a.inicio >= :fechaInicio) " +
            "  and (:fechaFin is null or a.inicio <= :fechaFin) " +
            "  and (:nombre is null or apm.primerNombre = concat(:nombre, '%')) " +
            "  and (:apellido is null or apm.primerApellido = concat(:apellido, '%')) " +
            "  and (:gerenteId is null or u.id = :gerenteId) " +
            " order by a.inicio, apm.id",
            nativeQuery = true)
    List<ExportParteDiarioProjection> getExportParteDiario(String nombre, String apellido, Date fechaInicio, Date fechaFin, Long gerenteId);


    @Query(value =
            "select * " +
            "from agenda a " +
            "left join doctor d on a.doctor_id = d.id " +
            "left join institucion i on a.institucion_id = i.id " +
            "left join agenda_tipo at on a.agenda_tipo_id = at.id " +
            "where apm_id = :idApm " +
            "and (:desde is null or a.inicio >= :desde) " +
            "and (:hasta is null or a.inicio <= :hasta) " +
            "and (:cicloInicio is null or a.inicio >= :cicloInicio) " +
            "and (:cicloFin is null or a.inicio <= :cicloFin) " +
            "and (:idTipoActividad is null or a.agenda_tipo_id = :idTipoActividad) " +
            "and (:tipoVisita is null or (a.visita_tipo like :tipoVisita and a.agenda_tipo_id = 0)) " +
            "and (:exitosa is null or (a.visita_exitosa = :exitosa and a.agenda_tipo_id = 0)) " +
            "and (:turno is null or a.turno like :turno) " +
            "and (:nombreMedico is null or d.primerNombre like concat('%', :nombreMedico, '%')) " +
            "and (:apellidoMedico is null or d.primerApellido like concat('%', :apellidoMedico, '%')) " +
            "and (:institucion is null or i.nombre like concat('%', :institucion, '%')) " +
            "and (:motivoParteDiario is null or (a.agenda_motivo_fallida_id = :motivoParteDiario and a.agenda_tipo_id = 1))" +
            "and a.inactivo = 0 "
            ,
            nativeQuery = true)
    Page<Agenda> listAgendasApm(Long idApm, Date desde, Date hasta, Date cicloInicio, Date cicloFin, Long idTipoActividad, String tipoVisita, Boolean exitosa, String turno, String nombreMedico, String apellidoMedico, String institucion, Long motivoParteDiario,Pageable pageable);

    /*
    Date desde, Date hasta, Date cicloInicio, Date cicloFin, Long idTipoActividad, String tipoVisita, Boolean exitosa, String turno, String nombreMedico, String apellidoMedico, String institucion, Long motivoParteDiario,
     */

    @Query(value=
            "SELECT b.id, b.nombre, SUM(porcentaje_turno_manana+porcentaje_turno_tarde) as cantidad, a.apm_id idApm," +
                    "TRIM(CONCAT(c.primerNombre, ' ', c.segundoNombre)) nombreApm, TRIM(CONCAT(c.primerApellido, ' ', c.segundoApellido)) apellidoApm," +
                    "c.gerente_regional_id idGerente, u.apellido  " +
                    "FROM agenda a " +
                    "JOIN motivo b ON(b.id=a.agenda_motivo_fallida_id) " +
                    "JOIN apm c ON(c.id=a.apm_id) " +
                    "JOIN usuario u ON u.id=c.gerente_regional_id " +
                    "WHERE a.inactivo = 0 " +
                    "AND c.inactivo = 0 " +
                    "AND u.inactivo = 0 " +
                    "AND u.rol = 'GERENTE_REGIONAL' " +
                    "AND (:gerenteId is null or c.gerente_regional_id = :gerenteId) " +
                    "AND a.inicio BETWEEN :inicioCiclo AND :finCiclo " +
                    "AND agenda_tipo_id =1 " +
                    "AND agenda_motivo_fallida_id IS NOT NULL " +
                    "AND (:apmId is null OR c.id=:apmId) " +
                    "GROUP BY b.id, b.nombre, a.apm_id, c.primerNombre, c.primerApellido, c.segundoNombre,c.segundoApellido, c.gerente_regional_id, u.apellido  ORDER BY u.apellido ",
    nativeQuery = true)
    List<KpiTiempoFueraProjection> getTiempoFueraByGerenteIdApmId(Long gerenteId, LocalDate inicioCiclo, LocalDate finCiclo, Long apmId);

    @Query(value = "SELECT a.doctor_id as doctorId, MAX(a.inicio) as fechaUltimaVisita " +
            "FROM agenda a, cartera_medica cm, cartera_medica_estado cme " +
            "WHERE a.agenda_tipo_id = (SELECT id FROM agenda_tipo at2  WHERE nombre = 'Visita') " +
            "AND a.apm_id = cm.apm_id " +
            "AND cm.cartera_medica_estado_id = cme.id " +
            "AND (a.visita_exitosa is null " +
            "OR a.visita_exitosa = 1) " +
            "AND a.apm_id = :apmId " +
            "AND a.doctor_id in :doctores " +
            "AND cme.estado <> 'BAJA_APROBADA' " +
            "AND cme.estado <> 'ALTA_RECHAZADA' " +
            "AND cme.estado <> 'ACTIVACION_RECHAZADA' " +
            "GROUP BY a.doctor_id", nativeQuery = true)
    List<FechasUltimaVisitaProjection> getLastVisitByApmDoctors(Long apmId, List<Long> doctores);

    @Query(value = "SELECT a.id as id, a.primerNombre as nombre, a.primerApellido as apellido, " +
            "MAX(cm.modified_date) fechaInicio, " +
            "COUNT(a2.id) visitas, " +
            "dv.frecuencia frecuencia " +
            "FROM apm a " +
            "JOIN cartera_medica cm ON cm.apm_id = a.id " +
            "LEFT JOIN agenda a2 ON a2.apm_id = a.id AND a2.doctor_id = cm.doctor_id " +
            "JOIN cartera_medica_estado cme ON cme.id = cm.cartera_medica_estado_id " +
            "LEFT JOIN datos_visita dv ON dv.id = cm.datos_visita_id " +
            "WHERE (a2.agenda_tipo_id = 0 OR a2.agenda_tipo_id IS NULL) " +
            "AND cm.inactivo = 0 " +
            "AND a.inactivo  = 0 " +
            "AND (:exitosa is null or a2.visita_exitosa = :exitosa) " +
            "AND (cme.estado = 'ALTA_APROBADA' OR cme.estado = 'ACTIVACION_APROBADA') " +
            "AND cm.doctor_id = :doctorId " +
            "AND (:gerenteId is null OR a.gerente_regional_id = :gerenteId) " +
            "GROUP BY  a.id, a.primerNombre, a.primerApellido, dv.frecuencia",
    countQuery = "SELECT COUNT(*) " +
            "FROM apm a " +
            "JOIN cartera_medica cm ON cm.apm_id = a.id " +
            "LEFT JOIN agenda a2 ON a2.apm_id = a.id AND a2.doctor_id = cm.doctor_id " +
            "JOIN cartera_medica_estado cme ON cme.id = cm.cartera_medica_estado_id " +
            "LEFT JOIN datos_visita dv ON dv.id = cm.datos_visita_id " +
            "WHERE " +
            "(a2.agenda_tipo_id = 0 or a2.agenda_tipo_id is null) " +
            "and cm.inactivo = 0 " +
            "AND a.inactivo  = 0 " +
            "AND (:exitosa is null or a2.visita_exitosa = :exitosa) " +
            "AND (cme.estado = 'ALTA_APROBADA' OR cme.estado = 'ACTIVACION_APROBADA') " +
            "AND cm.doctor_id = :doctorId " +
            "AND (:gerenteId is null OR a.gerente_regional_id = :gerenteId)",
    nativeQuery = true)
    List<KpiApmVisitasProjection> getVisitasApmByDoctorId(Long doctorId, Long gerenteId, Boolean exitosa);

    @Query(value = "SELECT a FROM Agenda a WHERE a.inactivo = 0 and a.inicio = :inicio AND a.fin = :fin AND a.apm.id = :apmId " +
            "AND a.agendaTipo.id = :agendaTipo AND a.turno = :turno " +
            "AND (:doctorId IS NULL OR a.doctor.id = :doctorId) " +
            "AND (:motivoFallidaId IS NULL OR a.motivoFallida.id = :motivoFallidaId) " +
            "AND (:perfil IS NULL OR a.perfil = :perfil)")
    List<Agenda> getAgendasByParams(Timestamp inicio, Timestamp fin, Long apmId, Long doctorId, Long agendaTipo, TurnoEnum turno, Long motivoFallidaId, PerfilEnum perfil);

    @Query(value = "EXEC Reporte_Visita_por_Ciclo_V3 @ciclo=:ciclo", nativeQuery = true)
    List<ReporteVisitasProjection> Reporte_Visita_por_Ciclo(@Param("ciclo") Integer ciclo);

    @Query(value = "EXEC Reporte_TiempoFueraTerritorio_por_Ciclo_V2 @ciclo=:ciclo", nativeQuery = true)
    List<ReporteTiempoFueraExcelProjection> getReporteTiempoFueraPorCiclo(@Param("ciclo") Integer ciclo);
}
