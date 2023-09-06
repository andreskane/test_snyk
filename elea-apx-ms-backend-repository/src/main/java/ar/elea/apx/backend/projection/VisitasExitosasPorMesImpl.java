package ar.elea.apx.backend.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitasExitosasPorMesImpl implements VisitasExitosasPorMesProjection {
    private Long apmId;
    private String apmNombre;
    private String apmApellido;
    private String gerenteNombre;
    private String gerenteApellido;
    private Long doctores;
    private Long presenciales;
    private Long virtuales;
    private Long frecuencias;
}
