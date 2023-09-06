package ar.elea.apx.backend.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "prescripciones_doctor")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrescripcionesDoctor implements Serializable {
	private static final long serialVersionUID = 5617273478671009310L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
	
	private Date fecha;
	
	@Column(name = "codigo_laboratorio")
	private String codigoLaboratorio;
	
	@Column(name = "nombre_laboratorio")
	private String nombrelaboratorio;
	
	@Column(name = "codigo_producto")
	private String codigoProducto;
	
	@Column(name = "nombre_producto")
	private String nombreProducto;
	
	@Column(name = "marca")
	private String marca;
	
	private int prescripciones;
}
