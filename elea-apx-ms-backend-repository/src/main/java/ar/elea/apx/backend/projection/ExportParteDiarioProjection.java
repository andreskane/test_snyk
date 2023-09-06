package ar.elea.apx.backend.projection;

/**
 * @author Guillermo Nasi
 */
public interface ExportParteDiarioProjection {
    Long getId();
    Long getLegajo();
    String getApmNombre();
    String getApmApellido();
    String getGerenteNombre();
    String getGerenteApellido();
    String getMotivo();
    String getFecha();
    String getTurno();
    Integer getPorcentaje();
    String getConexion();
}
