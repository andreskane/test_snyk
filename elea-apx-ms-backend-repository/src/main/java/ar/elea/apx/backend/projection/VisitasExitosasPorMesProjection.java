package ar.elea.apx.backend.projection;

/**
 * @author Felipe Jimenez
 * @author Guillermo Nasi
 * Proyección para ser utilizado como tipo de retorno en la consulta de visitas por mes
 */
public interface VisitasExitosasPorMesProjection {
    Long getApmId();
    String getApmNombre();
    String getApmApellido();
    String getGerenteNombre();
    String getGerenteApellido();
    Long getDoctores();
    Long getPresenciales();
    Long getVirtuales();
    Long getFrecuencias();

}
