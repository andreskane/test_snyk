package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.ConexionApm;
import ar.elea.apx.backend.projection.ConexionesKpiProjection;
import ar.elea.apx.backend.projection.ConexionesProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Guillermo Nasi
 */
@Repository
public interface ConexionApmRepository extends JpaRepository<ConexionApm, Long> {

    @Query(value=
            "with conexiones_apm_ciclo as (select ca.id_apm as apm_id, count(ca.id_apm) conexiones, max(ca.fecha) as fecha " +
            "from conexion_apm ca " +
            "where (:apmId is null or ca.id_apm = :apmId) " +
            "and ca.fecha > (select c.inicio from ciclo c where c.id = :cicloId) " +
            "and ca.fecha < (select c.fin from ciclo c where c.id = :cicloId) " +
            "group by ca.id_apm) " +
            "select " +
                    "conexiones_apm_ciclo.apm_id,  " +
                    "conexiones_apm_ciclo.conexiones, " +
                    "apm.legajo as legajoApm, " +
                    "apm.primerApellido as apellidoApm, " +
                    "apm.primerNombre as nombreApm, " +
                    "u.apellido as nombreGerente, " +
                    "u.nombre as apellidoGerente, " +
                    "conexiones_apm_ciclo.fecha as fechaUltimaConexion " +
            "from conexiones_apm_ciclo " +
            "inner join apm on apm.id = conexiones_apm_ciclo.apm_id " +
            "inner join usuario u on u.id = apm.gerente_regional_id " +
            "where (:gerenteRegionalId is null or u.id = :gerenteRegionalId)"
            , nativeQuery = true )
    List<ConexionesProjection> findConexionesPorCiclo(
            @Param("gerenteRegionalId") Long gerenteRegionalId,
            @Param("apmId") Long apmId,
            @Param("cicloId") Long cicloId);



    @Query(value =
    "with conexiones_apm_ciclo as ( select ca.id_apm as apm_id," +
            "COUNT(DISTINCT(FORMAT(ca.fecha, 'yyyy-MM-dd'))) conexiones " +
            "from conexion_apm ca " +
            "where ca.fecha > ( select " +
            "c.inicio from ciclo c " +
            "where c.id = :cicloId) " +
            " and ca.fecha < (" +
            " select " +
            " c.fin " +
            " from ciclo c" +
            " where c.id = :cicloId) " +
            " group by ca.id_apm) " +
            " select " +
            "conexiones_apm_ciclo.apm_id as apmId,"+
            "conexiones_apm_ciclo.conexiones as totalConexionesMes,"+
            "apm.primerApellido as primerApellidoApm,"+
            "apm.segundoApellido as segundoApellidoApm,"+
            "apm.primerNombre as primerNombreApm,"+
            "apm.segundoNombre as segundoNombreApm,"+
            "u.apellido as nombreGerente,"+
            "u.nombre as apellidoGerente, "+
            "apm.profile_image as profileImage" +
            " from conexiones_apm_ciclo " +
            " inner join apm on " +
            " apm.id = conexiones_apm_ciclo.apm_id " +
            " inner join usuario u on " +
            " u.id = apm.gerente_regional_id " +
            "where (:gerenteRegionalId is null or u.id = :gerenteRegionalId) " +
            " and u.inactivo = 0 " +
            "order by conexiones_apm_ciclo.conexiones",
    nativeQuery = true)
    List<ConexionesKpiProjection> findKpiConexionesByGerenteByCiclo(@Param("gerenteRegionalId") Long gerenteRegionalId,
                                                                    @Param("cicloId") Long cicloId);
}
