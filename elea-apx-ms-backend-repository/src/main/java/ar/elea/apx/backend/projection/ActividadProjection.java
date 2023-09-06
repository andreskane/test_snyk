package ar.elea.apx.backend.projection;

import ar.elea.apx.backend.entity.TurnoEnum;

import java.util.Date;

/**
 * @author Guillermo Nasi
 */
public interface ActividadProjection {
    String getGerenteRegionalApellido();
    String getGerenteRegionalNombre();
    String getApmApellido();
    String getApmNombre();
    String getDoctorApellido();
    String getDoctorNombre();
    Integer getFrecuencia();
    Date getFechaInicio();
    Date getFechaFin();
    Boolean getVisitaExitosa();
    Integer getTipoAgendaId();
    String getTipoAgendaNombre();
    String getMotivo();
    String getTipo();
    TurnoEnum getTurno();
    String getInstitucion();
    Date getConexion();
    Integer getPorcentajeTurnoManana();
    Integer getPorcentajeTurnoTarde();

}
