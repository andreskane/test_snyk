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

@Entity(name= "categoria_promocion")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaPromocion implements Serializable {

    private static final long serialVersionUID = -23386429255217703L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String abreviatura;

    @Column(name = "nombre_categoria")
    private String nombreCategoria;

    @Column(name = "orden")
    private int orden;

}
