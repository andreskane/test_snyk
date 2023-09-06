package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Felipe Jimenez
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Recordatorio extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1145354675378371479L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_apm")
    private Apm apm;

    @ManyToOne
    @JoinColumn(name = "id_agenda")
    private Agenda agenda;

    @Column(name="fecha_recordatorio")
    private LocalDate fechaRecordatorio;

    @ManyToOne
    @JoinColumn(name="id_doctor")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name="id_agenda_completado")
    private Agenda agendaCompletado;

    private String descripcion;

    private Boolean inactivo;

    @Enumerated(EnumType.STRING)
    private FrecuenciaRecordatorioEnum frecuencia;

    @Column(name = "hora_recordatorio")
    private String horaRecordatorio;

}

