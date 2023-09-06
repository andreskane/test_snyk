package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Felipe Jimenez
 */
@SqlResultSetMapping(
        name = "CarterasMedicasView",
        classes = {
                @ConstructorResult(
                        targetClass = ar.elea.apx.backend.view.CarteraMedicaView.class,
                        columns = {
                                @ColumnResult(name="carteraId", type = Long.class),
                                @ColumnResult(name="doctorId", type = Long.class),
                                @ColumnResult(name="doctorApellido", type = String.class),
                                @ColumnResult(name="doctorNombre", type = String.class),
                                @ColumnResult(name="doctorApp", type = Integer.class),
                                @ColumnResult(name="matriculaNacional", type = String.class),
                                @ColumnResult(name="matriculaProvincial", type = String.class),
                                @ColumnResult(name="especialidadDoctor", type = String.class),
                                @ColumnResult(name="subespecialidad", type = String.class),
                                @ColumnResult(name="provinciaDireccion", type = String.class),
                                @ColumnResult(name="localidadDireccion", type = String.class),
                                @ColumnResult(name="ciudadDireccion", type = String.class),
                                @ColumnResult(name="calleDireccion", type = String.class),
                                @ColumnResult(name="numeroDireccion", type = String.class),
                                @ColumnResult(name="apmId", type = Long.class),
                                @ColumnResult(name="apmApellido", type = String.class),
                                @ColumnResult(name="apmNombre", type = String.class),
                                @ColumnResult(name="gerenteRegionalApellido", type = String.class),
                                @ColumnResult(name="gerenteRegionalNombre", type = String.class),
                                @ColumnResult(name="frecuencia", type = Integer.class),
                                @ColumnResult(name="turno", type = Integer.class),
                                @ColumnResult(name="ultimaVisitaCartera", type = Date.class),
                                @ColumnResult(name="visitasCiclo", type = Integer.class),
                                @ColumnResult(name="cpa", type = String.class),
                                @ColumnResult(name="pisoDireccion", type = String.class),
                                @ColumnResult(name="departamentoDireccion", type = String.class),
                                @ColumnResult(name="otroDireccion", type = String.class),
                                @ColumnResult(name="entidadMatriculaProvincial", type = String.class),
                                @ColumnResult(name="ISO_entidadMatriculaProvincial", type = String.class)
                        }
                )
        }
)
@Entity(name = "cartera_medica")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarteraMedica extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 2057607170183903048L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Apm apm;

    @ManyToOne
    private Doctor doctor;

    @OneToOne
    @JoinColumn(name = "datos_visita_id")
    @BatchSize(size = 100)
    private DatosVisita datosVisita;

    private Boolean approved;

    private Boolean inactivo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartera_medica_estado_id")
    @BatchSize(size = 100)
    private CarteraMedicaEstado estado;

    public DatosVisita getDatosVisita() {
        return datosVisita;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        return EqualsBuilder.reflectionEquals(this, o, "createdDate", "modified", "modifiedBy");
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, true);
    }
}
