package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.EstadoDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Felipe Jimenez
 */
@Repository
public interface EstadoDoctorRepository extends JpaRepository<EstadoDoctor, Long> {

    Optional<List<EstadoDoctor>> findAllByDoctor_Id(Long doctorId);
}
