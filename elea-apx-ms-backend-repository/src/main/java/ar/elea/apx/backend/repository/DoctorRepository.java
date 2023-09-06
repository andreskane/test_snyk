package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.Doctor;
import ar.elea.apx.backend.entity.Provincia;
import ar.elea.apx.backend.entity.enums.DoctorTipoEstancia;
import ar.elea.apx.backend.projection.*;
import ar.elea.apx.backend.view.DoctorWithLastVisitView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Felipe Jimenez
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    /**
     * Find all doctors with the given surname
     *
     * @param primerApellido
     * @return list of doctors
     */
    List<Doctor> findAllByPrimerApellido(String primerApellido);

    List<Doctor> findAllByPrimerApellidoStartingWith(String primerApellido);

    /**
     * Find doctors with exact national registration with provided string
     *
     * @param matricula
     * @return
     */
    List<Doctor> findAllByMatriculaNacional(String matricula);

    /**
     * Find doctors with provincial registration with provided string
     *
     * @param matricula
     * @return
     */
    List<Doctor> findAllByMatriculaProvincial(String matricula);

    /**
     * Find doctor by matricula nacional
     *
     * @param matricula
     * @return
     */
    List<Doctor> findByMatriculaNacionalAndInactivoIsFalse(String matricula);


    /**
     * Find doctor by matricula provincial
     * @param matricula
     * @return
     */
    List<Doctor> findByMatriculaProvincialAndEntidadMatriculaProvincialAndInactivoIsFalse(String matricula, Provincia provincia);


    @Query(value =
            "SELECT new ar.elea.apx.backend.view.DoctorWithLastVisitView(d) " +
                    "FROM Doctor d LEFT JOIN d.especialidad LEFT JOIN d.espeSubEspecialidad " +
                    "where " +
                    "(:primerNombre is null or coalesce(d.primerNombre,'') like lower(concat('%', :primerNombre, '%')))  " +
                    "and (:primerApellido is null or coalesce(d.primerApellido,'') like lower(concat('%', :primerApellido, '%')))  " +
                    "and (:especialidad is null or d.especialidad.id = :especialidad) " +
                    "and (:subespecialidad is null or d.espeSubEspecialidad.id = :subespecialidad) " +
                    "and (:matriculaNacional is null or coalesce(d.matriculaNacional,'') like lower(concat('%', :matriculaNacional, '%')))  " +
                    "and (:matriculaProvincial is null or coalesce(d.matriculaProvincial,'') like lower(concat('%', :matriculaProvincial, '%')))  " +
                    "and (:tipoEstancia is null or d.tipoEstancia = :tipoEstancia)  " +
                    "and (:flagResidente is null or d.flagResidente = :flagResidente)  " +
                    "and (:flagJefeServicio is null or d.flagJefeServicio = :flagJefeServicio)  " +
                    "and (:inactivo is null or d.inactivo = :inactivo) ",

            countQuery = "select count(distinct d.id) " +
                    "FROM Doctor d LEFT JOIN d.especialidad LEFT JOIN d.espeSubEspecialidad " +
                    "where " +
                    " (:primerNombre is null or coalesce(d.primerNombre,'') like lower(concat('%', :primerNombre, '%')))  " +
                    "and (:primerApellido is null or coalesce(d.primerApellido,'') like lower(concat('%', :primerApellido, '%')))  " +
                    "and (:especialidad is null or d.especialidad.id = :especialidad) " +
                    "and (:subespecialidad is null or d.espeSubEspecialidad.id = :subespecialidad) " +
                    "and (:matriculaNacional is null or coalesce(d.matriculaNacional,'') like lower(concat('%', :matriculaNacional, '%')))  " +
                    "and (:matriculaProvincial is null or coalesce(d.matriculaProvincial,'') like lower(concat('%', :matriculaProvincial, '%')))  " +
                    "and (:tipoEstancia is null or d.tipoEstancia = :tipoEstancia)  " +
                    "and (:flagResidente is null or d.flagResidente = :flagResidente)  " +
                    "and (:flagJefeServicio is null or d.flagJefeServicio = :flagJefeServicio)  " +
                    "and (:inactivo is null or d.inactivo = :inactivo)")
    Page<DoctorWithLastVisitView> findDoctorsWithLastVisit(
            @Param("primerNombre") String nombre,
            @Param("primerApellido") String apellido,
            Long especialidad,
            Long subespecialidad,
            String matriculaNacional,
            String matriculaProvincial,
            DoctorTipoEstancia tipoEstancia,
            Boolean flagResidente,
            Boolean flagJefeServicio,
            Boolean inactivo,
            Pageable pageable);


    @Query("select d.profileImage from Doctor d where d.id = :id")
    byte[] getProfileImage(Long id);

    @Modifying
    @Query(value = "update doctor set inactivo = 1 where id = :id", nativeQuery = true)
    void markDoctorInactive(Long id);

    @Query(value =
            "select distinct " +
                    "repetidos.matriculaNacional as numeroMatricula, " +
                    "'' as enteEmisor, " +
                    "'' as nombreEmisor, " +
                    "repetidos.cantidad " +
                    "from doctor d " +
                    "left join " +
                    "     (SELECT matriculaNacional, COUNT(matriculaNacional) as cantidad " +
                    "         FROM doctor " +
                    "         GROUP BY matriculaNacional " +
                    "         HAVING COUNT(matriculaNacional) > 1) as repetidos " +
                    "     on d.matriculaNacional like repetidos.matriculaNacional " +
                    "left join cartera_medica cm on d.id = cm.doctor_id " +
                    "left join datos_visita dv on cm.datos_visita_id = dv.id " +
                    "left join apm a on cm.apm_id = a.id " +
                    "where d.matriculaNacional like repetidos.matriculaNacional and " +
                    "    (:idApm is null or cm.apm_id = :idApm) and " +
                    "    (:idGerenteRegional is null or a.gerente_regional_id = :idGerenteRegional) and " +
                    "    (:idEspecialidad is null or d.especialidad_id = :idEspecialidad) and " +
                    "    (:idEspecialidadVisita is null or dv.especialidad_id = :idEspecialidadVisita) and " +
                    "    (:nombreDoctor is null or d.primerNombre like concat(:nombreDoctor, '%')) and " +
                    "    (:apellidoDoctor is null or d.primerApellido like concat(:apellidoDoctor, '%')) and " +
                    "    (:matricula is null or d.matriculaNacional like concat(:matricula, '%')) and " +
                    "    (d.inactivo = 0) " +
                    "order by numeroMatricula desc"
            , nativeQuery = true)
    List<MatriculaProjection> findMatriculasNacionalesDuplicadas(Long idApm, Long idGerenteRegional,
                                                                 Long idEspecialidad, Long idEspecialidadVisita,
                                                                 String nombreDoctor, String apellidoDoctor, String matricula);

    @Query(value =
            "select distinct " +
                    "repetidos.matriculaProvincial as numeroMatricula, " +
                    "repetidos.enteEmisor_id as enteEmisor, " +
                    "repetidos.nombreEmisor, " +
                    "repetidos.cantidad " +
                    "from doctor d " +
                    "left join " +
                    "    (SELECT matriculaProvincial, enteEmisor_id, p.nombre as nombreEmisor, COUNT(*) as cantidad " +
                    "    FROM doctor " +
                    "    left join provincia p on enteEmisor_id = p.id " +
                    "    where matriculaProvincial is not null and enteEmisor_id is not null " +
                    "    GROUP BY matriculaProvincial, enteEmisor_id, p.nombre " +
                    "    HAVING COUNT(*) > 1) as repetidos " +
                    "    on d.matriculaProvincial like repetidos.matriculaProvincial and d.enteEmisor_id = repetidos.enteEmisor_id " +
                    "left join cartera_medica cm on d.id = cm.doctor_id " +
                    "left join datos_visita dv on cm.datos_visita_id = dv.id " +
                    "left join apm a on cm.apm_id = a.id " +
                    "where " +
                    "      d.matriculaProvincial like repetidos.matriculaProvincial and " +
                    "      d.enteEmisor_id = repetidos.enteEmisor_id and " +
                    "      (:idApm is null or cm.apm_id = :idApm) and " +
                    "      (:idGerenteRegional is null or a.gerente_regional_id = :idGerenteRegional) and " +
                    "      (:idEspecialidad is null or d.especialidad_id = :idEspecialidad) and " +
                    "      (:idEspecialidadVisita is null or dv.especialidad_id = :idEspecialidadVisita) and " +
                    "      (:nombreDoctor is null or d.primerNombre like concat(:nombreDoctor, '%')) and " +
                    "      (:apellidoDoctor is null or d.primerApellido like concat(:apellidoDoctor, '%')) and " +
                    "      (:matricula is null or d.matriculaNacional like concat(:matricula, '%')) and " +
                    "      (d.inactivo = 0) " +
                    "order by numeroMatricula desc"
            , nativeQuery = true)
    List<MatriculaProjection> findMatriculasProvincialesDuplicadas(Long idApm, Long idGerenteRegional, Long idEspecialidad, Long idEspecialidadVisita, String nombreDoctor, String apellidoDoctor, String matricula);



    @Query(value =
            "select d from Doctor d where " +
                    "(:enteEmisor is null and d.matriculaNacional like :matricula) or (d.entidadMatriculaProvincial.id = :enteEmisor and d.matriculaProvincial like :matricula)")
    List<Doctor> findByMatriculaAndEntidadMatriculaProvincial(String matricula, Long enteEmisor);

    @Query(value =
            "select distinct" +
                    "       matriculaNacional as numeroMatricula, " +
                    "       '' as enteEmisor, " +
                    "       '' as nombreEmisor, " +
                    "       null  as cantidad " +
                    "from doctor d " +
                    "     left join cartera_medica cm on d.id = cm.doctor_id " +
                    "     left join datos_visita dv on cm.datos_visita_id = dv.id " +
                    "     left join apm a on cm.apm_id = a.id " +
                    "where " +
                    "    matriculaNacional in (select matriculaProvincial from doctor where doctor.inactivo = 0) and " +
                    "    matriculaNacional <> matriculaProvincial and " +
                    "    matriculaNacional <> matriculaProvincial and " +
                    "    matriculaNacional not like '' and " +
                    "    (:idApm is null or cm.apm_id = :idApm) and " +
                    "    (:idGerenteRegional is null or a.gerente_regional_id = :idGerenteRegional) and " +
                    "    (:idEspecialidad is null or d.especialidad_id = :idEspecialidad) and " +
                    "    (:idEspecialidadVisita is null or dv.especialidad_id = :idEspecialidadVisita) and " +
                    "    (:nombreDoctor is null or d.primerNombre like concat(:nombreDoctor, '%')) and " +
                    "    (:apellidoDoctor is null or d.primerApellido like concat(:apellidoDoctor, '%')) and " +
                    "    (:matricula is null or d.matriculaNacional like concat(:matricula, '%')) and " +
                    "    (d.inactivo = 0) " +
                    "order by numeroMatricula"
            , nativeQuery = true)
    List<MatriculaProjection> findMatriculasDuplicadasSoloNumeros(Long idGerenteRegional, Long idApm, Long idEspecialidad, Long idEspecialidadVisita, String nombreDoctor, String apellidoDoctor, String matricula);

    @Query(value =
            "select d from Doctor d where " +
                    "(d.matriculaNacional like :matricula or d.matriculaProvincial like :matricula) and d.inactivo = false")
    List<Doctor> findByAnyMatricula(String matricula);


    @Query(value =
            "select distinct d.primerApellido " +
                    "from doctor d " +
                    " left join cartera_medica cm on d.id = cm.doctor_id " +
                    " left join datos_visita dv on cm.datos_visita_id = dv.id " +
                    " left join apm a on cm.apm_id = a.id " +
                    "where " +
                    "    d.primerApellido in (SELECT primerApellido FROM doctor GROUP BY primerApellido HAVING COUNT(primerApellido) > 1) and " +
                    "    (:idApm is null or cm.apm_id = :idApm) and " +
                    "    (:idGerenteRegional is null or a.gerente_regional_id = :idGerenteRegional) and " +
                    "    (:idEspecialidad is null or d.especialidad_id = :idEspecialidad) and " +
                    "    (:idEspecialidadVisita is null or dv.especialidad_id = :idEspecialidadVisita) and " +
                    "    (:nombreDoctor is null or d.primerNombre like concat(:nombreDoctor, '%')) and " +
                    "    (:apellidoDoctor is null or d.primerApellido like concat(:apellidoDoctor, '%')) and " +
                    "    (:matricula is null or d.matriculaNacional like concat(:matricula, '%')) and " +
                    "    (d.inactivo = 0)" +
                    "order by primerApellido",
            countQuery =
                    "select count(distinct d.primerApellido) " +
                            "from doctor d " +
                            "         left join cartera_medica cm on d.id = cm.doctor_id " +
                            "         left join datos_visita dv on cm.datos_visita_id = dv.id " +
                            "         left join apm a on cm.apm_id = a.id " +
                            "where d.primerApellido in (SELECT primerApellido FROM doctor GROUP BY primerApellido HAVING COUNT(primerApellido) > 1) " +
                            "  and (:idApm is null or cm.apm_id = :idApm) " +
                            "  and (:idGerenteRegional is null or a.gerente_regional_id = :idGerenteRegional) " +
                            "  and (:idEspecialidad is null or d.especialidad_id = :idEspecialidad) " +
                            "  and (:idEspecialidadVisita is null or dv.especialidad_id = :idEspecialidadVisita) and " +
                            "    (:nombreDoctor is null or d.primerNombre like concat(:nombreDoctor, '%')) and " +
                            "    (:apellidoDoctor is null or d.primerApellido like concat(:apellidoDoctor, '%')) and " +
                            "    (:matricula is null or d.matriculaNacional like concat(:matricula, '%')) and " +
                            "    (d.inactivo = 0)"
            , nativeQuery = true)
    Page<String> findApellidosDuplicados(Long idGerenteRegional, Long idApm, Long idEspecialidad, Long idEspecialidadVisita, String nombreDoctor, String apellidoDoctor, String matricula, Pageable pageable);

    @Query(value =
            "select " +
                    "   distinct left(d.primerApellido, 4) as match " +
                    "from doctor d " +
                    "left join cartera_medica cm on d.id = cm.doctor_id " +
                    "left join datos_visita dv on cm.datos_visita_id = dv.id " +
                    "left join apm a on cm.apm_id = a.id " +
                    "where " +
                    "    left(d.primerApellido, 4) in (select left(d.primerApellido, 4) from doctor) and " +
                    "    (:idApm is null or cm.apm_id = :idApm) and " +
                    "    (:idGerenteRegional is null or a.gerente_regional_id = :idGerenteRegional) and " +
                    "    (:idEspecialidad is null or d.especialidad_id = :idEspecialidad) and " +
                    "    (:idEspecialidadVisita is null or dv.especialidad_id = :idEspecialidadVisita) and " +
                    "    (:nombreDoctor is null or d.primerNombre like concat(:nombreDoctor, '%')) and " +
                    "    (:apellidoDoctor is null or d.primerApellido like concat(:apellidoDoctor, '%')) and " +
                    "    (:matricula is null or d.matriculaNacional like concat(:matricula, '%')) " +
                    "    group by left(d.primerApellido, 4) " +
                    "    having count(left(d.primerApellido, 4)) > 1 " +
                    "union " +
                    "select " +
                    "    distinct left(d.primerApellido, 5) as match " +
                    "from doctor d " +
                    "left join cartera_medica cm on d.id = cm.doctor_id " +
                    "left join datos_visita dv on cm.datos_visita_id = dv.id " +
                    "left join apm a on cm.apm_id = a.id " +
                    "where " +
                    "        left(d.primerApellido, 5) in (select left(d.primerApellido, 5) from doctor) and " +
                    "    (:idApm is null or cm.apm_id = :idApm) and " +
                    "    (:idGerenteRegional is null or a.gerente_regional_id = :idGerenteRegional) and " +
                    "    (:idEspecialidad is null or d.especialidad_id = :idEspecialidad) and " +
                    "    (:idEspecialidadVisita is null or dv.especialidad_id = :idEspecialidadVisita) and " +
                    "    (:nombreDoctor is null or d.primerNombre like concat(:nombreDoctor, '%')) and " +
                    "    (:apellidoDoctor is null or d.primerApellido like concat(:apellidoDoctor, '%')) and " +
                    "    (:matricula is null or d.matriculaNacional like concat(:matricula, '%')) and " +
                    "    (d.inactivo = 0)" +
                    "group by left(d.primerApellido, 5) " +
                    "having count(left(d.primerApellido, 5)) > 1 " +
                    "order by match",
            countQuery =
                    "select count(*) from " +
                            "(select " +
                            "    distinct left(d.primerApellido, 4) as match " +
                            "from doctor d " +
                            "left join cartera_medica cm on d.id = cm.doctor_id " +
                            "left join datos_visita dv on cm.datos_visita_id = dv.id " +
                            "left join apm a on cm.apm_id = a.id " +
                            "where " +
                            "    left(d.primerApellido, 4) in (select left(d.primerApellido, 4) from doctor) and " +
                            "    (:idApm is null or cm.apm_id = :idApm) and " +
                            "    (:idGerenteRegional is null or a.gerente_regional_id = :idGerenteRegional) and " +
                            "    (:idEspecialidad is null or d.especialidad_id = :idEspecialidad) and " +
                            "    (:idEspecialidadVisita is null or dv.especialidad_id = :idEspecialidadVisita) and " +
                            "    (:nombreDoctor is null or d.primerNombre like concat(:nombreDoctor, '%')) and " +
                            "    (:apellidoDoctor is null or d.primerApellido like concat(:apellidoDoctor, '%')) and " +
                            "    (:matricula is null or d.matriculaNacional like concat(:matricula, '%')) " +
                            "    group by left(d.primerApellido, 4) " +
                            "    having count(left(d.primerApellido, 4)) > 1 " +
                            "union " +
                            "select " +
                            "    distinct left(d.primerApellido, 5) as match " +
                            "from doctor d " +
                            "         left join cartera_medica cm on d.id = cm.doctor_id " +
                            "         left join datos_visita dv on cm.datos_visita_id = dv.id " +
                            "         left join apm a on cm.apm_id = a.id " +
                            "where " +
                            "        left(d.primerApellido, 5) in (select left(d.primerApellido, 5) from doctor) and " +
                            "    (:idApm is null or cm.apm_id = :idApm) and " +
                            "    (:idGerenteRegional is null or a.gerente_regional_id = :idGerenteRegional) and " +
                            "    (:idEspecialidad is null or d.especialidad_id = :idEspecialidad) and " +
                            "    (:idEspecialidadVisita is null or dv.especialidad_id = :idEspecialidadVisita) and " +
                            "    (:nombreDoctor is null or d.primerNombre like concat(:nombreDoctor, '%')) and " +
                            "    (:apellidoDoctor is null or d.primerApellido like concat(:apellidoDoctor, '%')) and " +
                            "    (:matricula is null or d.matriculaNacional like concat(:matricula, '%')) and " +
                            "    (d.inactivo = 0)" +
                            "group by left(d.primerApellido, 5) " +
                            "having count(left(d.primerApellido, 5)) > 1) result ",
            nativeQuery = true)
    Page<String> findApellidosParcialmenteDuplicados(Long idGerenteRegional, Long idApm, Long idEspecialidad, Long idEspecialidadVisita, String nombreDoctor, String apellidoDoctor, String matricula, Pageable pageable);

    @Query(value =
            "SELECT " +
                    " nombre," +
                    "COUNT(*) as cantidad, " +
                    "COUNT(*) * 100.0 / " +
                    "(  SELECT count(doctor.id) " +
                    "    FROM cartera_medica " +
                    "     JOIN cartera_medica_estado cme ON cme.id = cartera_medica.cartera_medica_estado_id " +
                    "     JOIN apm on cartera_medica.apm_id = apm.id" +
                    "     JOIN doctor on cartera_medica.doctor_id = doctor.id " +
                    "     JOIN usuario on apm.gerente_regional_id = usuario.id " +
                    "    WHERE " +
                    "    cartera_medica.inactivo = 0 " +
                    "    and usuario.inactivo = 0 " +
                    "    and apm.inactivo = 0 " +
                    "    AND doctor.inactivo = 0 " +
                    "    and usuario.rol = 'GERENTE_REGIONAL' " +
                    "     AND (cme.estado = 'ALTA_APROBADA' OR cme.estado = 'ACTIVACION_APROBADA' OR cme.estado = 'BAJA_RECHAZADA' OR cme.estado IS NULL " +
                    "  OR (cme.estado = 'BAJA_APROBADA' AND (cme.fecha_confirmacion BETWEEN (SELECT c.inicio from ciclo c WHERE c.id = :cicloId) AND (SELECT c.fin from ciclo c WHERE c.id = :cicloId)))) " +
                    "    AND (:gerenteId is null or usuario.id = :gerenteId) " +
                    "    AND (:idApm is null or apm.id = :idApm) " +
                    "   ) as porcentaje " +
                    "FROM " +
                    "  ( SELECT doctor.id as idDoctor, doctor.especialidad_id as especialidadId " +
                    "    FROM cartera_medica " +
                    "     JOIN cartera_medica_estado cme ON cme.id = cartera_medica.cartera_medica_estado_id " +
                    "     JOIN apm on cartera_medica.apm_id = apm.id " +
                    "     JOIN doctor on cartera_medica.doctor_id = doctor.id " +
                    "     JOIN usuario on apm.gerente_regional_id = usuario.id " +
                    "    WHERE " +
                    "    cartera_medica.inactivo = 0 " +
                    "    and usuario.inactivo = 0 " +
                    "    and apm.inactivo = 0 " +
                    "    AND doctor.inactivo = 0 " +
                    "    and usuario.rol = 'GERENTE_REGIONAL' " +
                    "     AND (cme.estado = 'ALTA_APROBADA' OR cme.estado = 'ACTIVACION_APROBADA' OR cme.estado = 'BAJA_RECHAZADA' OR cme.estado IS NULL " +
                    "  OR (cme.estado = 'BAJA_APROBADA' AND (cme.fecha_confirmacion BETWEEN (SELECT c.inicio from ciclo c WHERE c.id = :cicloId) AND (SELECT c.fin from ciclo c WHERE c.id = :cicloId)))) " +
                    "    AND (:gerenteId is null or usuario.id = :gerenteId) " +
                    "    AND (:idApm is null or apm.id = :idApm) " +
                    "   ) tbl " +
                    "   join especialidad e on e.id=especialidadId " +
                    "GROUP BY especialidadId, nombre ORDER BY cantidad DESC",
            nativeQuery = true)
    List<DoctoresEspecialidadesProjection> buscarMedicosPorEspecialidadesYGererente(@Param("gerenteId") Long gerenteId, @Param("idApm") Long idApm, Long cicloId);

    @Modifying
    @Query(value = "INSERT INTO tag_doctor (tag_id, doctor_id) VALUES(:tagId, :doctorId)", nativeQuery = true)
    void registroInteresesDoctor(Long tagId,Long doctorId);

    @Modifying
    @Query(value = "delete from tag_doctor where doctor_id = :doctorId", nativeQuery = true)
    void deleteInteresesDoctor(Long doctorId);

    Optional<Doctor> findById(Long id);

    /**
     * Find doctors with exact national registration with provided string and doctorId
     *
     * @param matricula
     * @param doctorId
     * @return
     */
    @Query("select d from Doctor d where d.matriculaNacional = :matricula and (:doctorId is null OR d.id <> :doctorId)")
    List<Doctor> findAllByMatriculaNacionalNotDoctorId(String matricula, Long doctorId);

    /**
     * Find doctors with provincial registration with provided string and doctorId
     *
     * @param matricula
     * @param doctorId
     * @return
     */
    @Query("select d from Doctor d where d.matriculaProvincial = :matricula and (:doctorId is null OR d.id <> :doctorId)")
    List<Doctor> findAllByMatriculaProvincialNotDoctorId(String matricula, Long doctorId);

    @Query("SELECT MAX(a.inicio) as ultimaVisita, a.doctor.id as id from Agenda a where a.inactivo=0 and a.agendaTipo.id = 0 and a.doctor.id in(:doctores) group by a.doctor.id")
    List<UltimaVisitaApmProjection> getUltimaVisitaByDoctors(List<Long> doctores);

    @Query(value = "SELECT d.id, TRIM(CONCAT(trim(d.primerNombre), ' ',  TRIM(d.segundoNombre))) nombre, " +
            "TRIM(CONCAT(TRIM(d.primerApellido), ' ', trim(segundoApellido))) apellido, d.matriculaNacional, d.matriculaProvincial , d.especialidad_id , d.espeSubespecialidad_id,  " +
            "e.nombre AS especialidad, e2.nombre AS subEspecialidad, d.estancia asientoGira, d.flag_jefe_servicio jefeServicio, d.flag_residente residente, d.inactivo" +
            ", FORMAT(MAX(a.inicio), 'dd/MM/yyyy') ultimaVisita " +
            "FROM doctor d " +
            "LEFT JOIN agenda a ON (a.doctor_id = d.id and a.agenda_tipo_id = 0) " +
            "JOIN especialidad e ON e.id = d.especialidad_id " +
            "LEFT JOIN especiaSubespecialidad e2 ON e2.id = d.espeSubespecialidad_id " +
            "WHERE d.inactivo = 0 " +
            "AND (:apellido is null OR trim(concat(d.primerApellido, d.segundoApellido)) like concat('%',:apellido, '%')) " +
            "AND (:nombre is null OR trim(concat(d.primerNombre, d.segundoNombre)) like concat('%',:nombre, '%')) " +
            "AND (:matNacional is null OR d.matriculaNacional like concat('%',:matNacional, '%')) " +
            "AND (:matProvincial is null OR d.matriculaProvincial like concat('%',:matProvincial, '%')) " +
            "AND (:idEspecialidad is null OR d.especialidad_id = :idEspecialidad) " +
            "AND (:idSubEspecialidad is null OR d.espeSubespecialidad_id = :idSubEspecialidad) " +
            "AND (:jefeServicio is null OR d.flag_jefe_servicio = :jefeServicio) " +
            "AND (:residente is null OR d.flag_residente = :residente) " +
            "AND (:estancia is null OR d.estancia = :estancia) " +
            "GROUP BY  d.id, d.primerNombre, d.segundoNombre, d.primerApellido, d.segundoApellido , " +
            "d.matriculaNacional, d.matriculaProvincial , d.especialidad_id , d.espeSubespecialidad_id ,  " +
            "e.nombre, e2.nombre, d.estancia, d.flag_jefe_servicio, d.flag_residente, d.inactivo", nativeQuery = true)
    List<DoctorUltimaVisitaProjection> getDoctorUltimaVisita(String apellido, String nombre, String matNacional, String matProvincial,
                                                             String idEspecialidad, String idSubEspecialidad,
                                                             Boolean jefeServicio, Boolean residente, String estancia);


    @Query(value =  "SELECT d.id, d.primerNombre, d.segundoNombre, d.primerApellido, d.segundoApellido, d.matriculaNacional, d.matriculaProvincial " +
                    "FROM doctor d " +
                    "WHERE inactivo = 0 " +
                        "AND (:nombre IS NULL OR " +
                            "REPLACE(CONCAT(d.primerNombre, d.segundoNombre, d.primerApellido, d.segundoApellido), ' ', '') " +
                            " LIKE '%' + UPPER(:nombre) + '%') " +
                        "AND d.id NOT IN ( " +
                            "SELECT DISTINCT cm.doctor_id " +
                            "FROM cartera_medica cm " +
                            "WHERE cm.inactivo = 0 " +
                                "AND (cm.apm_id = :idApm)) " +
                    "ORDER BY d.primerApellido", nativeQuery = true)
    List<DoctoresProjection> getDoctoresResumidos(String nombre, Long idApm);

    @Query(value = "SELECT d.id, d.primerNombre, d.segundoNombre, d.primerApellido, d.segundoApellido, d.matriculaNacional, d.matriculaProvincial " +
            "FROM cartera_medica cm " +
            "LEFT JOIN cartera_medica_estado cme on cme.id=cm.cartera_medica_estado_id " +
            "LEFT JOIN doctor d on cm.doctor_id = d.id " +
            "WHERE cm.inactivo = 0 " +
            "AND d.inactivo = 0 " +
            "AND (:nombre IS NULL OR REPLACE(CONCAT(d.primerNombre, d.segundoNombre, d.primerApellido, d.segundoApellido), ' ', '') LIKE CONCAT('%', UPPER(:nombre), '%')) " +
            "AND (cme.estado = 'ALTA_APROBADA' OR cme.estado = 'ACTIVACION_APROBADA'  OR cme.estado = 'BAJA_SOLICITADA'  " +
            "OR (cme.estado = 'BAJA_APROBADA' AND (cme.fecha_confirmacion BETWEEN :inicio and :fin ))) " +
            " AND (:idApm is null or cm.apm_id = :idApm) ", nativeQuery = true)
    List<DoctoresProjection> getDoctoresResumidosByApm(Long idApm, String nombre, LocalDate inicio, LocalDate fin);

    @Query(value = "EXEC Reporte_Medico @ciclo = :ciclo", nativeQuery = true)
    List<MedicoExcelProjection> getReporteDoctores(@Param("ciclo") Long ciclo);


    @Query(value = "select   " +
            "id," +
            "primerNombre," +
            "segundoNombre," +
            "primerApellido," +
            "segundoApellido," +
            "fechaNacimiento," +
            "eMail," +
            "telefono," +
            "sexo," +
            "especialidadId," +
            "doctorApp," +
            "inicio," +
            "matriculaNacional," +
            "matriculaProvincial," +
            "especialidad," +
            "subespecialidad," +
            "ultimaVisita as ultimaVisita," +
            "estancia," +
            "residente," +
            "jefeServicio ," +
            "entidadMatriculaProvincial ," +
            "trim(ISO_entidadMatricula) as ISO_entidadMatricula" +
            " from (select d.id, " +
            "d.primerNombre as primerNombre, d.segundoNombre as segundoNombre , d.primerApellido as primerApellido  , d.segundoApellido as segundoApellido , " +
            "null as fechaNacimiento, " +
            "null as eMail, null as telefono, null as sexo, especialid1_.id as especialidadId, null as doctorApp, null as inicio, " +
            "d.matriculaNacional as matriculaNacional ," +
            " d.matriculaProvincial as matriculaProvincial , " +
            "especialid1_.nombre as especialidad, " +
            "especiasub2_.nombre as subespecialidad, " +
            "(SELECT MAX(FORMAT(agenda.inicio, 'dd/MM/yyyy HH:mm')) as ultimaVisita " +
            "from agenda " +
            "where agenda.inactivo=0 " +
            "and agenda.agenda_tipo_id = 0 " +
            "and agenda.doctor_id = d.id) as ultimaVisita, " +
            "d.estancia as estancia, d.flag_residente as residente, d.flag_jefe_servicio as jefeServicio , " +
            "case when pro.id=-1 then null else  pro.nombre end entidadMatriculaProvincial, " +
            "case when pro.ISO_CODE is null then '' else trim(pro.ISO_CODE) end as ISO_entidadMatricula " +
            "from doctor d " +
            "left join cartera_medica cm ON d.id =cm.doctor_id " +
            "left JOIN cartera_medica_estado cme ON cme.id = cm.cartera_medica_estado_id  " +
            "left JOIN apm on apm.id=cm.apm_id " +
            "left outer join Especialidad especialid1_ on d.especialidad_id = especialid1_.id " +
            "left outer join EspeciaSubespecialidad especiasub2_ on d.espeSubEspecialidad_id = especiasub2_.id " +
            "left join provincia pro on pro.id=d.EntidadMatriculaProvincial_id " +
            "where " +
            "( d.deleted = 0 or d.deleted is null) " +
            "and (:idGerente is null or apm.gerente_regional_id = :idGerente) " +
            "and cm.inactivo = 0 " +
            "and apm.inactivo =0 " +
            "and (cme.estado = 'ALTA_APROBADA' OR cme.estado = 'ACTIVACION_APROBADA' OR cme.estado = 'BAJA_RECHAZADA' OR cme.estado IS NULL " +
            "OR (cme.estado = 'BAJA_APROBADA' AND (cme.fecha_confirmacion BETWEEN  (SELECT c.inicio from ciclo c WHERE c.id = :cicloId) AND (SELECT c.fin from ciclo c WHERE c.id = :cicloId)))) " +
            "and ( d.deleted = 0 or d.deleted is null) " +
            "and (:nombre is null or coalesce(d.primerNombre, '') like lower(('%' +:nombre+ '%'))) " +
            "and (:apellido is null or coalesce(d.primerApellido,'') like lower(('%' +:apellido+ '%'))) " +
            "and (:especialidad is null or d.especialidad_id =:especialidad) " +
            "and (:subespecialidad is null or d.espeSubEspecialidad_id =:subespecialidad) " +
            "and (:matriculaNacional is null or coalesce(d.matriculaNacional,'') like lower(('%' +:matriculaNacional+ '%'))) " +
            " and (:matriculaProvincial is null or coalesce(d.matriculaProvincial, '') like lower(('%'+:matriculaProvincial+'%'))) " +
            " and (:tipoEstancia is null or d.estancia=:tipoEstancia) " +
            " and (:flagResidente is null or d.flag_residente=:flagResidente) " +
            " and (:flagJefeServicio is null or d.flag_jefe_servicio=:flagJefeServicio) " +
            " and (:inactivo  is null or d.inactivo=:inactivo) " +
            "GROUP BY d.id, d.primerNombre, d.segundoNombre, " +
            "d.primerApellido, d.segundoApellido, " +
            "d.matriculaNacional, " +
            "d.matriculaProvincial , " +
            " especialid1_.nombre, " +
            " especialid1_.id, " +
            " especiasub2_.nombre, d.estancia, d.flag_residente, d.flag_jefe_servicio, " +
            " case when pro.id=-1 then null else  pro.nombre end, " +
            "pro.ISO_CODE) as cm",
            countQuery = "select COUNT(distinct d.id) " +
                    "from doctor d " +
                    "left join cartera_medica cm ON d.id =cm.doctor_id " +
                    "left JOIN cartera_medica_estado cme ON cme.id = cm.cartera_medica_estado_id  " +
                    "left JOIN apm on apm.id=cm.apm_id " +
                    "left outer join Especialidad especialid1_ on d.especialidad_id = especialid1_.id " +
                    "left outer join EspeciaSubespecialidad especiasub2_ on d.espeSubEspecialidad_id = especiasub2_.id " +
                    "left join provincia pro on pro.id=d.EntidadMatriculaProvincial_id " +
                    "where " +
                    "( d.deleted = 0 or d.deleted is null) " +
                    "and (:idGerente is null or apm.gerente_regional_id = :idGerente) " +
                    "and cm.inactivo = 0 " +
                    "and apm.inactivo =0 " +
                    "and (cme.estado = 'ALTA_APROBADA' OR cme.estado = 'ACTIVACION_APROBADA' OR cme.estado = 'BAJA_RECHAZADA' OR cme.estado IS NULL " +
                    "OR (cme.estado = 'BAJA_APROBADA' AND (cme.fecha_confirmacion BETWEEN  (SELECT c.inicio from ciclo c WHERE c.id = :cicloId) AND (SELECT c.fin from ciclo c WHERE c.id = :cicloId)))) " +
                    "and ( d.deleted = 0 or d.deleted is null) " +
                    "and (:nombre is null or coalesce(d.primerNombre, '') like lower(('%' +:nombre+ '%'))) " +
                    "and (:apellido is null or coalesce(d.primerApellido,'') like lower(('%' +:apellido+ '%'))) " +
                    "and (:especialidad is null or d.especialidad_id =:especialidad) " +
                    "and (:subespecialidad is null or d.espeSubEspecialidad_id =:subespecialidad) " +
                    "and (:matriculaNacional is null or coalesce(d.matriculaNacional,'') like lower(('%' +:matriculaNacional+ '%'))) " +
                    " and (:matriculaProvincial is null or coalesce(d.matriculaProvincial, '') like lower(('%'+:matriculaProvincial+'%'))) " +
                    " and (:tipoEstancia is null or d.estancia=:tipoEstancia) " +
                    " and (:flagResidente is null or d.flag_residente=:flagResidente) " +
                    " and (:flagJefeServicio is null or d.flag_jefe_servicio=:flagJefeServicio) " +
                    " and (:inactivo  is null or d.inactivo=:inactivo)",
            nativeQuery = true)
    Page<DoctoresProjection> getDoctorUltimaVisita(String nombre,
                                                   String apellido,
                                                   Long especialidad,
                                                   Long subespecialidad,
                                                   String matriculaNacional,
                                                   String matriculaProvincial,
                                                   DoctorTipoEstancia tipoEstancia,
                                                   Boolean flagResidente,
                                                   Boolean flagJefeServicio,
                                                   Boolean inactivo,
                                                   Long cicloId,
                                                   Long idGerente,
                                                   Pageable pageable);
}
