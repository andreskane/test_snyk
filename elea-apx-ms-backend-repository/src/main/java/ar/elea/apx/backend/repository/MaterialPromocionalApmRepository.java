package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.MaterialPromocionalApm;
import ar.elea.apx.backend.view.MaterialPromocionalApmView;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Felipe Jimenez
 */
public interface MaterialPromocionalApmRepository extends CrudRepository<MaterialPromocionalApm, Long> {

    @Query(value =
            "select new ar.elea.apx.backend.view.MaterialPromocionalApmView(mp, mpa.tieneMaterial, mpa.id) " +
                    "from material_promocional mp " +
                    "left join material_promocional_apm mpa on mpa.materialPromocional.id = mp.id and mpa.apm.id = :apmId " +
                    "where mp.inactivo = false ")
    List<MaterialPromocionalApmView> findMaterialPromocionalApm(Long apmId);



    @Modifying
    @Query(value = "IF EXISTS(select * from material_promocional_apm mpa where mpa.material_promocional_id = :materialPromocionalId and mpa.apm_id = :apmId) " +
            " UPDATE material_promocional_apm SET tiene_material = :tieneMaterial WHERE material_promocional_id = :materialPromocionalId and apm_id = :apmId " +
            " ELSE INSERT INTO material_promocional_apm (apm_id, material_promocional_id, tiene_material) VALUES (:apmId, :materialPromocionalId, :tieneMaterial)"
            ,nativeQuery = true)
    Integer insertOrUpdateMaterialPromocionalApm(Long materialPromocionalId, Long apmId, Boolean tieneMaterial);

}


