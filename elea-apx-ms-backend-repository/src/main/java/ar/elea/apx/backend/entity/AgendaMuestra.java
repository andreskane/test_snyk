package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Christian Corrales
 */

@Table(name = "agenda_muestra")
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendaMuestra implements Serializable {
    private static final long serialVersionUID = -1753141446078776337L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_agenda")
    private Agenda agenda;

    private String nombre;

    private Integer cantidad;

}
