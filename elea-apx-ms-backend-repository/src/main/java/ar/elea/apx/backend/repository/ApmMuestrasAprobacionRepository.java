package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.ApmMuestrasAprobacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApmMuestrasAprobacionRepository extends JpaRepository<ApmMuestrasAprobacion, Long> {
    ApmMuestrasAprobacion findFirstByCodigoMuestra(Long codigoMuestra);
}
