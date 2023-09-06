package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * @author Felipe Jimenez
 */
@Entity(name = "recordatorio_item")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordatorioItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_recordatorio")
    private Recordatorio recordatorio;

    @OneToOne
    @JoinColumn(name = "id_tipo")
    private RecordatorioItemTipo tipo;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_material_promocional")
    private MaterialPromocional materialPromocional;
}
