package ar.elea.apx.backend.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Felipe Jimenez
 * @author Guillermo Nasi
 * Proyección para ser utilizado en el reporte de conexiones por mes
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConexionesProjection {
    private Long apmId;
    private Long apmLegajo;
    private String apmNombre;
    private String gerenteNombre;
    private Date fecha;
    private Long cantidadConexiones;
}
