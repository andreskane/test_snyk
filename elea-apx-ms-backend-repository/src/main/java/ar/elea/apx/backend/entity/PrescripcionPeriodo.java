package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Felipe Jimenez
 */
@Entity(name = "SFN_audit_period")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrescripcionPeriodo implements Serializable {

    private static final long serialVersionUID = -9100054587634593307L;
    @Id
    @Column(name="cdg_periodo")
    private String codPeriodo;

    private String descripcion;

    private String fecha;

}
