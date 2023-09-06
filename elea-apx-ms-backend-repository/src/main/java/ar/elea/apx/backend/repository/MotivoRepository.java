package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.Motivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Felipe Jimenez
 */
@Repository
public interface MotivoRepository extends JpaRepository<Motivo, Long> {

    List<Motivo> findAllByTipoNombreIgnoreCaseAndInactivoIsFalse(String tipoNombre);

    @Modifying
    @Query("update motivo set inactivo = true where id = :id")
    void markAsInactive(Long id);
}
