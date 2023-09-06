package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Felipe Jimenez
 */
@Entity(name = "producto")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Producto implements Serializable {
    private static final long serialVersionUID = -1722461022905738950L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_linea")
    private Linea linea;

    @ManyToOne
    @JoinColumn(name="id_categoria")
    private CategoriaProducto categoriaProducto;

    @ManyToOne
    @JoinColumn(name="id_especialidad")
    private Especialidad especialidad;

    private String nombre;

    private String abreviatura;

    private String descripcion;

    private Boolean inactivo;

}