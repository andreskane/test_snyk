package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Diego Luis Hernandez
 */
@Entity(name = "apm_inventario_muestra")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApmInventarioMuestra implements Serializable {
    private static final long serialVersionUID = -5295332402524233639L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "estado")
    private String estado;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "id_muestras_aprobacion")
    private ApmMuestrasAprobacion apmMuestrasAprobacion;

}
