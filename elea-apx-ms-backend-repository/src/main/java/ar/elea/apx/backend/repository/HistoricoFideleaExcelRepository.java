package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.HistoricoDrAppCsv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Christian Corrales
 */
@Repository
public interface HistoricoFideleaExcelRepository extends JpaRepository<HistoricoDrAppCsv, Long> {
}
