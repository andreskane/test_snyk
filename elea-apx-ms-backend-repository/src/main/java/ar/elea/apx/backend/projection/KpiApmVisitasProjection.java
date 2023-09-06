package ar.elea.apx.backend.projection;

import java.util.Date;

/**
 * @author Christian Corrales
 */
public interface KpiApmVisitasProjection {
    Long getId();
    String getNombre();
    String getApellido();
    Integer getVisitas();
    Date getFechaInicio();
    Integer getFrecuencia();
}
