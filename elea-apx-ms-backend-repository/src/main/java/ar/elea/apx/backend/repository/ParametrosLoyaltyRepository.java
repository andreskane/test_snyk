package ar.elea.apx.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.elea.apx.backend.entity.ParametrosLoyalty;

/**
 * @author Ferney Escobar
 */
@Repository
public interface ParametrosLoyaltyRepository extends JpaRepository<ParametrosLoyalty, Long>{

}
