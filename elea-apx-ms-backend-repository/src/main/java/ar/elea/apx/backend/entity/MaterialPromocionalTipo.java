package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Felipe Jimenez
 */
@Entity(name = "material_promocional_tipo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaterialPromocionalTipo implements Serializable {
    private static final long serialVersionUID = -5295332402524233628L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
}
