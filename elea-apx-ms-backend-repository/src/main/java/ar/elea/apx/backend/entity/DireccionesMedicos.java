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
@Entity(name = "direcciones_medicos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DireccionesMedicos implements Serializable {
    private static final long serialVersionUID = -5295332407524233640L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * At this moment the fetch object doctor is not required in the process
     */
    @Column(name = "id_medico")
    private int idMedico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_direccion")
    private Direccion direccion;

    @Column()
    private boolean active;
}