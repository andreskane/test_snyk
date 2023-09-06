package ar.elea.apx.backend.projection;


/**
 * @author Christian Corrales
 * Proyección para ser utilizado como tipo de retorno en la consulta de visitas por gerente
 */
public interface VisitasPorGerenteProjection {
    Long getGerenteId();
    Long getTotalRequeridas();
}
