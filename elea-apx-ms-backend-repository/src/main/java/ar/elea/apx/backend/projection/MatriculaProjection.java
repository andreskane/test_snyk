package ar.elea.apx.backend.projection;

/**
 * @author Guillermo Nasi
 */
public interface MatriculaProjection extends Comparable<MatriculaProjection> {
    String getNumeroMatricula();
    Long getEnteEmisor();
    String getNombreEmisor();
    Integer getCantidad();
}
