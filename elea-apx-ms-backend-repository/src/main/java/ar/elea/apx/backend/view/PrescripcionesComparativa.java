package ar.elea.apx.backend.view;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Guillermo Nasi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescripcionesComparativa {
    private String codMercado;
    private String codRaiz;
    private String mercado;
    private String producto;
    private String periodo;
    private String categoria;
    private String cantidad;
    private String porcentaje;
    private String codigoLaboratorio;
    private String nombreLaboratorio;

}
