package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Felipe Jimenez
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Modifying
    @Query("update producto p set p.inactivo = true where p.id = :id")
    void markAsInactive(Long id);
}
