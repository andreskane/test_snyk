package ar.elea.apx.backend.projection;

/**
 * @author Christian Corrales
 * Proyección para ser utilizado en el reporte de conexiones diarias
 */

public interface DoctoresEspecialidadesProjection {
    String getNombre();
    Integer getCantidad();
    Double getPorcentaje();
}
