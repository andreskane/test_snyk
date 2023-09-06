package ar.elea.apx.backend.entity;

import ar.elea.apx.backend.entity.enums.DoctorTipoEstancia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author Felipe Jimenez
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "deleted=false or deleted is null")
public class Doctor extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 5134864648419977070L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String primerNombre;

    private String segundoNombre;

    private String primerApellido;

    private String segundoApellido;

    private Date fechaNacimiento;

    private String email;

    private String telefono;

    private String matriculaNacional;

    private String matriculaProvincial;

    @ManyToOne
    private Provincia entidadMatriculaNacional;

    @ManyToOne
    private Provincia entidadMatriculaProvincial;

    @ManyToOne
    @BatchSize(size = 100)
    private Especialidad especialidad;
    
    @ManyToOne
    @BatchSize(size = 100)
    private LoyaltyDoctor loyalty;

    @ManyToOne
    @BatchSize(size = 100)
    private CategoriaDoctor categoria;

    @ManyToOne
    @BatchSize(size = 100)
    private EspeciaSubespecialidad espeSubEspecialidad;
    
    private Boolean px;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Column(name = "estado_civil")
    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    //@ManyToMany(fetch = FetchType.EAGER)
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tag_doctor",
        joinColumns = @JoinColumn(name = "doctor_id",referencedColumnName = "id"))
    @Cascade(CascadeType.SAVE_UPDATE)
    @BatchSize(size = 100)
    private List<Tag> tag;


    @JoinColumn(name = "sociedad_cientifica_id", nullable = true)
    @ManyToOne   @BatchSize(size = 100)
    private SociedadCientifica sociedadCientifica;


    @Column(name ="profile_image")
    private byte[] profileImage;

    @Column(name ="doctor_app")
    private Boolean doctorApp;

    private Boolean inactivo;

    //NUEVO
    private Boolean flagDocente;
    private String lugarDocente;

    private Boolean flagCoordinador;
    private String lugarCoordinador;

    private Boolean flagLiderOpi;
    private String ObsLiderOpi;

    private Boolean flagSocieCienti;
    private String nomSocieCienti;
    private String cargSocieCienti;

    private Boolean flagInteCienti;
    private String obsSocieCienti;

    @Column(name = "estancia")
    @Enumerated(EnumType.STRING)
    private DoctorTipoEstancia tipoEstancia;

    @Column(name = "flag_residente")
    private Boolean flagResidente;

    @Column(name = "flag_jefe_servicio")
    private Boolean flagJefeServicio;

    private Boolean deleted;
    private String deletedBy;
    private LocalDateTime deletedDate;

    @PrePersist
    void prePersist() {
        if(this.inactivo == null)
            this.inactivo = false;
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
