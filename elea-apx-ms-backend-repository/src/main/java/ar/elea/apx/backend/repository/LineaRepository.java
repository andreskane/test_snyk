package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.Linea;
import ar.elea.apx.backend.projection.KpiLineasApmProjection;
import ar.elea.apx.backend.projection.LineaConGrillasProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Juan Cruz Rompani
 */


public interface LineaRepository extends JpaRepository<Linea, Long> {

    @Query(value = "select COUNT(distinct a.id)  as totalLineasAgrupadasApm, l.nombre " +
            "from linea_apm la " +
            "join linea l on l.id=la.id_linea " +
            "join apm a on a.id=la.id_apm " +
            "join usuario u on u.id=a.gerente_regional_id  " +
            "where a.inactivo =0 " +
            "and u.inactivo =0 " +
            "and u.rol='GERENTE_REGIONAL' " +
            "group by l.nombre  ", nativeQuery = true)
    List<KpiLineasApmProjection> getListLineasApmAgrupadas();

    @Query(value = "SELECT COUNT( DISTINCT la.id_apm) AS totalLineasApm " +
            "FROM linea_apm la " +
            "JOIN linea l ON l.id = la.id_linea " +
            "JOIN apm a ON a.id = la.id_apm " +
            "JOIN usuario u ON u.id = a.gerente_regional_id " +
            "WHERE a.inactivo = 0 " +
            "AND u.inactivo = 0 " +
            "AND u.rol = 'GERENTE_REGIONAL';", nativeQuery = true)
    KpiLineasApmProjection getTotalLineasApms();

    @Query(value = "SELECT  id_linea as id,  " +
            "        nombre_linea as nombre,  " +
            "        id_grilla as idGrilla,  " +
            "        nombre_grilla as nombreGrilla, " +
            "        mes,  " +
            "        mes_ano as mesAno,  " +
            "        ano,  " +
            "       estado_grilla as estadoGrilla," +
            "       abreviatura," +
            "       estado_ciclo as estadoCiclo " +
            "FROM ( " +
            "  SELECT  DISTINCT  " +
            "    linea.id AS id_linea,  " +
            "          linea.nombre AS nombre_linea,  " +
            "          grilla.id AS id_grilla,  " +
            "          grilla.nombre_grilla, " +
            "          calendario.mes,  " +
            "          calendario.mes_ano,  " +
            "          calendario.ano," +
            "          detalle.estado_grilla," +
            "          grilla.abreviatura," +
            "          detalle.estado_ciclo " +
            "  FROM    detalle_promocion_producto detalle " +
            "  LEFT JOIN   familia_producto familia  " +
            "  ON      familia.id = detalle.id_familia_producto  " +
            "  JOIN    grilla  " +
            "  ON      grilla.id = detalle.id_grilla  " +
            "  JOIN  categoria_promocion categoria  " +
            "  ON   categoria.id = detalle.id_categoria_promocion  " +
            "  JOIN    linea  " +
            "  ON      linea.id = grilla.id_linea  " +
            "  JOIN    ciclo  " +
            "  ON      ciclo.id = detalle.id_ciclo  " +
            "  JOIN    calendario  " +
            "  ON      calendario.fecha = ciclo.inicio " +
            "  WHERE  calendario.ano = :ano and " +
            " linea.id = :idLinea and " +
            " (detalle.estado_grilla <> 'ELIMINADO' or detalle.estado_grilla is null) " +
            ") tabla  " +
            "ORDER BY  " +
            "        nombre_linea,  " +
            "        nombre_grilla,  " +
            "        ano,  " +
            "        mes ",
            nativeQuery = true)
    List<LineaConGrillasProjection> getGrillasLineasAdmin(Long ano,Long idLinea);

    @Query(value = "select l from linea l where l.id = :idLinea ")
    Linea getLineaById(Long idLinea);

}
