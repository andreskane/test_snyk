package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.ApmCajaRecepcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApmCajaRecepcionRepository extends JpaRepository<ApmCajaRecepcion, Long> {

}
