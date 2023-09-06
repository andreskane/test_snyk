package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * @author Felipe Jimenez
 */
@Entity(name = "material_promocional_apm")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaterialPromocionalApm implements Serializable {

    private static final long serialVersionUID = 4971964925803480193L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="apm_id")
    private Apm apm;

    @ManyToOne
    @JoinColumn(name="material_promocional_id")
    private MaterialPromocional materialPromocional;

    @Column(name = "tiene_material")
    private Boolean tieneMaterial;
}
