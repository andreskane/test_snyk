package ar.elea.apx.backend.entity;

import ar.elea.apx.backend.projection.ConexionesProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * @author Felipe Jimenez
 * @author Guillermo Nasi
 */
@SqlResultSetMapping(
        name = "ConexionesMapping",
        classes = @ConstructorResult(
                targetClass = ConexionesProjection.class,
                columns = {
                        @ColumnResult(name = "apmId", type = Long.class),
                        @ColumnResult(name = "apmLegajo", type = Long.class),
                        @ColumnResult(name = "apmNombre"),
                        @ColumnResult(name = "gerenteNombre"),
                        @ColumnResult(name = "fecha", type = Date.class),
                        @ColumnResult(name = "cantidadConexiones", type = Long.class)

                }))
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "deleted=false or deleted is null")
public class Apm extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -882004976036490728L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "primerNombre")
    private String nombre;

    @Column(name = "primerApellido")
    private String apellido;

    @Column(name = "email")
    private String email;

    @Column(name ="ad_oid")
    private String adOid;

    @Column(name ="profile_image")
    private byte[] profileImage;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    private String telefono;

    @ManyToOne
    @JoinColumn(name = "gerente_regional_id")
    private Usuario gerenteRegional;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "linea_apm",
            joinColumns = @JoinColumn(name = "id_apm", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_linea", nullable = false))
    @BatchSize(size = 100)
    private List<Linea> lineas = new ArrayList<>();

    @Column(name = "dias_registro_visita")
    private Integer diasRegistroVisita;

    private Boolean inactivo;

    private Long legajo;

    @OneToOne
    @JoinColumn(name = "id_direccion_entrega")
    @BatchSize(size = 100)
    private Direccion direccionEntrega;

    @OneToOne
    @JoinColumn(name = "id_direccion_particular")
    @BatchSize(size = 100)
    private Direccion direccionParticular ;

    @ManyToMany
    @JoinTable(name = "apm_institucion",
            joinColumns = @JoinColumn(name = "id_apm"),
            inverseJoinColumns = @JoinColumn(name = "id_institucion"))
    @BatchSize(size = 100)
    private List<Institucion> instituciones;

    @Column(name = "alias_geodata")
    private String aliasGeodata;

    private Boolean deleted;
    private String deletedBy;
    private Date deletedDate;

}
