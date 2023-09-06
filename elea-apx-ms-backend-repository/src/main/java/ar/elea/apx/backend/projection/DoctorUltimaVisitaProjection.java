package ar.elea.apx.backend.projection;

/**
 * @author Christian Corrales
 */
public interface DoctorUltimaVisitaProjection {
    Long getId();
    String getNombre();
    String getApellido();
    String getMatriculaNacional();
    String getMatriculaProvincial();
    String getEspecialidad();
    String getSubEspecialidad();
    String getUltimaVisita();
    String getAsientoGira();
    String getResidente();
    String getJefeServicio();
    String getInactivo();
}
