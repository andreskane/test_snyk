package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Edison González
 */
@Entity(name = "instituciones_medicos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstitucionesMedicos implements Serializable {
    private static final long serialVersionUID = -5295112486524244140L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * At this moment the fetch object doctor is not required in the process
     */
    @Column(name = "id_medico")
    private int idMedico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_institucion")
    private Institucion institucion;

    @Column()
    private boolean active;
}