package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.NotificacionPush;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author Christian Corrales
 */
@Repository
public interface NotificacionPushRepository extends JpaRepository<NotificacionPush, Long>, JpaSpecificationExecutor<NotificacionPush> {
}
