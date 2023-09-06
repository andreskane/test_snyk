package ar.elea.apx.backend.projection;


/**
 * @author Diego Luis Hernandez
 */
public interface MuestrasApmProjection {
    long getCodigoProducto();
    String getNombreProducto();
    long getCodigoPresentaciones();
    String getPresentaciones();
    long getCodigoLinea();
    int getCantidad();
    String getLinea();
    long getCodigoApm();
    String getLote();
    String getFechaVencimiento();
    String getCantidadEnviada();
    String getCantidadRecibida();
    String getFechaEnvio();
    long getFila();
    String getJustificacion();
    long getCodigoSap();
}
