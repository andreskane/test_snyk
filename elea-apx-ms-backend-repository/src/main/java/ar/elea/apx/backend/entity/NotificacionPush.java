package ar.elea.apx.backend.entity;

import ar.elea.apx.backend.entity.enums.EstadoNotificacionEnum;
import ar.elea.apx.backend.entity.enums.TipoNotificacionEnum;
import ar.elea.apx.backend.entity.enums.TopicoNotificacionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.*;

/**
 * @author Christian Corrales
 */

@Entity(name = "notificacion_push")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionPush implements Serializable {
    private static final long serialVersionUID = -2997571946303655600L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    private String accion;
    private String token;

    @Enumerated(EnumType.STRING)
    private TopicoNotificacionEnum topico;

    @Enumerated(EnumType.STRING)
    private EstadoNotificacionEnum estado;

    @Enumerated(EnumType.STRING)
    private TipoNotificacionEnum tipo;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "modified_date")
    private Date modifiedDate;

    @Column(name = "modified_by")
    private String modifiedBy;

    @JoinTable(
            name = "notificacion_push_apm",
            joinColumns = @JoinColumn(name = "id_notificacion", nullable = false),
            inverseJoinColumns = @JoinColumn(name="id_apm", nullable = false)
    )
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Apm> apms = new ArrayList<>();

    @Column(name = "hora_prevista")
    private Time horaPrevista;

    @Column(name = "objeto_generico")
    private String objetoGenerico;
}
