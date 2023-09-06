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

@Entity(name="categoria_doctor")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDoctor implements Serializable {
    private static final long serialVersionUID = 5683672089534245077L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private int puntaje;
}
