package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.Asistente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Guillermo Nasi
 */
public interface AsistenteRepository extends JpaRepository<Asistente, Long> {
    Asistente findFirstByEmailIgnoreCaseOrTelefono(String email, String telefono);
}
