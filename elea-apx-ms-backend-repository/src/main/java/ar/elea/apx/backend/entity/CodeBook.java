package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Christian Corrales
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodeBook implements Serializable {

    private static final long serialVersionUID = -5745527662621413922L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String apellido;
    private String nombre;

    @Column(name = "matricula_nacional")
    private String matriculaNacional;

    @Column(name="matricula_provincial")
    private String matriculaProvincial;

    @Column(name="fecha_creacion_medico")
    private Timestamp fechaCreacionMedico;

    @Column(name="f_turnos")
    private Integer fTurnos;

    @Column(name="r_turnos")
    private Timestamp rTurnos;

    @Column(name="f_hce")
    private Integer fHce;

    @Column(name="r_hce")
    private Timestamp rHce;

    @Column(name="f_recetas")
    private Integer fRecetas;

    @Column(name="r_recetas")
    private Timestamp rRecetas;
}
