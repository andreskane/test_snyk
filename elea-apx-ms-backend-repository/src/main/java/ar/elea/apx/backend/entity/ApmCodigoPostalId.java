package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author Guillermo Nasi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class ApmCodigoPostalId implements Serializable {
    private static final long serialVersionUID = -1485833916836040363L;
    @Column(name = "id_apm")
    private Long idApm;
    @Column(name = "id_cpa")
    private Long idCpa;
}
