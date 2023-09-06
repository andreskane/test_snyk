package ar.elea.apx.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.elea.apx.backend.entity.LoyaltyDoctor;

/**
 * @author Ferney Escobar
 */
@Repository
public interface LoyaltyDoctorRepository extends JpaRepository<LoyaltyDoctor, Long>{
    @Query(value = "select  " +
            "   COUNT(DISTINCT(cm.doctor_id)) cantidad " +
            "from " +
            "   cartera_medica cm " +
            "join cartera_medica_estado cme ON (cme.id = cm.cartera_medica_estado_id) " +
            "join doctor on doctor.id = cm.doctor_id  " +
            "join apm on apm.id = cm.apm_id " +
            "join datos_visita dv on dv.id = cm.datos_visita_id " +
            "join usuario u on u.id=apm.gerente_regional_id " +
            "where  " +
            "   apm.inactivo = 0 " +
            "   and cm.inactivo = 0 " +
            "   and doctor.inactivo = 0  " +
            "   and u.inactivo = 0 " +
            "   and u.rol = 'GERENTE_REGIONAL' " +
            "   and (:gerenteId is null  " +
            "      or apm.gerente_regional_id = :gerenteId) " +
            "   and (:apmId is null or apm.id = :apmId)   " +
            "   and (:loyaltyId is null  " +
            "      or doctor.loyalty_id = :loyaltyId)  " +
            "   AND (cme.estado = 'ALTA_APROBADA' " +
            "      OR cme.estado = 'ACTIVACION_APROBADA' " +
            "      OR cme.estado = 'BAJA_RECHAZADA' " +
            "      OR cme.estado IS NULL " +
            "      OR (cme.estado = 'BAJA_APROBADA' " +
            "         AND (cme.fecha_confirmacion BETWEEN (  " +
            "         SELECT " +
            "            c.inicio " +
            "         from  " +
            "            ciclo c " +
            "         WHERE  " +
            "            c.id = :cicloId) AND (  " +
            "         SELECT  " +
            "            c.fin  " +
            "         from  " +
            "            ciclo c  " +
            "         WHERE  " +
            "            c.id = :cicloId))))", nativeQuery = true)
    Integer getCantidadMedicosPorLoyalty(Long gerenteId, Long apmId, Long cicloId, Long loyaltyId);
}
