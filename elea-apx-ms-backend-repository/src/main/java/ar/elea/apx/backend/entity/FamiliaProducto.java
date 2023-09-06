package ar.elea.apx.backend.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Christian Corrales
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="familia_producto")
public class FamiliaProducto implements Serializable {
    private static final long serialVersionUID = -2636856681469409362L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String descripcion;

    @Column(name="url_imagen")
    private String urlImagen;

    @Column(name="accion_terapeutica")
    private String accionTerapeutica;

    @Column(name="principio_activo")
    private String principioActivo;

    private Boolean muestraMedica;

    @Column(name = "obra_social")
    private Boolean obraSocial;

    private Boolean prepaga;

    private String linea;

    @Column(name = "url_prospecto")
    private String urlProspecto;

    private String nombre;


    @Override
    public String toString() {
        return "FamiliaProducto{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", urlImagen='" + urlImagen + '\'' +
                ", accionTerapeutica='" + accionTerapeutica + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FamiliaProducto that = (FamiliaProducto) o;
        return Objects.equals(id, that.id) && Objects.equals(descripcion, that.descripcion) && Objects.equals(urlImagen, that.urlImagen) && Objects.equals(accionTerapeutica, that.accionTerapeutica);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descripcion, urlImagen, accionTerapeutica);
    }
}
