package ar.elea.apx.backend.projection;

/**
 * @author Christian Corrales
 */
public interface ReporteTransferenciasExcelProjection {
    String getGerenteOrigen();
    String getCodMedico();
    String getNombreApmOrigen();
    String getApellidoApmOrigen();
    String getLegajoApmOrigen();
    String getGerenteDestino();
    String getNombreApmDestino();
    String getApellidoApmDestino();
    String getLegajoApmDestino();
    String getNombreDoctor();
    String getApellidoDoctor();
    String getMatriculaNacional();
    String getMatriculaProvincial();
    String getEspecialidad();
    String getUsuario();
    String getTipoTransferencia();
    String getFechaProgramada();
    String getProvincia();
    String getLocalidad();
    String getCiudad();
    String getCp();
    String getInstitucion();
    String getCategoria();
    String getId();
}
