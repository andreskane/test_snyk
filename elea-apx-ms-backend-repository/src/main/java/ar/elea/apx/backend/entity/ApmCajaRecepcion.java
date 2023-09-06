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
@Entity(name = "apm_caja_recepcion")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApmCajaRecepcion implements Serializable {
    private static final long serialVersionUID = -5295332402524233640L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id_apm")
    private long idApm;

    @Column(name = "fecha_envio")
    private LocalDate fechaEnvio;

    @Column(name = "comentarios")
    private String comentarios;

    @Column(name = "estado_aprobacion")
    private String estadoAprobacion;

    @Column(name = "fecha_aprobacion")
    private LocalDate fechaAprobacion;

}
