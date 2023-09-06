package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.MaterialPromocionalAdjunto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author Christian Corrales
 */
public interface MaterialPromocionalAdjuntoRepository extends JpaRepository<MaterialPromocionalAdjunto, Long> {

    @Query(value = "SELECT ma FROM material_promocional_adjunto ma WHERE ma.materialPromocional.id in (:materialesIds)")
    List<MaterialPromocionalAdjunto> findMaterialAdjuntoByIds(List<Long> materialesIds);

    MaterialPromocionalAdjunto findMaterialPromocionalAdjuntoByMaterialPromocional_Id(Long id);

    @Query(value = "SELECT ma FROM material_promocional_adjunto ma WHERE ma.materialPromocional.id = :idMaterial")
    Optional<MaterialPromocionalAdjunto> findMaterialAdjuntoByIdMaterial(Long idMaterial);

}
