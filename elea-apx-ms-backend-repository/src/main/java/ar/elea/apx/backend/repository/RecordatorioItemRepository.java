package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.RecordatorioItem;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author Felipe Jimenez
 */
public interface RecordatorioItemRepository extends CrudRepository<RecordatorioItem, Long> {

    Optional<RecordatorioItem> findByRecordatorioId(Long recordatorioId);
}
