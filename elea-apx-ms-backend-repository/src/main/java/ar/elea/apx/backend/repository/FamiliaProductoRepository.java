package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.FamiliaProducto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FamiliaProductoRepository extends JpaRepository<FamiliaProducto, Long> {
    List<FamiliaProducto> getFamiliaProductoById(Long id);
    List<FamiliaProducto> findAllByOrderByNombreAsc();
}
