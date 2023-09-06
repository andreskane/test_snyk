package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Juan Cruz Rompani
 */

@Entity(name = "categoria_producto")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String descripcion;

}
