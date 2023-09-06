package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.GrillaPromocional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * @author Guillermo Nasi
 */
public interface GrillaPromocionalRepository extends CrudRepository<GrillaPromocional, Long> {

    @Query("Select gp from grilla_promocional gp")
    List<GrillaPromocional> getAll();
    
    /*
     * Consulta las familias de la grilla promociona a partir de una fecha
     * las marcas coinciden con la familia de producto
     */
    @Query(value = "SELECT 'test' AS product_id FROM grilla_promocional WHERE 0>1", nativeQuery = true)
    List<String> getMarcasGrillaFecha(Date fecha);
}
