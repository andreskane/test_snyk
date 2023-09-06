package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.AccionApm;
import ar.elea.apx.backend.projection.AccionDoctorProjection;
import ar.elea.apx.backend.projection.ContactosPorGerenteProjection;
import ar.elea.apx.backend.projection.HistoricoEnvioMaterialProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Christian Corrales
 */
@Repository
public interface AccionApmRepository extends JpaRepository<AccionApm, Long> {
    List<AccionApm> findAccionesByApm_id_AndDoctores_id(Long apmId, Long doctorId);
    List<AccionApm> findAllByApm_IdAndApm_InactivoIsFalse(Long apmId);

    @Query(value = "SELECT COUNT(*) AS totalContactos, a.gerente_regional_id as gerenteId FROM accion_apm ap " +
            "JOIN apm a ON a.id =ap.apm_id " +
            "JOIN accion_apm_doctor aad on aad.id_accion = ap.id " +
            "WHERE  " +
            "(:apmId IS NULL OR ap.apm_id = :apmId ) " +
            "and (:gerenteId is null or CAST(a.gerente_regional_id as varchar) in (SELECT value FROM STRING_SPLIT(convert(varchar(max), :gerenteId), ','))) "+
            "AND ap.created_date  >= (" +
            "SELECT c.inicio " +
            "FROM ciclo c " +
            "WHERE c.id = :cicloId) " +
            "AND ap.created_date <= (" +
            "SELECT c.fin " +
            "FROM ciclo c " +
            "WHERE c.id = :cicloId) group by a.gerente_regional_id", nativeQuery = true)
    List<ContactosPorGerenteProjection> getTotalContactosCiclo(String gerenteId, Long apmId, Long cicloId);

    @Query(value = "SELECT aad.id_doctor as idDoctor, COUNT(aad.id_doctor) AS totalContactos " +
            "FROM accion_apm_doctor aad where aad.id_doctor in :doctors GROUP by aad.id_doctor", nativeQuery = true)
    List<AccionDoctorProjection> findAccionesByDoctores(List<Long> doctors);

    @Query(value = "SELECT aa.id as id, " +
            "mp.id as idMaterial," +
            "mp.nombre  nombreMaterial," +
            "CONCAT(d.primerNombre, ' ', d.segundoNombre, ' ', d.primerApellido, ' ', d.segundoApellido) as doctor," +
            "FORMAT(e2.fecha_envio, 'dd/MM/yyyy') as fechaEnvio," +
            "l.nombre as linea," +
            "p.nombre as producto," +
            "e.nombre as especialidad," +
            "mp.archivo_extension as tipo," +
            "e2.asunto as asunto," +
            "e2.mensaje as mensaje " +
            "FROM accion_apm aa " +
            "JOIN accion_apm_doctor aad ON aad.id_accion = aa.id " +
            "JOIN doctor d ON d.id=aad.id_doctor " +
            "LEFT JOIN accion_apm_items aai ON aai.id_accion_apm = aa.id " +
            "LEFT JOIN email e2 ON e2.id_accion=aa.id " +
            "JOIN apm a ON a.id= aa.apm_id " +
            "LEFT JOIN material_promocional mp ON mp.id=aai.id_material_promocional " +
            "LEFT JOIN producto p ON p.id=mp.producto_id " +
            "LEFT JOIN especialidad e ON e.id =mp.especialidad_id " +
            "LEFT JOIN material_promocional_linea_asociada mpla on mpla.id_material_promocional = mp.id " +
            "LEFT JOIN linea l ON l.id = mpla.id_linea " +
            "WHERE aa.apm_id = :idApm",
            countQuery = "SELECT COUNT(*) " +
                    "FROM accion_apm aa " +
                    "JOIN accion_apm_doctor aad ON aad.id_accion = aa.id " +
                    "JOIN doctor d ON d.id=aad.id_doctor " +
                    "LEFT JOIN accion_apm_items aai ON aai.id_accion_apm = aa.id " +
                    "LEFT JOIN email e2 ON e2.id_accion=aa.id " +
                    "JOIN apm a ON a.id= aa.apm_id " +
                    "LEFT JOIN material_promocional mp ON mp.id=aai.id_material_promocional " +
                    "LEFT JOIN producto p ON p.id=mp.producto_id " +
                    "LEFT JOIN especialidad e ON e.id =mp.especialidad_id " +
                    "LEFT JOIN material_promocional_linea_asociada mpla on mpla.id_material_promocional = mp.id " +
                    "LEFT JOIN linea l ON l.id = mpla.id_linea " +
                    "WHERE aa.apm_id = :idApm",
            nativeQuery = true)
    Page<HistoricoEnvioMaterialProjection> getHistoricoEnvios(Long idApm, Pageable pageable);

}
