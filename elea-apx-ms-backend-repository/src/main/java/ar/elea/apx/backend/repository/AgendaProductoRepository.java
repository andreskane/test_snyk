package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.AgendaProducto;
import ar.elea.apx.backend.entity.FamiliaProducto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Felipe Jimenez
 */
@Repository
public interface AgendaProductoRepository extends CrudRepository<AgendaProducto, Long> {
    void deleteAgendaProductoByAgendaId(Long agendaId);


    @Query(value = "select ap.producto from agenda_producto ap where ap.agenda.id = :agendaId")
    List<FamiliaProducto> findAllProductosForAgendaId(Long agendaId);
}
