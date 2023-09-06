package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.EMail;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Christian Corrales
 */
public interface EMailRepository extends CrudRepository<EMail, Long> {
}
