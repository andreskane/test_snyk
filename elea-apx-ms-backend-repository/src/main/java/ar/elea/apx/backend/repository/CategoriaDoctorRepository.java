package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.CategoriaDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Christian Corrales
 */
@Repository
public interface CategoriaDoctorRepository extends JpaRepository<CategoriaDoctor, Long>{

}
