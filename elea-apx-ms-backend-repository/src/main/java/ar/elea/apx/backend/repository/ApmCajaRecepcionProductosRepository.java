package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.ApmCajaRecepcionProductos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApmCajaRecepcionProductosRepository extends JpaRepository<ApmCajaRecepcionProductos, Long> {

}
