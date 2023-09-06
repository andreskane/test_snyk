package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.AgendaTipo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Felipe Jimenez
 */
@Repository
public interface AgendaTipoRepository extends CrudRepository<AgendaTipo, Long> {
    Optional<AgendaTipo> findAgendaTipoByNombreIgnoreCase(String agendaTipoNombre);
}
