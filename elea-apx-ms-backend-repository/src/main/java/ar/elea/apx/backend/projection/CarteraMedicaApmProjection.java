package ar.elea.apx.backend.projection;

/**
 * @author Christian Corrales
 */
public interface CarteraMedicaApmProjection {
    Long getGerenteId();
    Long getApmId();
    Integer getTotalDoctores();
    String getDoctoresId();
    Integer getContactosObjetivos();
}
