package ar.elea.apx.backend.projection;

/**
 * @author Christian Corrales
 */
public interface KpiMedicosApmProjection {
    Long getDoctorId();
    String getNombreMedico();
    String getApellidoMEdico();
    String getEspecialidad();
    Integer getContactosTarget();
    Integer getTotalApms();
    Integer getTotalVisitas();
    Double getCobertura();
    byte[] getImagen();
}
