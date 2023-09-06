package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.*;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * @author Guillermo Nasi
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "datos_visita")
public class DatosVisita extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -7543134907915920523L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "institucion_id")
    @BatchSize(size = 100)
    private Institucion institucion;

    @OneToOne
    @JoinColumn(name = "id_direccion")
    @BatchSize(size = 100)
    private Direccion direccion;

    private TurnoEnum turno;

    @ManyToOne
    @JoinColumn(name = "especialidad_id")
    @BatchSize(size = 100)
    private Especialidad especialidad;

    private Integer frecuencia;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "datos_visita_horario_visita",
            joinColumns = @JoinColumn(name = "datos_visita_id"),
            inverseJoinColumns = @JoinColumn(name = "horario_visita_id"))
    @LazyCollection(LazyCollectionOption.FALSE)
    @BatchSize(size = 100)
    private List<HorarioVisita> horarios;

    @OneToOne
    @JoinColumn(name = "asistente_id")
    private Asistente asistente;

    @Cacheable("institucion")
    public Institucion getInstitucion() {
        return institucion;
    }

    @Cacheable("asistente")
    public Asistente getAsistente() {
        return asistente;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        return EqualsBuilder.reflectionEquals(this, o, "createdDate", "modified", "modifiedBy");
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, false);
    }
}
