package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.Prescripcion;
import ar.elea.apx.backend.entity.id.PrescripcionId;
import ar.elea.apx.backend.view.PrescripcionesComparativa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrescripcionRepository extends CrudRepository<Prescripcion, PrescripcionId> {
    @Query(value = "select p.cdg_mercado            as codMercado," +
            "            p.cdg_raiz               as codRaiz," +
            "            mercado.descripcion    as mercado," +
            "            product.descripcion    as producto," +
            "            p.cdg_periodo          as periodo," +
            "            c.categoria            as categoria," +
            "            p.cant_px              as cantidad," +
            "            p.part_producto_medico as porcentaje," +
            "            product.cdg_laboratorio as codigoLaboratorio," +
            "            laboratory.descripcion as nombreLaboratorio" +
            "    from SFN_audit_customer_prescription p" +
            "             left join SFN_audit_class mercado on p.cdg_mercado = mercado.cdg_mercado" +
            "             left join SFN_audit_product product on p.cdg_raiz = product.cdg_raiz" +
            "             left join SFN_audit_laboratory laboratory on laboratory.cdg_laboratorio = product.cdg_laboratorio" +
            "             left join SFN_audit_category c on (p.cdg_periodo = c.cdg_periodo and p.cdg_mercado = c.cdg_mercado and" +
            "                                                 p.cdg_medico = c.cdg_medico and p.cdg_region = c.cdg_region)" +
            "    where p.cdg_medico like :cdg_medico" +
            "      and p.cdg_region like :cdg_region" +
            "      and p.cdg_periodo in (:cdg_periodo1, :cdg_periodo2)", nativeQuery = true)
    List<PrescripcionesComparativa> findPrescripcionesTrimestres(@Param("cdg_medico") String cdgMedico,
                                                                 @Param("cdg_region") String cdgRegion,
                                                                 @Param("cdg_periodo1") String cdgPeriodo1,
                                                                 @Param("cdg_periodo2") String cdgPeriodo2);
}
