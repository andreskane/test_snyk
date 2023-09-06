package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Christian Corrales
 */

@Data
@Entity
@Table(name = "accion_apm_items")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccionApmItems implements Serializable {


    private static final long serialVersionUID = -369293913686395005L;
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_accion_apm")
    private Long idContactoDigital;

    @Column(name = "id_material_promocional")
    private Long idMaterialPromocional;

    @Column(name = "id_doctor")
    private Long idDoctor;


}
