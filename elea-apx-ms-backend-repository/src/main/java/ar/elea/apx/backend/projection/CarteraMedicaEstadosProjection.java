package ar.elea.apx.backend.projection;

import java.time.LocalDateTime;

/**
 * @author Globant
 */
public interface CarteraMedicaEstadosProjection {
    Long getId();
    String getApellidoDoctor();
    String getNombreDoctor();
    String getEspecialidad();
    String getTipo();
    String getLugar();
    String getEstado();
    LocalDateTime getFechaSolicitud();
    Long getApmId();
    Long getDoctorId();
    Long getGerenteId();
    Long getAprobadorId();
    LocalDateTime getFechaConfirmacion();
    String getComentarios();
}
