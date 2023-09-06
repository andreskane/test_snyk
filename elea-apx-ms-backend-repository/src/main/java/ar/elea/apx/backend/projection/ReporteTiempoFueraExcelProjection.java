package ar.elea.apx.backend.projection;

/**
 * @author Christian Corrales
 */
public interface ReporteTiempoFueraExcelProjection {
    String getSupervisor();
    String getLegajoApm();
    String getApellidoApm();
    String getNombrApm();
    String getFechaInicio();
    String getFechaFin();
    String getMotivo();
    String getPorcentajeTotal();
    String getPorcentajeManana();
    String getPorcentajeTarde();
    String getTurno();
    String getCiclo();
    String getCicloIncio();
    String getCicloFin();
    String getUltActualizacion();
}
