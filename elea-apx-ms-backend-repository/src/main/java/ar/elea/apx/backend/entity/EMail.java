package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Christian Corrales
 */

@Entity(name = "email")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EMail implements Serializable {
    private static final long serialVersionUID = -4739994594625981414L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String asunto;
    private String mensaje;

    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;

    @ManyToOne
    @JoinColumn(name = "id_accion")
    private AccionApm accionApm;


}
