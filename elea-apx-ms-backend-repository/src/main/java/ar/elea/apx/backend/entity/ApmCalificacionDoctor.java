package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Diego Luis Hernandez
 */
@Entity(name = "apm_calificacion_doctor")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApmCalificacionDoctor implements Serializable {
    private static final long serialVersionUID = -5295332402524233640L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id_apm")
    private long idApm;

    @Column(name = "id_doctor")
    private long idDoctor;

    @Column(name = "calificacion_doctor")
    private int calificacionDoctor;

    @Column(name = "fecha_calificacion")
    private LocalDate fechaCalificacion;

    @Column(name = "proxima_calificacion")
    private LocalDate proximaCalificacion;

}
