package ar.elea.apx.backend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "parametros_loyalty")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParametrosLoyalty implements Serializable {
	private static final long serialVersionUID = -3266892508282159530L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "prioridad_id")
	private PrioridadLoyalty prioridad;

	@Column(name = "limite_superior")
	private int limiteSuperior;
	@Column(name = "puntaje_superior")
	private int puntajeSuperior;
	@Column(name = "limite_alto")
	private int limiteAlto;
	@Column(name = "puntaje_alto")
	private int puntajeAlto;
	@Column(name = "limite_medio")
	private int limiteMedio;
	@Column(name = "puntaje_medio")
	private int puntajeMedio;
	@Column(name = "puntaje_bajo")
	private int puntajeBajo;
	@Column(name = "limite_inferior")
	private int limiteInferior;
	@Column(name = "puntaje_inferior")
	private int puntajeInferior;

}
