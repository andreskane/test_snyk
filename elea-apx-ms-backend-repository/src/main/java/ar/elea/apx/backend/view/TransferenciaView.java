package ar.elea.apx.backend.view;

import ar.elea.apx.backend.entity.TransferType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @author Guillermo Nasi
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TransferenciaView {
    private Long id;
    private String nombreDoctor;
    private String apellidoDoctor;
    private String nombreApmOrigen;
    private String apellidoApmOrigen;
    private String nombreApmDestino;
    private String apellidoApmDestino;
    private String nombreUsuario;
    private String apellidoUsuario;
    private Date fecha;
    private Date fechaProgramada;
    private TransferType tipo;
    private String comentario;

    public TransferenciaView(Long id,
                             String nombreDoctor, String apellidoDoctor,
                             String nombreApmOrigen, String apellidoApmOrigen,
                             String nombreApmDestino, String apellidoApmDestino,
                             String nombreUsuario, String apellidoUsuario,
                             Date fecha, Date fechaProgramada, String tipo, String comentario) {
        this.id = id;
        this.nombreDoctor = nombreDoctor;
        this.apellidoDoctor = apellidoDoctor;
        this.nombreApmOrigen = nombreApmOrigen;
        this.apellidoApmOrigen = apellidoApmOrigen;
        this.nombreApmDestino = nombreApmDestino;
        this.apellidoApmDestino = apellidoApmDestino;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.fecha = fecha == null ? null : (Date)fecha.clone();
        this.fechaProgramada = fechaProgramada == null ? null : (Date)fechaProgramada.clone();
        this.tipo = StringUtils.isEmpty(tipo) ? null : TransferType.valueOf(tipo);
        this.comentario = comentario;
    }
}
