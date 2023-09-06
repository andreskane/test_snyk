package ar.elea.apx.backend.projection;

/**
 * @author Diego Luis Hernandez
 */
public interface MaterialPromocionalProjection extends Comparable<MaterialPromocionalProjection> {
    Long getId();
    String getNombre();
    String getArchivoExtension();
    byte[] getArchivo();
    Long getIdLinea();
    String getNombreLinea();
    Long getIdEspecialidad();
    String getNombreEspecialidad();
    Long getIdProducto();
    String getNombreProducto();
    Long getIdCiclo();
    String getNombreCiclo();
    boolean getInactivo();
    String getFecha();
    String getTamanio();
}
