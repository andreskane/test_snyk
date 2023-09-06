package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * @author Guillermo Nasi
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Provincia implements Serializable {
    private static final long serialVersionUID = -6122492804215539765L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_pais")
    private Pais pais;

    private Boolean inactivo;

    @Column(name = "EsEntidadMatriculanteProvincial")
    private Boolean esEntidadMatriculanteProvincial;

    @Column(name="ISO_CODE")
    private  String ISO_CODE;
}
