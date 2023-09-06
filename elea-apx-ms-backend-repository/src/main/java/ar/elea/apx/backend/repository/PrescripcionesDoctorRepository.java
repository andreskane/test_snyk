package ar.elea.apx.backend.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.elea.apx.backend.entity.PrescripcionesDoctor;
import ar.elea.apx.backend.projection.PrescripcionesMedicasProjection;

/**
 * @author Ferney Escobar
 */
@Repository
public interface PrescripcionesDoctorRepository  extends JpaRepository<PrescripcionesDoctor, Long>{
	
	List<PrescripcionesDoctor> findByFechaGreaterThanEqualOrderByDoctorIdAscFechaAsc(Date fecha);
	
	@Query(value =
			"SELECT codigo_producto as codigo, nombre_producto as nombre, SUM(prescripciones) prescripciones  " +
					"from prescripciones_doctor prescripciones " +
					"where doctor_id = :doctorId " +
					"GROUP by codigo_producto, nombre_producto " +
			        "ORDER BY SUM(prescripciones) DESC"
			, nativeQuery = true)
	List<PrescripcionesMedicasProjection> getPescripcionesPorProducto(Long doctorId);

	@Query(value =
			"SELECT codigo_laboratorio as codigo, nombre_laboratorio as nombre, SUM(prescripciones) AS prescripciones " +
					"from prescripciones_doctor prescripciones " +
					"where doctor_id = :doctorId " +
					"GROUP BY codigo_laboratorio, nombre_laboratorio " +
					"ORDER BY SUM(prescripciones) DESC"
			, nativeQuery = true)
	List<PrescripcionesMedicasProjection> getPescripcionesPorLaboratorio(Long doctorId);

}
