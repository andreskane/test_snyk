package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.CategoriaPromocion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Diego Luis Hernandez
 */
public interface CategoriaPromocionRepository extends JpaRepository<CategoriaPromocion, Long> {

    @Query("select c from categoria_promocion c where c.id <> -1 order by c.orden asc")
    List<CategoriaPromocion> findByAllCategorias();

}
