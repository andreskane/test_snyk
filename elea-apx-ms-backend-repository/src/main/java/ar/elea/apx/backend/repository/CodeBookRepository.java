package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.CodeBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Christian Corrales
 */
@Repository
public interface CodeBookRepository extends JpaRepository<CodeBook, Long> {
    List<CodeBook> getCodeBookByNombre(String nombre);

    @Query(value = "select cb " +
            " from CodeBook cb " +
            " where (:nombre is null or cb.nombre = :nombre)" +
            " and (:apellido is null or cb.apellido = :apellido) " +
            " and (:matriculaNacional is null or :matriculaNacional = '' or cb.matriculaNacional = :matriculaNacional) " +
            " and (:matriculaProvincial is null or :matriculaProvincial = '' or cb.matriculaProvincial = :matriculaProvincial)")
    List<CodeBook> findCodeBookByNombreByApellidoByMatriculas(@Param("nombre") String name,
                                                              @Param("apellido") String apellido,
                                                              @Param("matriculaNacional") String matriculaNacional,
                                                              @Param("matriculaProvincial") String matriculaProvincial);

    @Query(value = "select cb " +
            " from CodeBook cb " +
            " where (:nombre is null or upper(cb.nombre) = upper(:nombre))" +
            " and (:apellido is null or upper(cb.apellido) = upper(:apellido)) " +
            " and (:matriculaNacional is null or :matriculaNacional = '' or cb.matriculaNacional = :matriculaNacional) " +
            " and (:matriculaProvincial is null or :matriculaProvincial = '' or cb.matriculaProvincial = :matriculaProvincial)")
    List<CodeBook> findCodeBookByNombreByApellidoByMatriculasMayusculas(@Param("nombre") String name,
                                                              @Param("apellido") String apellido,
                                                              @Param("matriculaNacional") String matriculaNacional,
                                                              @Param("matriculaProvincial") String matriculaProvincial);

}
