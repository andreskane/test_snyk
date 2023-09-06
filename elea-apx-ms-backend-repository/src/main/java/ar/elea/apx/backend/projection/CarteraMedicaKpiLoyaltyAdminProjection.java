package ar.elea.apx.backend.projection;

/**
 * @author Diego Luis Hernandez
 */
public interface CarteraMedicaKpiLoyaltyAdminProjection {
    Long getGerenteId();
    String getNombre();
    String getApellido();
    Long getTotalMedicos();
    Long getTotalApm();
    Long getContactosTarget();
    Long getSinCategoria();
    Long getBronce();
    Long getPlata();
    Long getOro();
    Long getPlatino();
}
