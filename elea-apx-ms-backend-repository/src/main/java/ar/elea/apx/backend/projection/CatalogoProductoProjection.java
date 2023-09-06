package ar.elea.apx.backend.projection;


import java.util.Date;

/**
 * @author Christian Corrales
 */
public interface CatalogoProductoProjection {
    Long getId();
    Long getCodSap();
    String getNombre();
    String getEspecialidad();
    Boolean getActivo();
    Double getPrecio();
    Long getIdFamilia();
    String getFamiliaProducto();
    String getGrupoFamilia();
    String getUnidadNegocio();
    Date getPrecioVigengia();
    Integer getCantidad();
}
