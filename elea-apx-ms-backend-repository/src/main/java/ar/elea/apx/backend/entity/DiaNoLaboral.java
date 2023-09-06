package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Christian Corrales
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "dias_no_laborables")
public class DiaNoLaboral implements Serializable {
    private static final long serialVersionUID = 6206479795121379402L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private LocalDate fecha;

}
