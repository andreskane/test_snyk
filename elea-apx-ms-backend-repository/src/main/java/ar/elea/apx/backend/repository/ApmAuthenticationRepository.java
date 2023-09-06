package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.ApmAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Guillermo Nasi
 */
@Repository
public interface ApmAuthenticationRepository extends JpaRepository<ApmAuthentication, Long> {
    ApmAuthentication findByTokenLike(String token);

    boolean existsByApmIdAndTokenLike(Long id, String token);
}
