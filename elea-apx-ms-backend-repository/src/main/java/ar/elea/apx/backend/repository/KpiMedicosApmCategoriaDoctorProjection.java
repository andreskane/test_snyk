package ar.elea.apx.backend.repository;

public interface KpiMedicosApmCategoriaDoctorProjection {
    Long getApmId();
    String getNombre();
    String getApellidos();
    Integer getTotalMedicos();
    Integer getContactosTarget();
    Integer getTotalCategoriaDoctor();
    Long getCategoriaDoctorId();
    String getCategoriaDoctor();
    Integer getTotalMedicosCategoriaDoctor();
    byte[] getImagen();
    Long getGerenteId();
    Integer getTotalApms();
}
