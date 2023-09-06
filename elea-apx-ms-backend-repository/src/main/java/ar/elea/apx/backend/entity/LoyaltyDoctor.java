package ar.elea.apx.backend.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ferney Escobar
 */

@Entity(name="loyalty_doctor")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoyaltyDoctor implements Serializable  {
	private static final long serialVersionUID = 8324967169105255628L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String nombre;
	private int puntaje; //Corresponde al puntaje minimo para pertenecer a esta categoria
}
