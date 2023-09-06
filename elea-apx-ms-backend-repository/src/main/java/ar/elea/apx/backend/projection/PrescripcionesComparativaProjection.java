package ar.elea.apx.backend.projection;


/**
 * @author Felipe Jimenez
 * Proyección para ser utilizado como tipo de retorno en la consulta de prescripciones por trimestres
 */
public interface PrescripcionesComparativaProjection {
    String getCodMercado();
    String getCodRaiz();
    String getMercado();
    String getProducto();
    String getPeriodo();
    String getCategoria();
    String getCantidad();
    String getPorcentaje();
    String getCodigoLaboratorio();
    String getNombreLaboratorio();

}
