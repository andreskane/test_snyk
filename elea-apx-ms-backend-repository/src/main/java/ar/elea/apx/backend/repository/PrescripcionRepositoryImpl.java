package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.view.PrescripcionesComparativa;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PrescripcionRepositoryImpl {
  private final EntityManager entityManager;

    public PrescripcionRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<PrescripcionesComparativa> findPrescripcionesTrimestres(String cdgMedico, String cdgRegion, String cdgPeriodo1, String cdgPeriodo2) {
        TypedQuery<PrescripcionesComparativa> query = entityManager.createNamedQuery("prescripciones_medico", PrescripcionesComparativa.class);

        query.setParameter("cdg_medico", cdgMedico);
        query.setParameter("cdg_region", cdgRegion);
        query.setParameter("cdg_periodo1", cdgPeriodo1);
        query.setParameter("cdg_periodo2", cdgPeriodo2);

        return query.getResultList();
    }
}
