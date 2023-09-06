package ar.elea.apx.backend.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Date;

/**
 * @author Guillermo Nasi
 */
@JsonPropertyOrder(alphabetic = true)
public interface CarteraMedicaProjection {

    Long getId();

    Long getDoctorId();

    Long getApmId();

    String getDoctorApellido();

    String getDoctorNombre();

    String getMatriculaNacional();

    String getMatriculaProvincial();

    String getEnteEmisor();

    String getEspecialidadDoctor();

    String getEspecialidadVisita();

    Boolean getPrescripciones();

    String getProvinciaDireccion();

    String getLocalidadDireccion();

    String getCiudadDireccion();

    String getCalleDireccion();

    String getNumeroDireccion();

    String getApmApellido();

    String getApmNombre();

    Long getGerenteRegionalId();

    String getGerenteRegionalApellido();

    String getGerenteRegionalNombre();

    Integer getFrecuencia();

    Integer getTurno();

    Date getUltimaVisitaCartera();

    Integer getVisitasCiclo();

}