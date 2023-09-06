package ar.elea.apx.backend.projection;

/**
 * @author Christian Corrales
 */
public interface KpiMedicosApmEspecialidadProjection {
    Long getApmId();
    String getNombre();
    String getApellidos();
    Integer getTotalMedicos();
    Integer getContactosTarget();
    Integer getTotalEspecialidad();
    Long getEspecialidadId();
    String getEspecialidad();
    Integer getTotalMedicosEspecialidad();
    byte[] getImagen();
    Long getGerenteId();
    Integer getTotalApms();
}
