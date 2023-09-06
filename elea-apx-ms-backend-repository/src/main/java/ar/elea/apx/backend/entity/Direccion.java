package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;

import java.io.Serializable;

/**
 * @author Guillermo Nasi
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Direccion implements Serializable {

    private static final long serialVersionUID = -169086927414629382L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_codigo_postal")
    private CodigoPostal codigoPostal;

    @Column(name = "numero")
    private Long numero;

    @Column(name = "detalle")
    private String detalle;

    private String piso;

    private String departamento;

    private String otro;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
