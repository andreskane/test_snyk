package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.RecordatorioItemTipo;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Felipe Jimenez
 */
public interface RecordatorioItemTipoRepository extends CrudRepository<RecordatorioItemTipo, Long> {
    RecordatorioItemTipo findFirstByNombre(String nombre);
}
