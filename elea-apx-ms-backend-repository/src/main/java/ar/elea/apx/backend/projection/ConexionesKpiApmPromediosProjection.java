package ar.elea.apx.backend.projection;

/**
 * @author Diego Luis Hernandez Angulo
 * Proyección para ser utilizado en el reporte de conexiones diarias apm
 */

public interface ConexionesKpiApmPromediosProjection {
    long getApmId();
    String getApellidos();
    String getNombres();
    int getDia();
    int getCantidad();
    int getDiasHabilesMes();
    int getConexion();
    int getTotalConexionesMes();
    String getPromedio();
    int getDiasMes();
    Long getGerenteRegionalId();
}
