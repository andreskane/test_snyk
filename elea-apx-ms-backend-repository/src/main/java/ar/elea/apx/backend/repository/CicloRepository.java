package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.Ciclo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Felipe Jimenez
 */
public interface CicloRepository extends CrudRepository<Ciclo, Long> {
    /**
     * Find a Ciclo starting before a Date and ending after other date
     * @param inicioBefore
     * @param finAfter
     * @return Ciclo that meets the given dates
     */
    Optional<Ciclo> findCicloByInicioBeforeAndFinAfter(Date inicioBefore, Date finAfter);

    /**
     * Find a Ciclo for nombre column
     * @param nombre
     * @return Ciclo that meets the given dates
     */
    Optional<Ciclo> findCicloByNombreIgnoreCase(String nombre);

    @Query("select c from Ciclo c where c.nombre like %:anio% ")
    List<Ciclo> findByCicloByAnio(int anio);
}
