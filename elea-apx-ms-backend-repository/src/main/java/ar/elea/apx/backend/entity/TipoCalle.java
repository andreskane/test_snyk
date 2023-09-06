package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Guillermo Nasi
 */
@Data
@Entity
@Builder
@Table(name = "tipo_calle")
@NoArgsConstructor
@AllArgsConstructor
public class TipoCalle implements Serializable {
    private static final long serialVersionUID = -6510872358896306801L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Boolean inactivo;
}
