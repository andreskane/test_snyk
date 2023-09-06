package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Diego Luis Hernandez
 */
@Entity(name = "apm_muestras_aprobacion")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApmMuestrasAprobacion implements Serializable {
    private static final long serialVersionUID = -5295332402524233640L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "codigo_muestra")
    private long codigoMuestra;

    @Column(name = "producto_muestra")
    private String productoMuestra;

    @Column(name = "cantidad_enviada")
    private int cantidadEnviada;

    @Column(name = "cantidad_aprobada")
    private int cantidadAprobada;

    @Column(name = "lote")
    private String lote;

    @Column(name = "vecimiento")
    private LocalDate vencimiento;

    @Column(name = "fecha_envio")
    private LocalDate fechaEnvio;

    @Column(name = "justificacion")
    private String justificacion;

    @ManyToOne
    @JoinColumn(name = "id_recepcion_productos")
    private ApmCajaRecepcionProductos productos;

}