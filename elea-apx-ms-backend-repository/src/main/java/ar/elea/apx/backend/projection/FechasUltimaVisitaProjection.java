package ar.elea.apx.backend.projection;

import java.time.LocalDateTime;

/**
 * @author Christian Corrales
 */
public interface FechasUltimaVisitaProjection {
    Long getDoctorId();
    LocalDateTime getFechaUltimaVisita();
}
