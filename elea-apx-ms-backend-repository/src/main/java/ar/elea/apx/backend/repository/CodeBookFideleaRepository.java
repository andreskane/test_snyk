package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.FideleaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Juan Cruz Rompani
 */

@Repository
public interface CodeBookFideleaRepository extends JpaRepository<FideleaData, Long> {
    List<FideleaData> getCodeBookByNombre(String nombre);

    @Query(value = "select cb " +
            " from FideleaData cb " +
            " where (:nombre is null or cb.nombre = :nombre)" +
            " and (:apellido is null or cb.apellido = :apellido) " +
            " and (:tipoMatricula is null or :tipoMatricula = '' or cb.tipoMatricula = :tipoMatricula) " +
            " and (:nroMatricula is null or :nroMatricula = '' or cb.nroMatricula = :nroMatricula)")
    List<FideleaData> findCodeBookByNombreByApellidoByMatriculas(@Param("nombre") String name,
                                                              @Param("apellido") String apellido,
                                                              @Param("tipoMatricula") String tipoMatricula,
                                                              @Param("nroMatricula") String nroMatricula);

    @Query(value = "select cb " +
            " from FideleaData cb " +
            " where (:nombre is null or upper(cb.nombre) = upper(:nombre))" +
            " and (:apellido is null or upper(cb.apellido) = upper(:apellido)) " +
            " and (:tipoMatricula is null or :tipoMatricula = '' or cb.tipoMatricula = :tipoMatricula) " +
            " and (:nroMatricula is null or :nroMatricula = '' or cb.nroMatricula = :nroMatricula)")
    List<FideleaData> findCodeBookByNombreByApellidoByMatriculasMayuscula(@Param("nombre") String name,
                                                                 @Param("apellido") String apellido,
                                                                 @Param("tipoMatricula") String tipoMatricula,
                                                                 @Param("nroMatricula") String nroMatricula);

}

