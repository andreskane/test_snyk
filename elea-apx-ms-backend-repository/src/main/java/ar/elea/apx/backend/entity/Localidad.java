package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

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
@BatchSize(size = 200)
public class Localidad implements Serializable {
    private static final long serialVersionUID = 6944968461636789792L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_provincia")
    private Provincia provincia;

    private Boolean inactivo;
}
