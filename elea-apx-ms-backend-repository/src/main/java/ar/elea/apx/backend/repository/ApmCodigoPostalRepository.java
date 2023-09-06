package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.ApmCodigoPostal;
import ar.elea.apx.backend.entity.ApmCodigoPostalId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApmCodigoPostalRepository extends JpaRepository<ApmCodigoPostal, ApmCodigoPostalId> {
}
