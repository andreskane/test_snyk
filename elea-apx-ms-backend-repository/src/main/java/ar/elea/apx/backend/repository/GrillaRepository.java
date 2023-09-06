package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.Grilla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * @author Diego Luis Hernandez
 */
@Repository
public interface GrillaRepository extends JpaRepository<Grilla, Long> {

    @Query(value= "SELECT g FROM grilla g where g.id = :idGrilla")
    Grilla findAllByIdGrilla(Long idGrilla);
}
