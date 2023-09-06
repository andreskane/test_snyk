package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.CategoriaProducto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Juan Cruz Rompani
 */
public interface CategoriaProductoRepository extends JpaRepository<CategoriaProducto, Long> {
}
