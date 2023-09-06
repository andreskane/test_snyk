package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.BatchSize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * @author Felipe Jimenez
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Institucion implements Serializable {
    private static final long serialVersionUID = -6440928129485483612L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_direccion")
    @BatchSize(size = 100)
    private Direccion direccion;

    @ManyToOne
    @JoinColumn(name="id_categoria")
    private InstitucionCategoria categoria;

    @ManyToOne
    @JoinColumn(name="id_tipo")
    private InstitucionTipo tipo;

    @Column(name="razon_social")
    private String razonSocial;

    private String telefono;

    private String identificacion;

    private boolean inactivo;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, true);
    }
}
