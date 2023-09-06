package ar.elea.apx.backend.projection;

/**
 * @author Christian Corrales
 */
public interface HistoricoEnvioMaterialProjection {
    Long getId();
    Long getIdMaterial();
    String getNombreMaterial();
    String getDoctor();
    String getFechaEnvio();
    String getLinea();
    String getProducto();
    String getEspecialidad();
    String getTipo();
    String getAsunto();
    String getMensaje();
}
