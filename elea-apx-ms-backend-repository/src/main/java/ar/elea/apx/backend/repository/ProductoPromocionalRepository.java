package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.Grilla;
import ar.elea.apx.backend.projection.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alex Cucho
 */
@Repository
public interface ProductoPromocionalRepository extends JpaRepository<Grilla, Long> {

    @Query(value = "SELECT DISTINCT familia_producto.id AS idFamiliaProducto, " +
            "            familia_producto.nombre nombreFamilia " +
            "            FROM detalle_promocion_producto detalle " +
            "            JOIN grilla " +
            "            ON grilla.id = detalle.id_grilla " +
            "            JOIN linea ON linea.id = grilla.id_linea " +
            "            JOIN familia_producto ON familia_producto.id = detalle.id_familia_producto " +
            "            JOIN ciclo ON ciclo.id = detalle.id_ciclo " +
            "            WHERE (:cicloId is null OR ciclo.id = :cicloId) " +
            "            AND detalle.estado_ciclo = 'PUBLICADO' " +
            "            AND estado_grilla = 'ACTIVO'  " +
            "            ORDER BY " +
            "            nombreFamilia",
            nativeQuery = true)
    List<GrillasApmProjection> getGrillasByApmIdCicloGrilla(Long cicloId);


    @Query(value = "SELECT linea.id, "+
            "linea.nombre "+
            "FROM linea_apm JOIN linea ON linea.id = linea_apm.id_linea " +
            "WHERE linea_apm.id_apm = :apmId", nativeQuery = true)
    List<LineaConGrillasProjection> getLineaConGrillasProjectionByApmId(Long apmId);

    @Query(value = "SELECT DISTINCT grilla.id, grilla.nombre_grilla as nombre, grilla.abreviatura " +
            "FROM grilla JOIN linea ON linea.id = grilla.id_linea " +
            "JOIN detalle_promocion_producto d ON d.id_grilla = grilla.id " +
            "WHERE " +
            "d.estado_ciclo = 'PUBLICADO' AND " +
            "d.estado_grilla = 'ACTIVO' AND " +
            "d.id_ciclo = :idCiclo AND " +
            "linea.id = :lineaId", nativeQuery = true)
    List<GrillaProjection> getGrillasProjectionByLineaId(Long lineaId, Long idCiclo);

    @Query(value = "SELECT categoria_promocion.id, categoria_promocion.nombre_categoria as nombre FROM categoria_promocion WHERE id > -1 order by categoria_promocion.orden asc;", nativeQuery = true)
    List<CategoriaGrillaProjection> getCategoriaGrillas();

    @Query(value = "SELECT DISTINCT familia_producto.id, " +
            "familia_producto.nombre " +
            "FROM detalle_promocion_producto detalle " +
            "JOIN grilla " +
            "ON grilla.id = detalle.id_grilla " +
            "JOIN linea ON linea.id = grilla.id_linea " +
            "JOIN familia_producto ON familia_producto.id = detalle.id_familia_producto " +
            "JOIN ciclo ON ciclo.id = detalle.id_ciclo " +
            "JOIN categoria_promocion " +
            "ON categoria_promocion.id = detalle.id_categoria_promocion " +
            "WHERE (:lineaId is null OR linea.id = :lineaId) " +
            "AND (:grillaId is null OR grilla.id = :grillaId) " +
            "AND (:categoriaId is null OR categoria_promocion.id = :categoriaId) " +
            "AND (:cicloId is null OR ciclo.id = :cicloId) " +
            "AND detalle.estado_ciclo = 'PUBLICADO' " +
            "AND detalle.estado_grilla = 'ACTIVO' " +
            "ORDER BY " +
            "nombre",
            nativeQuery = true)
    List<ProductoGrillaProjection> getProductosByApmIdLineaIdGrillaIdCategoriaIdCicloId(Long lineaId, Long grillaId, Long categoriaId, Long cicloId);

}