package ar.elea.apx.backend.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Christian Corrales
 */
@Entity(name="catalogo_productos")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoProductos implements Serializable {
    private static final long serialVersionUID = -326221643162555994L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="cod_sap")
    private Long codSap;

    private String nombre;
    private String especialidad;
    private Boolean activo;
    private Double precio;


    @Column(name="id_familia")
    private FamiliaProducto idFamilia;

    @Column(name = "familia_producto")
    private String familiaProducto;

    @Column(name = "grupo_familia")
    private String grupoFamilia;

    @Column(name = "unidad_negocio")
    private String unidadNegocio;

    @Column(name="precio_vigencia")
    private Date precioVigengia;

    @Override
    public String toString() {
        return "CatalogoProductos{" +
                "id=" + id +
                ", codSap=" + codSap +
                ", nombre='" + nombre + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", activo=" + activo +
                ", precio=" + precio +
                '}';
    }
}
