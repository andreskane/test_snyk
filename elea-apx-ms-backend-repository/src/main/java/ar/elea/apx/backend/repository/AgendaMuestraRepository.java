package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.AgendaMuestra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Christian Corrales
 */
public interface AgendaMuestraRepository extends CrudRepository<AgendaMuestra, Long> {
    void deleteAgendaMuestraByAgendaId(Long agendaId);
    List<AgendaMuestra> findAllByAgenda_id(Long agendaId);
}
