package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.PrescripcionMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PrescripcionMedicoRepository extends JpaRepository<PrescripcionMedico, String> {

    PrescripcionMedico findPrescripcionMedicoByMatricula(String matricula);

    @Query(value = "select * FROM SFN_audit_customer c " +
            "where coalesce(c.matricula,'') like :matriculaNacional OR " +
            "(coalesce(c.matricula,'') like :matriculaProvincial AND c.cdg_region in (:regiones))", nativeQuery = true)
    List<PrescripcionMedico> findSfnCustomerByMatricula(String matriculaNacional, String matriculaProvincial, List<String> regiones);
}
