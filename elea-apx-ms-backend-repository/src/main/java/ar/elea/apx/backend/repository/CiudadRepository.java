package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.Ciudad;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Felipe Jimenez
 */
@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Long> {

    List<Ciudad> findAllByLocalidad_Id(Long idLocalidad, Sort nombre);


    @Query(value =
            "select distinct c.* from provincia p " +
                    "left join codigo_postal cd on cd.id_provincia=p.id " +
                    "left join ciudad c on cd.id_ciudad=c.id " +
                    "left join localidad l on l.id = c.id_localidad " +
                    "where l.id_provincia = :idProvincia " +
                    "and cd.id_provincia = :idProvincia " +
                    "and c.inactivo=0 "
            , nativeQuery = true)
    List<Ciudad> findAllByLocalidad_Provincia_Id(Long idProvincia);


    @Query(value =
            "select distinct c from Ciudad c " +
                    "where c.localidad.provincia.id = :idProvincia "
            )
    List<Ciudad> findAllResumedByLocalidad_Provincia_Id(Long idProvincia);

    @Query(value =
            "select distinct c.* " +
            "from ciudad c " +
            "left join codigo_postal cp on c.id = cp.id_ciudad " +
            "left join apm_cpa ac on cp.id = ac.id_cpa " +
            "where ac.id_apm = :idApm and cp.id_provincia = :idProvincia"
        , nativeQuery = true)
    List<Ciudad> getCiudadesForProvinciaAndApm(Long idProvincia, Long idApm);

    List<Ciudad> findByNombreIgnoreCase(String nombre);

    @Modifying
    @Query("update Ciudad set inactivo = true where id = :id")
    void markInactive(Long id);
}
