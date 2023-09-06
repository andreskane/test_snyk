package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.Calle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalleRepository extends JpaRepository<Calle, Long> {

    List<Calle> findAllByCiudad_Id(Long idCiudad);

    @Query(value =
            "select c.* " +
            "from calle c " +
            "where c.id in (select distinct cp.id_calle " +
            "               from codigo_postal cp " +
            "                        left join apm_cpa a on cp.id = a.id_cpa " +
            "               where a.id_apm = :idApm " +
            "                 and cp.id_ciudad = :idCiudad)"
            , nativeQuery = true)
    List<Calle> getCallesForCiudadAndApm(Long idCiudad, Long idApm);

    List<Calle> findByNombreIgnoreCase(String nombre);

    @Modifying
    @Query("update Calle set inactivo = true where id = :id")
    void markInactive(Long id);
}
