package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Diego Luis Hernandez
 */

@Entity(name= "grilla")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grilla implements Serializable {

    private static final long serialVersionUID = -2338626292552177465L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String abreviatura;

    @Column(name = "nombre_grilla")
    private String nombreGrilla;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_linea")
    private Linea linea;


}
