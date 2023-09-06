package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Juan Cruz Rompani
 */
@Entity(name= "linea_apm")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineaApm {

    private static final long serialVersionUID = -1485833911536040363L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name = "id_apm")
    private Long idApm;
    @Column(name = "id_linea")
    private Long idLinea;

}
