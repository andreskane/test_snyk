package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.Localidad;
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
public interface LocalidadRepository extends JpaRepository<Localidad, Long> {

    @Query(value =
            "select distinct l.* " +
            "from localidad l " +
            "left join codigo_postal cp on l.id = cp.id_localidad " +
            "left join apm_cpa ac on cp.id = ac.id_cpa " +
            "where ac.id_apm = :idApm and cp.id_provincia = :idProvincia"
            , nativeQuery = true)
    List<Localidad> getLocalidadesProvinciaAndApm(Long idProvincia, Long idApm);

    List<Localidad> findAllByProvincia_Id(Long id, Sort name);

    @Query(value =
            "select l.*  " +
            "from localidad l  " +
            "where l.id in (select distinct cp.id_localidad  " +
            "from codigo_postal cp  " +
            "left join apm_cpa ac on cp.id = ac.id_cpa  " +
            "where ac.id_apm = :idApm)"
            , nativeQuery = true)
    List<Localidad> getLocalidadesForApm(Long idApm);

    @Modifying
    @Query("update Localidad set inactivo = true where id = :id")
    void markInactive(Long id);

    List<Localidad> findByNombreIgnoreCase(String nombre);

    @Query(value ="select l.* from localidad l where (coalesce(l.nombre,'') like lower(concat(:nombre, '%'))) and (:idProvincia is null or l.id_provincia = :idProvincia) " , nativeQuery = true)
    List<Localidad> getLocalidadesOpcional(String nombre, Long idProvincia);

}
