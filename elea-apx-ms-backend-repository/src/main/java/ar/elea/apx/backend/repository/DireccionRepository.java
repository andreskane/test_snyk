package ar.elea.apx.backend.repository;


import ar.elea.apx.backend.entity.Direccion;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Edison González
 */
@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {
    @Cacheable(value = "DireccionRepository.findDireccionByCodigoPostal_IdAndNumeroAndDetalleIgnoreCase")
    Optional<List<Direccion>> findDireccionByCodigoPostal_IdAndNumeroAndDetalleIgnoreCase(Long idCodigoPostal,
                                                                                          Long numero, String detalle);
}
