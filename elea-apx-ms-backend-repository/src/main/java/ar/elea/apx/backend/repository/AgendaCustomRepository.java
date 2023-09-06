package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.Agenda;
import ar.elea.apx.backend.projection.VisitasExitosasPorMesImpl;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Guillermo Nasi
 */
@Repository
public class AgendaCustomRepository {
    private final EntityManager entityManager;

    public AgendaCustomRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<VisitasExitosasPorMesImpl> findVisitasExitosasMes(String nombreApm, String apellidoApm,
                                                                  String fechaInicio, String fechaFin,
                                                                  List<Long> apms, List<Long> gerentes) {
        String strQuery =
                "select distinct " +
                "       apm.id                              as apmId, " +
                "       apm.primerNombre             as apmNombre, " +
                "       apm.primerApellido           as apmApellido, " +
                "       u.nombre                     as gerenteNombre, " +
                "       u.apellido                   as gerenteApellido, " +
                "       (select count(distinct doctor_id) " +
                "        from agenda a " +
                "        where a.inactivo = 0 " +
                "          and a.visita_exitosa = 1 " +
                "          and a.apm_id = apm.id " +
                "          and (:fechaInicio is null or a.inicio >= :fechaInicio) " +
                "          and (:fechaFin is null or a.inicio <= :fechaFin)) as doctores, " +
                "        (select count(*) " +
                "         from agenda a " +
                "         where a.inactivo = 0 " +
                "           and a.visita_exitosa = 1 " +
                "           and a.apm_id = apm.id " +
                "           and a.visita_tipo like 'PRESENCIAL' " +
                "           and (:fechaInicio is null or a.inicio >= :fechaInicio) " +
                "           and (:fechaFin is null or a.inicio <= :fechaFin)) as presenciales, " +
                "        (select count(*) " +
                "         from agenda a " +
                "         where a.inactivo = 0 " +
                "           and a.visita_exitosa = 1 " +
                "           and a.apm_id = apm.id " +
                "           and a.visita_tipo like 'VIRTUAL' " +
                "           and (:fechaInicio is null or a.inicio >= :fechaInicio) " +
                "           and (:fechaFin is null or a.inicio <= :fechaFin)) as virtuales, " +
                "        (select sum(dv.frecuencia) " +
                "         from cartera_medica cm " +
                "                  left join datos_visita dv on cm.datos_visita_id = dv.id " +
                "         where apm_id = apm.id) as frecuencias " +
                "from agenda a " +
                "         left join apm on a.apm_id = apm.id " +
                "         left join usuario u on apm.gerente_regional_id = u.id " +
                "where a.inactivo = 0 " +
                "  and a.visita_exitosa = 1 " +
                "  and (:fechaInicio is null or a.inicio >= :fechaInicio) " +
                "  and (:fechaFin is null or a.inicio <= :fechaFin) ";

        if(StringUtils.isNotEmpty(nombreApm)) {
            strQuery = strQuery + "  and (:nombreApm is null or apm.primerNombre like concat(:nombreApm, '%')) ";
        }

        if(StringUtils.isNotEmpty(apellidoApm)) {
            strQuery = strQuery + "  and (:apellidoApm is null or apm.primerApellido like concat(:apellidoApm, '%')) ";
        }

        if(apms != null && !apms.isEmpty()) {
            strQuery = strQuery + "  and (a.apm_id in (" + StringUtils.join(apms, ",") + ")) ";
        }

        if(gerentes != null && !gerentes.isEmpty()) {
            strQuery = strQuery + "  and (u.id in (" + StringUtils.join(gerentes, ",") + ")) ";
        }

        Session session = entityManager.unwrap(Session.class);

        NativeQuery<VisitasExitosasPorMesImpl> query = session.createNativeQuery(strQuery, "VisitasExitosasMapping");

        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        if(StringUtils.isNotEmpty(nombreApm))
            query.setParameter("nombreApm", nombreApm);
        if(StringUtils.isNotEmpty(apellidoApm))
            query.setParameter("apellidoApm", apellidoApm);

        return query.list();
    }

    public List<Agenda> findParteDiarioByCriteria(String nombre, String apellido, Long cicloId,
                                                  List<Long> apms, List<Long> gerentes) {

        String strQuery = "select a.* " +
                "from agenda a " +
                "inner join apm on apm.id = a.apm_id " +
                "inner join usuario u on u.id = apm.gerente_regional_id " +
                "where a.agenda_tipo_id = (select at.id from agenda_tipo at where at.id = 1) " +
                "and a.inactivo = 0 " +
                "and a.inicio >= (select c.inicio from ciclo c where c.id = :cicloId) " +
                "and a.inicio <= (select c.fin from ciclo c where c.id = :cicloId) " +
                "and (:nombre is null or apm.primerNombre like concat(:nombre,'%')) " +
                "and (:apellido is null or apm.primerApellido like concat(:apellido,'%'))";


        if(!CollectionUtils.isEmpty(gerentes))
            strQuery = strQuery + " and (u.id in (" + StringUtils.join(gerentes, ",") + ")) ";

        if(!CollectionUtils.isEmpty(apms))
            strQuery = strQuery + " and (a.apm_id in (" + StringUtils.join(apms, ",") + ")) ";

        strQuery = strQuery + "order by apm.id, a.inicio";


        Session session = entityManager.unwrap(Session.class);

        NativeQuery<Agenda> query = session.createNativeQuery(strQuery, Agenda.class);

        query.setParameter("cicloId", cicloId);

        query.setParameter("nombre", nombre);
        query.setParameter("apellido", apellido);

        return query.list();
    }

}
