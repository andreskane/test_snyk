package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.view.CarteraMedicaView;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Guillermo Nasi
 */
@Repository
public class CarteraMedicaCustomRepository {
    private final EntityManager entityManager;

    public CarteraMedicaCustomRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * List, filter and page carteras medicas
     *
     * @param idApm                -
     * @param doctorNombre         -
     * @param doctorApellido       -
     * @param matriculaNacional    -
     * @param matriculaProvincial  -
     * @param especialidadDoctorId -
     * @param inactivo             -
     * @param idGerente            -
     * @param semaforo             -
     * @param provinciaId          -
     * @param localidadId          -
     * @param ciudadId             -
     * @param calleId              -
     * @param codigoPostal         -
     * @param turno                -
     * @param cantidad             -
     * @param pagina               -
     * @return -
     */
    public Page<CarteraMedicaView> list(Long idApm,
                                        String doctorNombre, String doctorApellido,
                                        String matriculaNacional, String matriculaProvincial,
                                        Long especialidadDoctorId, Long subespecialidad, Boolean inactivo,
                                        Long idGerente, Integer semaforo,
                                        Long provinciaId, Long localidadId, Long ciudadId,
                                        Long calleId, String codigoPostal, Integer turno, Integer frecuencia, Integer cantidad,
                                        Integer pagina, String order) {
        Session session = entityManager.unwrap(Session.class);


        String query =
                "select cm.id as carteraId, " +
                        "cm.doctor_id as doctorId, " +
                        "d.primerApellido as doctorApellido, " +
                        "d.primerNombre as doctorNombre, " +
                        "d.doctor_app as doctorApp, " +
                        "d.matriculaNacional as matriculaNacional, " +
                        "d.matriculaProvincial as matriculaProvincial, " +
                        "especialidadDoctor.nombre as especialidadDoctor, " +
                        "sep.nombre as subespecialidad, " +
                        "p.nombre as provinciaDireccion, " +
                        "l.nombre as localidadDireccion, " +
                        "c.nombre as ciudadDireccion, " +
                        "calle.nombre as calleDireccion, " +
                        "direccion.numero as numeroDireccion, " +
                       "cm.apm_id as apmId, " +
                        "apm.primerApellido as apmApellido, " +
                        "apm.primerNombre as apmNombre, " +
                        "gerente_regional.apellido as gerenteRegionalApellido, " +
                        "gerente_regional.nombre as gerenteRegionalNombre, " +
                        "datos_visita.frecuencia as frecuencia, " +
                        "datos_visita.turno as turno, " +
                        "(select top 1 a.inicio from agenda a " +
                        "   where agenda_tipo_id = 0 and a.doctor_id = d.id and a.apm_id = apm.id " +
                        "   order by a.inicio) as ultimaVisitaCartera, " +
                        "(select count(*) from agenda a, ( " +
                        "   select inicio, fin from ciclo " +
                        "       where inicio <= current_timestamp and fin >= current_timestamp) as ciclo " +
                        "   where agenda_tipo_id = 0 and a.doctor_id = d.id and a.apm_id = apm.id " +
                        "   and a.inicio >= ciclo.inicio and a.inicio <= ciclo.fin) as visitasCiclo, " +
                        " codigo_postal.cpa as cpa, " +


                        " case when direccion.piso is null then '' else direccion.piso end as pisoDireccion, " +
                        " case when direccion.departamento is null then '' else direccion.departamento end as departamentoDireccion, " +
                        " case when direccion.otro is null then '' else direccion.otro end  as otroDireccion, " +


                        " pro.nombre entidadMatriculaProvincial , trim(pro.ISO_CODE) as ISO_entidadMatriculaProvincial ," +
                        "  case when datos_visita.frecuencia >0 then" +
                        "  cast(" +
                        "          (select count(*) from agenda a, (" +
                        "          select inicio, fin from ciclo" +
                        "  where inicio <=  current_timestamp  and fin >=   current_timestamp ) as ciclo" +
                        "  where agenda_tipo_id = 0 and a.doctor_id = d.id and a.apm_id = apm.id " +
                        " and a.inicio >= ciclo.inicio and a.inicio <= ciclo.fin) as float)" +
                        " /" +
                        " cast(datos_visita.frecuencia as float)" +
                        " else 1  end semaforo " +


                        " from " +
                        "cartera_medica cm " +
                        "left join cartera_medica_estado cme on " +
                        "cm.cartera_medica_estado_id = cme.id " +
                        "left join datos_visita on " +
                        "cm.datos_visita_id = datos_visita.id " +
                        "left join institucion on " +
                        "datos_visita.institucion_id = institucion.id " +
                        "left join direccion on direccion.id = CASE WHEN datos_visita.id_direccion IS NOT NULL THEN datos_visita.id_direccion ELSE institucion.id_direccion END " +
                        "left join apm on cm.apm_id = apm.id " +
                        "left join doctor d on cm.doctor_id = d.id " +
                        "left join codigo_postal on (direccion.id is not null and direccion.id_codigo_postal = codigo_postal.id) " +
                        "left join provincia p on codigo_postal.id_provincia = p.id " +
                        "left join localidad l on codigo_postal.id_localidad = l.id " +
                        "left join ciudad c on codigo_postal.id_ciudad = c.id " +
                        "left join calle on codigo_postal.id_calle = calle.id " +
                        "left join usuario gerente_regional on gerente_regional.id = apm.gerente_regional_id " +
                        "left join especialidad especialidadDoctor on d.especialidad_id = especialidadDoctor.id " +
                        "left join especiaSubespecialidad sep on sep.id = d.espeSubespecialidad_id " +
                        "left join provincia pro on pro.id=d.EntidadMatriculaProvincial_id " +
                        "where " +
                        "cm.inactivo = 0 " +
                        "and apm.inactivo = 0 " +
                        "and d.inactivo = 0 " +
                        "AND (cme.estado = 'ALTA_APROBADA' " +
                        "OR cme.estado = 'BAJA_RECHAZADA ' " +
                        "OR cme.estado = 'ACTIVACION_APROBADA' )";

        query = carteraMedicaQueryFilters(query);

        if (order != null)
            query = query + order;

        Integer countResults = listCarteraMedicaCount(idApm, doctorNombre, doctorApellido, subespecialidad, matriculaNacional, matriculaProvincial, especialidadDoctorId,
                inactivo, idGerente, semaforo, provinciaId, localidadId, ciudadId,
                calleId, codigoPostal, turno, frecuencia);


        NativeQuery<CarteraMedicaView> nativeQuery = session.createNativeQuery(query);
        addParametersToCarteraMedicaQuery(idApm, doctorNombre, doctorApellido, subespecialidad,
                matriculaNacional, matriculaProvincial, especialidadDoctorId, idGerente, semaforo,
                provinciaId, localidadId, ciudadId, calleId, codigoPostal, turno, frecuencia, nativeQuery);

        if (cantidad != null && pagina != null) {
            nativeQuery.setMaxResults(cantidad);
            nativeQuery.setFirstResult(getFirstResult(pagina, cantidad));
        }

        nativeQuery.setResultSetMapping("CarterasMedicasView");


        List<CarteraMedicaView> result = nativeQuery.getResultList();

        if (cantidad != null && pagina != null) {
            return new PageImpl<>(result, PageRequest.of(pagina - 1, cantidad), countResults);
        }

        return new PageImpl<>(result);
    }


    private int getFirstResult(Integer pagina, Integer cantidad) {
        if (pagina == 1)
            return 0;
        else
            return (pagina - 1) * cantidad;
    }

    /**
     * Count the registers that matched the query
     *
     * @param idApm                -
     * @param doctorNombre         -
     * @param doctorApellido       -
     * @param matriculaNacional    -
     * @param matriculaProvincial  -
     * @param especialidadDoctorId -
     * @param inactivo             -
     * @param idGerente            -
     * @param semaforo             -
     * @param provinciaId          -
     * @param localidadId          -
     * @param ciudadId             -
     * @param calleId              -
     * @param codigoPostal         -
     * @param turno                -
     * @return -
     */
    public Integer listCarteraMedicaCount(Long idApm,
                                          String doctorNombre, String doctorApellido, Long subespecialidad,
                                          String matriculaNacional, String matriculaProvincial,
                                          Long especialidadDoctorId, Boolean inactivo,
                                          Long idGerente, Integer semaforo,
                                          Long provinciaId, Long localidadId, Long ciudadId,
                                          Long calleId, String codigoPostal, Integer turno, Integer frecuencia) {
        String countQuery =
                "select count(cm.id) from cartera_medica cm " +
                        "left join cartera_medica_estado cme on " +
                        "cm.cartera_medica_estado_id = cme.id " +
                        "left join datos_visita on " +
                        "cm.datos_visita_id = datos_visita.id " +
                        "left join institucion on " +
                        "datos_visita.institucion_id = institucion.id " +
                        "left join direccion on direccion.id = CASE WHEN datos_visita.id_direccion IS NOT NULL THEN datos_visita.id_direccion ELSE institucion.id_direccion END " +
                        "left join apm on cm.apm_id = apm.id " +
                        "left join doctor d on cm.doctor_id = d.id " +
                        "left join codigo_postal on (direccion.id is not null and direccion.id_codigo_postal = codigo_postal.id) " +
                        "left join provincia p on codigo_postal.id_provincia = p.id " +
                        "left join localidad l on codigo_postal.id_localidad = l.id " +
                        "left join ciudad c on codigo_postal.id_ciudad = c.id " +
                        "left join calle on codigo_postal.id_calle = calle.id " +
                        "left join usuario gerente_regional on gerente_regional.id = apm.gerente_regional_id " +
                        "left join especialidad especialidadDoctor on d.especialidad_id = especialidadDoctor.id " +
                        "left join especiaSubespecialidad sep on sep.id = d.espeSubespecialidad_id " +
                        "where " +
                        "cm.inactivo = 0 " +
                        "and apm.inactivo = 0 " +
                        "and d.inactivo = 0 " +
                        "AND (cme.estado = 'ALTA_APROBADA' " +
                        "OR cme.estado = 'BAJA_RECHAZADA ' " +
                        "OR cme.estado = 'ACTIVACION_APROBADA' " +
                        "OR cme.estado = 'BAJA_SOLICITADA' " +
                        "OR cme.estado = 'ALTA_SOLICITADA' OR cme.estado = 'ACTIVACION_SOLICITADA' )";

        countQuery = carteraMedicaQueryFilters(countQuery);

        NativeQuery<Integer> countNativeQuery = entityManager.unwrap(Session.class).createNativeQuery(countQuery);
        addParametersToCarteraMedicaQuery(idApm, doctorNombre, doctorApellido, subespecialidad,
                matriculaNacional, matriculaProvincial, especialidadDoctorId, idGerente, semaforo,
                provinciaId, localidadId, ciudadId, calleId, codigoPostal, turno, frecuencia, countNativeQuery);

        return countNativeQuery.uniqueResult();
    }

    private String carteraMedicaQueryFilters(String query) {
        query = query + " and (:primerApellido is null or d.primerApellido COLLATE Latin1_General_CI_AI like concat('%',:primerApellido, '%')) " +
                "and (:primerNombre is null or d.primerNombre COLLATE Latin1_General_CI_AI like concat('%',:primerNombre, '%')) " +
                "and (:idApm is null or apm.id = :idApm) " +
                "and (:idGerenteRegional is null or gerente_regional.id = :idGerenteRegional) " +
                "and (:matriculaNacional is null or d.matriculaNacional like concat('%',:matriculaNacional, '%')) " +
                "and (:matriculaProvincial is null or d.matriculaProvincial like concat('%',:matriculaProvincial, '%')) " +
                "and (:idEspecialidad is null or d.especialidad_id = :idEspecialidad) " +
                "and (:idSubespecialidad is null or sep.id = :idSubespecialidad) " +
                "and (:semaforo is null " +
                "   or (:semaforo = 0 and ( " +
                "    select count(*) " +
                "    from agenda a, ( " +
                "     select inicio, fin " +
                "     from ciclo " +
                "     where " +
                "      inicio <= current_timestamp " +
                "      and fin >= current_timestamp) as ciclo " +
                "    where agenda_tipo_id = 0 and a.doctor_id = d.id and a.apm_id = apm.id and a.inicio >= ciclo.inicio and a.inicio <= ciclo.fin) = 0 ) " +
                "   or (:semaforo = 1 and datos_visita.frecuencia > ( " +
                "    select count(*) " +
                "    from agenda a, (select inicio, fin " +
                "     from ciclo " +
                "     where inicio <= current_timestamp and fin >= current_timestamp) as ciclo " +
                "    where agenda_tipo_id = 0 and a.doctor_id = d.id and a.apm_id = apm.id and a.inicio >= ciclo.inicio and a.inicio <= ciclo.fin) " +
                "    and ( select count(*) " +
                "    from agenda a, (select inicio, fin " +
                "     from ciclo " +
                "     where inicio <= current_timestamp and fin >= current_timestamp) as ciclo " +
                "    where agenda_tipo_id = 0 and a.doctor_id = d.id and a.apm_id = apm.id and a.inicio >= ciclo.inicio and a.inicio <= ciclo.fin) > 0 ) " +
                "   or (:semaforo = 2 and ( select count(*) " +
                "    from agenda a, (select inicio, fin " +
                "     from ciclo " +
                "     where inicio <= current_timestamp and fin >= current_timestamp) as ciclo " +
                "    where agenda_tipo_id = 0 and a.doctor_id = d.id and a.apm_id = apm.id and a.inicio >= ciclo.inicio and a.inicio <= ciclo.fin) >= datos_visita.frecuencia)) " +
                "and (:idProvincia is null or p.id = :idProvincia) " +
                "and (:idLocalidad is null or l.id = :idLocalidad) " +
                "and (:idCiudad is null or c.id = :idCiudad) " +
                "and (:idCalle is null or calle.id = :idCalle) " +
                "and (:codigoPostal is null or codigo_postal.cpa like concat('%',:codigoPostal, '%')) " +
                "and (:turno is null or datos_visita.turno like concat('%',:turno, '%')) " +
                "and (:frecuencia is null or datos_visita.frecuencia = :frecuencia)";
        return query;
    }

    private void addParametersToCarteraMedicaQuery(Long idApm, String doctorNombre, String doctorApellido, Long subespecialidad, String matriculaNacional, String matriculaProvincial,
                                                   Long especialidadDoctorId, Long idGerente,
                                                   Integer semaforo, Long provinciaId, Long localidadId,
                                                   Long ciudadId, Long calleId, String codigoPostal, Integer turno, Integer frecuencia, NativeQuery query) {
        query.setParameter("primerApellido", doctorApellido);
        query.setParameter("primerNombre", doctorNombre);
        query.setParameter("idApm", idApm);
        query.setParameter("idGerenteRegional", idGerente);
        query.setParameter("matriculaNacional", matriculaNacional);
        query.setParameter("matriculaProvincial", matriculaProvincial);
        query.setParameter("idEspecialidad", especialidadDoctorId);
        query.setParameter("idSubespecialidad", subespecialidad);
        query.setParameter("semaforo", semaforo);
        query.setParameter("idProvincia", provinciaId);
        query.setParameter("idLocalidad", localidadId);
        query.setParameter("idCiudad", idApm);
        query.setParameter("idCalle", calleId);
        query.setParameter("idCiudad", ciudadId);
        query.setParameter("codigoPostal", codigoPostal);
        query.setParameter("turno", turno);
        query.setParameter("frecuencia", frecuencia);
    }
}
