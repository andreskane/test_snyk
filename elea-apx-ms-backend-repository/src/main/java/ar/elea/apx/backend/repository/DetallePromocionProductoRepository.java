package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.DetallePromocionProducto;
import ar.elea.apx.backend.projection.DetallePromocionProductoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * @author Diego Luis Hernandez
 */
@Repository
public interface DetallePromocionProductoRepository extends JpaRepository<DetallePromocionProducto, Long> {

    @Query(value = "select d from detalle_promocion_producto d where d.grilla.id=:idGrilla and d.ciclo.id = :idCiclo ")
    List<DetallePromocionProducto> findAllDetallePromocionIdGrilla(Long idGrilla,Long idCiclo);

    @Modifying
    @Query(value = "update detalle_promocion_producto set estado_grilla=:estado where id_grilla = :idGrilla and id = :idDetalle",nativeQuery = true)
    void updateEstadoGrilla(String estado,Long idGrilla, Long idDetalle);

    @Query(value = "select distinct RIGHT(YEAR(c.inicio), 4) anio from detalle_promocion_producto deta JOIN ciclo c ON c.id = deta.id_ciclo " +
            "where estado_grilla <> 'ELIMINADO' order by 1",nativeQuery = true)
    List<DetallePromocionProductoProjection> getFechasCiclosGrillas();

    @Query(value = "select d from detalle_promocion_producto d where d.grilla.id=:idGrilla and " +
            "d.estadoGrilla in ('ACTIVO') and " +
            "d.ciclo.nombre like %:anio% ")
    List<DetallePromocionProducto> findAllDetallePromocionIdGrillaAnio(Long idGrilla,Long anio);

    @Modifying
    @Query(value = "update detalle_promocion_producto set id_familia_producto = :idProducto, estado_ciclo=:estado " +
            "where id_grilla = :idGrilla and " +
            "id_categoria_promocion = :idCategoria and " +
            "id_ciclo = :idCiclo", nativeQuery = true)
    void updateContenidoGrilla(Long idProducto, Long idGrilla, Long idCategoria, Long idCiclo, String estado);

    @Modifying
    @Query(value = "update detalle_promocion_producto set id_familia_producto = :idProducto, estado_ciclo=:estado " +
            "where id = :idDetallePromocionProducto ", nativeQuery = true)
    void updateContenidoGrillaById(Long idDetallePromocionProducto, Long idProducto, String estado);

    @Modifying
    @Query(value = "delete from detalle_promocion_producto where id = :idDetallePromocionProducto ", nativeQuery = true)
    void deleteContenidoGrillaById(Long idDetallePromocionProducto);

    @Query(value = "select d from detalle_promocion_producto d where d.grilla.id=:idGrilla and " +
            "d.ciclo.id = :idCiclo and " +
            "d.categoriaPromocion.id = :idCategoria and " +
            "d.familiaProducto is null and " +
            "d.estadoGrilla in ('ACTIVO') ")
    Optional<DetallePromocionProducto> findDetalleProductoFamiliaEsNull(Long idGrilla, Long idCategoria, Long idCiclo);

    @Modifying
    @Query(value = "INSERT INTO detalle_promocion_producto (id_familia_producto, id_grilla, id_categoria_promocion, " +
            "id_ciclo, valor, estado_ciclo, estado_grilla) VALUES (:idProducto,:idGrilla,:idCategoria, :idCiclo,0,'PENDIENTE','ACTIVO')", nativeQuery = true)
    void saveContenidoGrilla(Long idProducto, Long idGrilla, Long idCategoria, Long idCiclo);

    @Modifying
    @Query(value = "delete from detalle_promocion_producto where id_familia_producto = :idProducto and " +
            "id_grilla = :idGrilla and " +
            "id_categoria_promocion = :idCategoria and  " +
            "id_ciclo = :idCiclo ", nativeQuery = true)
    void deleteContenidoGrilla(Long idProducto, Long idGrilla, Long idCategoria, Long idCiclo);

    @Query(value = "select d from detalle_promocion_producto d where d.grilla.id=:idGrilla and " +
            "d.ciclo.id = :idCiclo and " +
            "d.categoriaPromocion.id = :idCategoria and " +
            "d.estadoGrilla in ('ACTIVO') ")
    List<DetallePromocionProducto> findDetalleProductoFamilia(Long idGrilla, Long idCategoria, Long idCiclo);

    @Modifying
    @Query(value = "update detalle_promocion_producto set estado_ciclo = :estadoCiclo " +
            "where id_grilla = :idGrilla and " +
            "id_ciclo = :idCiclo", nativeQuery = true)
    void updateContenidoGrillaEstado(String estadoCiclo, Long idGrilla, Long idCiclo);

    @Query(value = "select d from detalle_promocion_producto d where d.grilla.id=:idGrilla and " +
            "d.ciclo.id = :idCiclo and " +
            "d.categoriaPromocion.id = :idCategoria and " +
            "d.familiaProducto.id = :idProducto ")
    DetallePromocionProducto findDetalleProductoFamiliaExistente(Long idGrilla, Long idCategoria, Long idCiclo, Long idProducto);

    @Query(value = "select max(d.categoriaPromocion.id) as categoriaMax from detalle_promocion_producto d where d.grilla.id=:idGrilla ")
    DetallePromocionProductoProjection findCantidadMaximaCategoria(Long idGrilla);

    @Query(value = " select d from detalle_promocion_producto d where d.grilla.id = :idGrilla and " +
            "d.ciclo.id = :idCiclo and " +
            "d.familiaProducto is null and " +
            "d.estadoCiclo = 'PENDIENTE' ")
    List<DetallePromocionProducto> findCategoriasConProductos(Long idGrilla,Long idCiclo);

    @Query(value = " select d from detalle_promocion_producto d where d.grilla.id = :idGrilla and " +
            "d.ciclo.id = :idCiclo and " +
            "d.estadoCiclo = 'PENDIENTE' ")
    List<DetallePromocionProducto> findCategoriasConProductosIdGrilla(Long idGrilla,Long idCiclo);

    @Modifying
    @Query(value = "update detalle_promocion_producto set id_familia_producto = :idProducto, estado_ciclo=:estado " +
            "where id_grilla = :idGrilla and " +
            "id_ciclo = :idCiclo ", nativeQuery = true)
    void updateContenidoGrillaIncompleto(Long idProducto, Long idGrilla, Long idCiclo, String estado);

    @Modifying
    @Query(value = "update detalle_promocion_producto set id_familia_producto = :idProducto, estado_ciclo = 'PENDIENTE' " +
            "where id = :id ", nativeQuery = true)
    void updateContenidoGrillaProductoById(Long id, Long idProducto);

    @Query(value = "select d from detalle_promocion_producto d where d.grilla.id=:idGrilla and " +
            "d.ciclo.id = :idCiclo and " +
            "d.familiaProducto.id = :idProducto ")
    List<DetallePromocionProducto> findDetalleProductoFamiliaExistente(Long idGrilla, Long idProducto, Long idCiclo);
}
