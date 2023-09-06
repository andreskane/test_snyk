package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Christian Corrales
 */
@Entity(name = "historico_fidelea_excel")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoFideleaExcel implements Serializable {

    private static final long serialVersionUID = -990866590038635960L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_actualizacion")
    private Timestamp fechaActualizacion;

    @Column(name = "cantidad_registros")
    private Integer cantidadRegistros;
}
