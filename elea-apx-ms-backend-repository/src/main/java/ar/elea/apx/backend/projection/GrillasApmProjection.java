package ar.elea.apx.backend.projection;

/**
 * @author Christian Corrales
 */
public interface GrillasApmProjection {
    Long getIdApm();
    String getPrimerApellido();
    String getSegundoApellido();
    String getPrimerNombre();
    String getSegundoNombre();
    Long getIdLinea();
    String getNombreLinea();
    Long getIdGrilla();
    String getAbreviatura();
    String getNombreGrilla();
    Long getIdCategoriaPromocion();
    String getNombreCategoria();
    Integer getAnno();
    Integer getTrimestre();
    String getNombreTrimestre();
    Integer getMes();
    String getMesAnno();
    Integer getFila();
    String getNombreFamilia();
    Double getValor();
    Long getIdFamiliaProducto();
}
