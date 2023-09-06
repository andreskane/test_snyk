package ar.elea.apx.backend.projection;

/**
 * @author Guillermo Nasi
 */
public interface CodigoPostalProjection {
    Long getCodigoPostal();

    Long getProvinciaId();
    String getProvinciaNombre();

    Long getLocalidadId();
    String getLocalidadNombre();

    Long getCiudadId();
    String getCiudadNombre();
}
