package ar.elea.apx.backend.entity;



import lombok.*;

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
@Entity(name="agenda_producto")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendaProducto implements Serializable {

    private static final long serialVersionUID = -4329651104807190506L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_agenda")
    private Agenda agenda;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private FamiliaProducto producto;

}
