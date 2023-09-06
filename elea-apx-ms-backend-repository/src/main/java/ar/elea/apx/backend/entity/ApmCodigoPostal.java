package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@Builder
@Entity
@Table(name = "apm_cpa")
@NoArgsConstructor
@AllArgsConstructor
public class ApmCodigoPostal implements Serializable {

    private static final long serialVersionUID = -3825130349450202081L;
    @EmbeddedId
    private ApmCodigoPostalId id;

}
