package ar.elea.apx.backend.entity;

import ar.elea.apx.backend.entity.enums.TipoAccionEnum;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Christian Corrales
 */
@Data
@Entity(name = "accion_apm")
@NoArgsConstructor
@Builder
public class AccionApm extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1695122430790985767L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuario;

    @Column(name = "tipo_accion")
    @Enumerated(EnumType.STRING)
    private TipoAccionEnum tipoAccion;

    @ManyToOne
    @JoinColumn(name = "apm_id")
    private Apm apm;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "accion_apm_items",
            joinColumns = @JoinColumn(name = "id_accion_apm", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_material_promocional", nullable = false))
    private List<MaterialPromocional> materialPromocional = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "accion_apm_doctor",
            joinColumns = @JoinColumn(name = "id_accion", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_doctor", nullable = false))
    private List<Doctor> doctores = new ArrayList<>();


    @Builder(builderMethodName = "accionApmBuilder")
    public AccionApm(Long id, String usuario, TipoAccionEnum tipoAccion, Apm apm, List<MaterialPromocional> materialPromocional, List<Doctor> doctores) {
        super();
        this.id = id;
        this.usuario = usuario;
        this.tipoAccion = tipoAccion;
        this.apm = apm;
        this.materialPromocional = materialPromocional;
        this.doctores = doctores;

    }

}
