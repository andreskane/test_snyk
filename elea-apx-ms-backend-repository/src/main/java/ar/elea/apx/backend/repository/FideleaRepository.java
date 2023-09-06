package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.FideleaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Juan Cruz Rompani
 */

@Repository
public interface FideleaRepository extends JpaRepository<FideleaData, Long> {

}
