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

@Entity(name = "prioridad_loyalty")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrioridadLoyalty implements Serializable {
	private static final long serialVersionUID = -3176308006767911430L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private float multiplicador;
}
