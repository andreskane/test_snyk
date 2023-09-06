package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.RegionProvincia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Felipe Jimenez
 */
public interface RegionProvinciaRepository extends CrudRepository<RegionProvincia, Long> {

    @Query(value = "FROM region_provincia rp WHERE rp.provincia.id = :provinciaId")
    List<RegionProvincia> findRegionByProvinciaId(Long provinciaId);
}
