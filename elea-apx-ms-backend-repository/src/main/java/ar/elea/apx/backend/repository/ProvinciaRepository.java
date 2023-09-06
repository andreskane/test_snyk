package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.Provincia;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Guillermo Nasi
 */
@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {

    List<Provincia> findAllByInactivoIsFalse(Sort nombre);
    List<Provincia> findByNombreIgnoreCase(String nombre);

    @Query(value =
            "select p.* " +
            "from provincia p   " +
            "where p.id in (select distinct cp.id_provincia " +
            "from codigo_postal cp " +
            "left join apm_cpa ac on cp.id = ac.id_cpa " +
            "where ac.id_apm = :apmId)", nativeQuery = true)
    List<Provincia> getProvinciasApm(Long apmId);

    @Modifying
    @Query("update Provincia set inactivo = true where id = :id")
    void markInactive(Long id);
}
