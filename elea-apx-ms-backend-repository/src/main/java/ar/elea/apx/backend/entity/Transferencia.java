package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Guillermo Nasi
 */
@SqlResultSetMapping(
        name = "TransferenciaView",
        classes = {
                @ConstructorResult(
                        targetClass = ar.elea.apx.backend.view.TransferenciaView.class,
                        columns = {
                                @ColumnResult(name="id", type = Long.class),
                                @ColumnResult(name="nombreDoctor", type = String.class),
                                @ColumnResult(name="apellidoDoctor", type = String.class),
                                @ColumnResult(name="nombreApmOrigen", type = String.class),
                                @ColumnResult(name="apellidoApmOrigen", type = String.class),
                                @ColumnResult(name="nombreApmDestino", type = String.class),
                                @ColumnResult(name="apellidoApmDestino", type = String.class),
                                @ColumnResult(name="nombreUsuario", type = String.class),
                                @ColumnResult(name="apellidoUsuario", type = String.class),
                                @ColumnResult(name="fecha", type = Date.class),
                                @ColumnResult(name="fechaProgramada", type = Date.class),
                                @ColumnResult(name="tipo", type = String.class),
                                @ColumnResult(name="comentario", type = String.class)

                        }
                )
        }
)
@NamedNativeQuery(
        name = "TransferenciasPendientes",
        resultSetMapping = "TransferenciaView",
        query = "select t.id                             as id, " +
                "       d.primerNombre            as nombreDoctor, " +
                "       d.primerApellido          as apellidoDoctor, " +
                "       apmOrigen.primerNombre    as nombreApmOrigen, " +
                "       apmOrigen.primerApellido  as apellidoApmOrigen, " +
                "       apmDestino.primerNombre   as nombreApmDestino, " +
                "       apmDestino.primerApellido as apellidoApmDestino, " +
                "       u.nombre                  as nombreUsuario, " +
                "       u.apellido                as apellidoUsuario, " +
                "       t.fecha                          as fecha, " +
                "       t.fechaProgramada                as fechaProgramada, " +
                "       t.tipo                           as tipo, " +
                "       t.comentario                     as comentario " +
                "from transferencia t " +
                "         left join cartera_medica cm on t.id_cartera_medica = cm.id " +
                "         left join usuario u on t.id_usuario = u.id " +
                "         left join doctor d on cm.doctor_id = d.id " +
                "         left join apm apmOrigen on cm.apm_id = apmOrigen.id " +
                "         left join apm apmDestino on t.id_apm_destino = apmDestino.id " +
                "where " +
                "t.id_usuario = :idUsuario and " +
                "((t.fecha is null and fechaProgramada is null) or (t.fecha is null and programada_confirmada = 0))")

@NamedNativeQuery(
        name = "TransferenciasProgramadas",
        resultSetMapping = "TransferenciaView",
        query = "select t.id                             as id, " +
                "       d.primerNombre            as nombreDoctor, " +
                "       d.primerApellido          as apellidoDoctor, " +
                "       apmOrigen.primerNombre    as nombreApmOrigen, " +
                "       apmOrigen.primerApellido  as apellidoApmOrigen, " +
                "       apmDestino.primerNombre   as nombreApmDestino, " +
                "       apmDestino.primerApellido as apellidoApmDestino, " +
                "       u.nombre                  as nombreUsuario, " +
                "       u.apellido                as apellidoUsuario, " +
                "       t.fecha                          as fecha, " +
                "       t.fechaProgramada                as fechaProgramada, " +
                "       t.tipo                           as tipo, " +
                "       t.comentario                     as comentario " +
                "from transferencia t " +
                "         left join cartera_medica cm on t.id_cartera_medica = cm.id " +
                "         left join usuario u on t.id_usuario = u.id " +
                "         left join doctor d on cm.doctor_id = d.id " +
                "         left join apm apmOrigen on cm.apm_id = apmOrigen.id " +
                "         left join apm apmDestino on t.id_apm_destino = apmDestino.id " +
                "where t.fecha is null and t.fechaProgramada is not null and t.programada_confirmada = 1 and " +
                "      (:doctorNombre is null or d.primerNombre like concat(:doctorNombre, '%')) and " +
                "      (:doctorApellido is null or d.primerApellido like concat(:doctorApellido, '%')) and " +
                "      (:apmOrigenId is null or cm.apm_id = :apmOrigenId) and " +
                "      (:apmDestinoId is null or t.id_apm_destino = :apmDestinoId) and " +
                "      (:usuarioId is null or t.id_usuario = :usuarioId) and " +
                "      (:fechaProgramada is null or t.fechaProgramada = Convert(date, :fechaProgramada)) and " +
                "      (:tipo is null or t.tipo like :tipo)")
@NamedQuery(
        name = "TransferenciasProgramadasCount",
        query = "select count(t) " +
                "from Transferencia t " +
                "         left join cartera_medica cm on t.carteraMedica.id = cm.id " +
                "         left join Usuario u on t.usuario.id = u.id " +
                "         left join Doctor d on cm.doctor.id = d.id " +
                "         left join Apm apmOrigen on cm.apm.id = apmOrigen.id " +
                "         left join Apm apmDestino on t.apmDestino.id = apmDestino.id " +
                "where t.fecha is null and t.fechaProgramada is not null and t.programadaConfirmada = true and " +
                "      (:doctorNombre is null or d.primerNombre like :doctorNombre + '%') and " +
                "      (:doctorApellido is null or d.primerApellido like :doctorApellido + '%') and " +
                "      (:apmOrigenId is null or cm.apm.id = :apmOrigenId) and " +
                "      (:apmDestinoId is null or t.apmDestino.id = :apmDestinoId) and " +
                "      (:usuarioId is null or t.usuario.id = :usuarioId) and " +
                "      (:fechaProgramada is null or t.fechaProgramada = Convert(date, :fechaProgramada)) and " +
                "      (:tipo is null or t.tipo like :tipo)"
)
@Entity
@Table(name = "transferencia")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transferencia implements Serializable {

    private static final long serialVersionUID = -5341306270251438263L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cartera_medica")
    private CarteraMedica carteraMedica;

    @ManyToOne
    @JoinColumn(name = "id_apm_destino")
    private Apm apmDestino;

    @Enumerated(EnumType.STRING)
    private TransferType tipo;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private Date fecha;

    private Date fechaProgramada;

    @Column(name = "programada_confirmada", columnDefinition = "bit default 0")
    private Boolean programadaConfirmada;

    private String comentario;

    @PrePersist
    void prePersist() {
        if(this.programadaConfirmada == null)
            this.programadaConfirmada = false;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
