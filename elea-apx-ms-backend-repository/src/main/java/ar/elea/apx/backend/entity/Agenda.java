package ar.elea.apx.backend.entity;

import ar.elea.apx.backend.projection.VisitasExitosasPorMesImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Felipe Jimenez
 */
@SqlResultSetMapping(
        name = "VisitasExitosasMapping",
        classes = @ConstructorResult(
                targetClass = VisitasExitosasPorMesImpl.class,
                columns = {
                        @ColumnResult(name = "apmId", type = Long.class),
                        @ColumnResult(name = "apmNombre"),
                        @ColumnResult(name = "apmApellido"),
                        @ColumnResult(name = "gerenteNombre"),
                        @ColumnResult(name = "gerenteApellido"),
                        @ColumnResult(name = "doctores", type = Long.class),
                        @ColumnResult(name = "presenciales", type = Long.class),
                        @ColumnResult(name = "virtuales", type = Long.class),
                        @ColumnResult(name = "frecuencias", type = Long.class)

                }))
@Table(name = "agenda")
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agenda implements Serializable {

    private static final long serialVersionUID = 2337877338876723635L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp inicio;

    private Timestamp fin;

    @ManyToOne
    private Apm apm;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Institucion institucion;

    private String descripcion;

    private String lugar;

    @ManyToOne
    @JoinColumn(name = "direccion_id")
    private Direccion direccion_id;

    private String direccion;

    @Enumerated(EnumType.STRING)
    private PerfilEnum perfil;

    private String observaciones;

    @Enumerated(EnumType.STRING)
    private TurnoEnum turno;

    @Enumerated(EnumType.STRING)
    @Column(name="visita_tipo")
    private VisitaTipoEnum visitaTipo;

    @ManyToOne
    @JoinColumn(name="agenda_tipo_id")
    private AgendaTipo agendaTipo;

    @Column(name="visita_exitosa")
    private Boolean visitaExitosa;

    @ManyToOne
    @JoinColumn(name="agenda_motivo_fallida_id")
    private Motivo motivoFallida;

    @Enumerated(EnumType.STRING)
    private VisitaAcompananteEnum acompanante;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agenda")
    private List<AgendaProducto> agendaProductos;

    private Timestamp lastUpdated;

    private Boolean inactivo;

    @Column(name="porcentaje_turno_manana")
    private Float porcentajeTurnoManana;

    @Column(name="porcentaje_turno_tarde")
    private Float porcentajeTurnoTarde;

    @Column(name="requires_approval")
    private Boolean requiresApproval;

    private Boolean approved;

    @ManyToOne
    @JoinColumn(name = "approved_by")
    private Usuario approvedBy;

    private Integer plataforma;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agenda")
    private List<AgendaMuestra> agendaMuestras;

    @Cacheable("apm")
    public Apm getApm() {
        return apm;
    }

    @Cacheable("doctor")
    public Doctor getDoctor() {
        return doctor;
    }
}
