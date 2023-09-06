package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.DatosVisita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Guillermo Nasi
 */
@Repository
public interface DatosVisitaRepository extends JpaRepository<DatosVisita, Long> {

}
