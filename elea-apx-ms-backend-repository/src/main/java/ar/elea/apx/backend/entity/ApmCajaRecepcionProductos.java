package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Diego Luis Hernandez
 */
@Entity(name = "apm_caja_recepcion_productos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApmCajaRecepcionProductos implements Serializable {
    private static final long serialVersionUID = -5295332402524233640L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "codigo_producto")
    private Long codigoProducto;

    @Column(name = "nombre_producto")
    private String nombreProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_caja_recepcion")
    private ApmCajaRecepcion cajaRecepcion;
}
