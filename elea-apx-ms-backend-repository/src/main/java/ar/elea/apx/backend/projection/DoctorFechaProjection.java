package ar.elea.apx.backend.projection;

import java.time.LocalDate;

/**
 * @author Christian Corrales
 */
public interface DoctorFechaProjection {
    Long getidDoctor();
    LocalDate getFechaCalificacion();
}
