package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.StockProductos;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Christian Corrales
 */
public interface StockProductosRepository extends JpaRepository<StockProductos, Long> {
}
