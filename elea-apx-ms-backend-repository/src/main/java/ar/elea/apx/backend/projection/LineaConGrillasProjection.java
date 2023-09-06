package ar.elea.apx.backend.projection;

/**
 * @author Danilo Roman
 * Proyección para ser utilizado en el reporte de conexiones diarias
 */

public interface LineaConGrillasProjection {
    Long getId();
    String getNombre();
    Long getIdGrilla();
    String getNombreGrilla();
    String getMesAno();
    String getSemaforo();
    String getestadoGrilla();
    String getAbreviatura();
    String getEstadoCiclo();

}
