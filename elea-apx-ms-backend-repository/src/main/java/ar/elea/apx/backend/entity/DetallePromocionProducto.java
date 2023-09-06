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
@Entity(name = "detalle_promocion_producto")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetallePromocionProducto implements Serializable {

    private static final long serialVersionUID = -5295332402524233658L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Integer valor;

    @Column(name = "estado_ciclo")
    private String estadoCiclo;

    @Column(name = "estado_grilla")
    private String estadoGrilla;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_grilla")
    private Grilla grilla;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ciclo")
    private Ciclo ciclo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_familia_producto")
    private FamiliaProducto familiaProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria_promocion")
    private CategoriaPromocion categoriaPromocion;


}
