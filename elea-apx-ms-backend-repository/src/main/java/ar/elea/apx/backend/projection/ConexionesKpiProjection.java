package ar.elea.apx.backend.projection;

import java.util.Date;

/**
 * @author Christian Corrales
 * Proyección para ser utilizado en el reporte de conexiones diarias
 */

public interface ConexionesKpiProjection {
    Long getApmId();
    Date getFecha();
    String getPrimerApellidoApm();
    String getSegundoApellidoApm();
    String getPrimerNombreApm();
    String getSegundoNombreApm();
    String getNombreGerente();
    String getApellidoGerente();
    Integer getTotalConexionesMes();
    byte[] getProfileImage();
}
