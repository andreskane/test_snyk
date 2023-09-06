package ar.elea.apx.backend.projection;

import java.util.Date;

/**
 * @author Diego Luis Hernandez
 * Proyección para ser utilizado en el list de doctores
 */

public interface DoctoresProjection {
    Long getId();
    String getPrimerNombre();
    String getSegundoNombre();
    String getPrimerApellido();
    String getSegundoApellido();
    Date getFechaNacimiento();
    String getEmail();
    String getTelefono();
    String getSexo();
    String getMatriculaNacional();
    String getMatriculaProvincial();
    String getISO_entidadMatricula();
    String getEntidadMatriculaProvincial();
    Integer getEspecialidadId();
    boolean getInactivo();
    boolean getDoctorApp();
    Date getInicio();
    String getEspecialidad();
    String getSubEspecialidad();
    String getUltimaVisita();
    String getJefeServicio();
    String getResidente();
    String getEstancia();
}
