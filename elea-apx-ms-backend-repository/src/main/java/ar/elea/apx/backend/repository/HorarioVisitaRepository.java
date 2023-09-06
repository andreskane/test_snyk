package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.HorarioVisita;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Guillermo Nasi
 */
@Repository
public interface HorarioVisitaRepository extends CrudRepository<HorarioVisita, Long> {}
