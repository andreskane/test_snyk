package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.CatalogoProductos;
import ar.elea.apx.backend.projection.CatalogoProductoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CatalogoProductosRepository extends JpaRepository<CatalogoProductos, Long> {
    List<CatalogoProductos> getCatalogoProductosById(Long id);

    @Query(value = "select id, cod_sap as codSap, nombre, grupo_familia as grupoFamilia, " +
            "familia_producto as familiaProducto, " +
            "unidad_negocio as unidadNegocio, especialidad, precio, precio_vigencia as precioVigengia, " +
            "id_familia as idFamilia, activo, st.stock_disponible as cantidad " +
            "from catalogo_productos_view vi " +
            "left join stock_productos st on st.id_producto = vi.cod_sap " +
            "WHERE familia_producto = :nombreFamilia",
            nativeQuery = true)
    List<CatalogoProductoProjection> getCatalogoProductos(String nombreFamilia);
}
