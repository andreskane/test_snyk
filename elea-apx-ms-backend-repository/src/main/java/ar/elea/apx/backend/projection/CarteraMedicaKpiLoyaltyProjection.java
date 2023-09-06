package ar.elea.apx.backend.projection;

/**
 * @author Christian Corrales
 */
public interface CarteraMedicaKpiLoyaltyProjection {
    Long getGerenteId();
    Long getApmId();
    Integer getTotalMedicos();
    String getPrimerNombreApm();
    String getPrimerApellidoApm();
    Integer getContactosTarget();
    byte[] getImagen();
}
