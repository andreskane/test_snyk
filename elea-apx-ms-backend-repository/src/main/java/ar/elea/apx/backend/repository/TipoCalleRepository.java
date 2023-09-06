package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.TipoCalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Guillermo Nasi
 */
@Repository
public interface TipoCalleRepository extends JpaRepository<TipoCalle, Long> {

    @Modifying
    @Query("update TipoCalle set inactivo = true where id = :id")
    void markInactive(Long id);

    TipoCalle getTipoCalleByNombre(String tipo);
}
