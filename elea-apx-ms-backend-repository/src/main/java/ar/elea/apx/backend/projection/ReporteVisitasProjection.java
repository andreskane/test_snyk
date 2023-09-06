package ar.elea.apx.backend.projection;

import java.time.LocalDateTime;

/**
 * @author Christian Corrales
 */
public interface ReporteVisitasProjection {
    Long getCodigoMed();
    Long getCodCartera();
    String getSupervisor();
    String getApellidoApm();
    String getNombreApm();
    Long getLegajoApm();
    String getCiclo();
    String getFechaInicio();
    String getFechaFin();
    String getTurno();
    String getNombreMedico();
    String getApellidoMedico();
    String getMatriculaNac();
    String getMatriculaProv();
    String getTipoAccion();
    String getVisitaExitosa();
    String getProvincia();
    String getCiudad();
    String getCalle();
    Long getNumero();
    String getZip();
    String getInstitucion();
    String getFrecuencia();
    String getVisitaTipo();
    String getProducto1();
    String getProducto2();
    String getProducto3();
    String getProducto4();
    String getProducto5();
    String getProducto6();
    String getProducto7();
    String getProducto8();
    String getProducto9();
    String getProducto10();
    String getCicloIncio();
    String getCicloFin();
    String getMedicoEspecialidad();
    LocalDateTime getUltActualizacion();

}
