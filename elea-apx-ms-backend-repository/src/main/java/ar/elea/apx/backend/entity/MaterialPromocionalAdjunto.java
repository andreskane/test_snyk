package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Christian Corrales
 */

@Entity(name = "material_promocional_adjunto")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialPromocionalAdjunto implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private byte[] archivo;

    @OneToOne
    @JoinColumn(name = "id_material")
    private MaterialPromocional materialPromocional;
}
