package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.Reporte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


/**
 * @author Christian Corrales
 */
public interface ReporteRepository extends CrudRepository<Reporte, Long> {
    Page<Reporte> findReporteByInactivoIsFalse(Pageable pageable);
}
