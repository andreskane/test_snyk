package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.ConexionGerente;
import ar.elea.apx.backend.projection.ConexionesKpiGerenteProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * @author Diego Luis Hernandez
 */
@Repository
public interface ConexionGerenteRepository extends JpaRepository<ConexionGerente, Long> {


    @Query(value =
            "with conexiones_gerente_ciclo as ( select ca.id_gerente as id_gerente, " +
                    "COUNT(DISTINCT(FORMAT(ca.fecha, 'yyyy-MM-dd'))) conexiones  " +
                    "from conexion_gerente ca  " +
                    "where ca.fecha > ( select  " +
                    "c.inicio from ciclo c  " +
                    "where c.id = :cicloId)  " +
                    " and ca.fecha < ( " +
                    " select  " +
                    " c.fin  " +
                    " from ciclo c " +
                    " where c.id = :cicloId)  " +
                    " group by ca.id_gerente)  " +
                    " select  " +
                    "conexiones_gerente_ciclo.id_gerente as idGerente, " +
                    "conexiones_gerente_ciclo.conexiones as totalConexionesMes, " +
                    "u.nombre as nombreGerente, " +
                    "u.apellido as apellidoGerente " +
                    " from conexiones_gerente_ciclo  " +
                    " inner join usuario u on u.id = conexiones_gerente_ciclo.id_gerente  " +
                    "where (:gerenteRegionalId is null or u.id = :gerenteRegionalId)  " +
                    " and u.inactivo = 0  " +
                    "order by conexiones_gerente_ciclo.conexiones",
            nativeQuery = true)
    List<ConexionesKpiGerenteProjection> findKpiConexionesByGerenteByCiclo(@Param("gerenteRegionalId") Long gerenteRegionalId,
                                                                                      @Param("cicloId") Long cicloId);

    List<ConexionGerente> findDistinctByIdGerenteAndFechaBetween(Long gerenteId, Date startDate, Date endDate);
}
