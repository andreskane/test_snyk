package ar.elea.apx.backend.projection;

/**
 * @author Diego Luis Hernandez
 * Proyección para ser utilizado en el reporte de conexiones diarias gerente
 */

public interface ConexionesKpiGerenteProjection {
    Long getIdGerente();
    String getNombreGerente();
    String getApellidoGerente();
    Integer getTotalConexionesMes();
}
