package ar.elea.apx.backend.entity;

import ar.elea.apx.backend.entity.enums.AppPantallaEnum;
import ar.elea.apx.backend.entity.enums.PuntajeFeedbackEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@SqlResultSetMapping(name = "FeedBackView",
        classes = {
                @ConstructorResult(
                        targetClass = ar.elea.apx.backend.view.FeedBackView.class,
                        columns = {
                                @ColumnResult(name="puntaje", type = Integer.class),
                                @ColumnResult(name="pantalla", type = Integer.class),
                                @ColumnResult(name="conteo", type = Integer.class),
                        }
                )
        }
)

@NamedNativeQuery(name = "ComentariosAgrupados",
                    resultSetMapping = "FeedBackView",
                    query = "select pantalla, puntaje, COUNT(puntaje) " +
                            "as conteo from feedback " +
                            " where (:idPantalla is null or pantalla = :idPantalla)" +
                            "GROUP BY pantalla, " +
                            "puntaje ORDER BY pantalla")

@NamedQuery(name = "ComentariosAgrupadosCount",
        query = "select COUNT(puntaje) " +
                " from feedback " +
                " where (:idPantalla is null or pantalla = :idPantalla) " +
                "GROUP BY pantalla, " +
                "puntaje ORDER BY pantalla")

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "feedback")
public class Feedback implements Serializable {

    private static final long serialVersionUID = -1880536058916830384L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "idApm")
    private Apm apm;
    @Column
    private AppPantallaEnum pantalla;
    @Column
    private PuntajeFeedbackEnum puntaje;
    @Column
    private String comentario;
    @Column
    private Date fecha;


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, false);
    }
}
