package ar.elea.apx.backend.projection;


/**
 * @author Felipe Jimenez
 * Proyección para ser utilizado como tipo de retorno en la consulta de visitas por doctor
 */
public interface VisitasPorDoctorProjection {
    Long getDoctorId();
    Long getVisitas();
}
