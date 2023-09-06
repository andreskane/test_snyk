package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.projection.ConexionesProjection;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

/**
 * @author Guillermo Nasi
 */
@Repository
public class ApmCustomRepository {
    private final EntityManager entityManager;

    public ApmCustomRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<ConexionesProjection> getConexiones(String nombre, String apellido, Date fechaInicio, Date fechaFin, List<Long> apms, List<Long> gerentes) {
        String strQuery = "select " +
                "       apm.id                                       as apmId, " +
                "       apm.legajo                                   as apmLegajo, " +
                "       apm.primerApellido + ', ' + apm.primerNombre as apmNombre, " +
                "       u.apellido + ', ' + u.nombre                 as gerenteNombre, " +
                "       conexiones.fecha                             as fecha, " +
                "       conexiones.conexiones                        as cantidadConexiones " +
                "from apm " +
                "         left join usuario u on apm.gerente_regional_id = u.id " +
                "         left join (select capm.id_apm              as idApm, " +
                "                      CAST(capm.fecha as DATE) as fecha, " +
                "                      count(*)                 as conexiones " +
                "               from conexion_apm capm " +
                "               group by capm.id_apm, CAST(capm.fecha as DATE)) conexiones on apm.id = conexiones.idApm " +
                "where (:nombre is null or apm.primerNombre like concat(:nombre, '%')) " +
                "  and (:apellido is null or apm.primerApellido like concat(:apellido, '%')) " +
                "  and (:fechaInicio is null or (CAST(conexiones.fecha as DATE) >= :fechaInicio or CAST(conexiones.fecha as DATE) is null)) " +
                "  and (:fechaFin is null or (CAST(conexiones.fecha as DATE) <= :fechaFin or CAST(conexiones.fecha as DATE) is null)) " +
                "  and apm.inactivo = 0";

                if(!CollectionUtils.isEmpty(apms))
                    strQuery = strQuery + "  and (apm.id in (" + StringUtils.join(apms, ",") + "))";

                if(!CollectionUtils.isEmpty(gerentes))
                    strQuery = strQuery + "  and (apm.gerente_regional_id in (" + StringUtils.join(gerentes, ",") + "))";

                strQuery = strQuery + " order by IIF(fecha is null, 1, 0), fecha";


        Session session = entityManager.unwrap(Session.class);
        NativeQuery<ConexionesProjection> query = session.createNativeQuery(strQuery, "ConexionesMapping");

        query.setParameter("nombre", nombre);
        query.setParameter("apellido", apellido);
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);

        return query.list();
    }
}
