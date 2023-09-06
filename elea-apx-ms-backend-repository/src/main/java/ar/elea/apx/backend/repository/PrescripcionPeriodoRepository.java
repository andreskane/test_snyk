package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.PrescripcionPeriodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PrescripcionPeriodoRepository extends JpaRepository<PrescripcionPeriodo, String> {

    @Query(value = "select top 2 * from SFN_audit_period order by fecha  desc", nativeQuery = true)
    List<PrescripcionPeriodo> getLastTwoPeriods();
}
