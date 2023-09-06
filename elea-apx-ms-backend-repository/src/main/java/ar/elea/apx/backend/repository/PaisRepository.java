package ar.elea.apx.backend.repository;


import ar.elea.apx.backend.entity.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Christian Corrales
 */
@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {
    Pais findByNombreIgnoreCase(String nombre);
}
