package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.Especialidad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Guillermo Nasi
 */
@Repository
public interface EspecialidadRepository extends CrudRepository<Especialidad, Long> {

    List<Especialidad> findAllByInactivoIsFalse();

}
