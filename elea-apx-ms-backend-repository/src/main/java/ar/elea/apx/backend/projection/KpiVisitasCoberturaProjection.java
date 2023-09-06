package ar.elea.apx.backend.projection;


/**
 * @author Christian Corrales
 */
public interface KpiVisitasCoberturaProjection {
    Long getId();
    Integer getTotalVisitasCiclo();
    String getFecha();
    String getVisitaTipo();
    Boolean getVisitaExitosa();
    Long getGerenteId();
}
