package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.MaterialPromocional;
import ar.elea.apx.backend.projection.MaterialPromocionalProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Felipe Jimenez
 */
public interface MaterialPromocionalRepository extends JpaRepository<MaterialPromocional, Long> {

    List<MaterialPromocional> findAllByEspecialidadIdAndInactivoIsFalseAndEstadoEliminadoIsFalse(Long especialidadId);

    List<MaterialPromocional> findAllByTipoIdAndInactivoIsFalseAndEstadoEliminadoIsFalse(Long tipoId);

    List<MaterialPromocional> findAllByInactivoIsFalseAndEstadoEliminadoIsFalse();

    @Query("SELECT matProm From material_promocional matProm where matProm.codigo = :codigo and matProm.tipo.id <> 2")
    MaterialPromocional findByCodigo(Long codigo);

    @Query(value =
            "SELECT  " +
                    "mp.id, " +
                    "mp.nombre, " +
                    "mp.archivo_extension as ArchivoExtension, " +
                    "convert(varchar(50),mp.created_date,103) as fecha, " +
                    "l.id as IdLinea, " +
                    "l.nombre as NombreLinea, " +
                    "e.id as IdEspecialidad, " +
                    "e.nombre as NombreEspecialidad, " +
                    "p.id as IdProducto, " +
                    "p.nombre as NombreProducto, " +
                    "c.id as IdCiclo, " +
                    "c.nombre as NombreCiclo, " +
                    "mp.inactivo," +
                    "mp.tamanio " +
                    "FROM material_promocional mp  " +
                    "INNER JOIN material_promocional_linea_asociada mpla on mpla.id_material_promocional = mp.id  " +
                    "LEFT JOIN material_promocional_especialidades mpes on mpes.id_material_promocional = mp.id  " +
                    "LEFT JOIN material_promocional_productos mppr on mppr.id_material_promocional = mp.id  " +
                    "INNER JOIN material_promocional_ciclos mpci on mpci.id_material_promocional = mp.id  " +
                    "LEFT JOIN especialidad e on mpes.id_especialidades = e.id  " +
                    "LEFT JOIN familia_producto p on p.id = mppr.id_producto " +
                    "INNER JOIN ciclo c on c.id = mpci.id_ciclo " +
                    "INNER JOIN linea l on l.id = mpla.id_linea  " +
                    "where " +
                    " (coalesce(mp.nombre,'') like lower(concat(:nombre,'%'))) and " +
                    "mp.estado_eliminado = '0' " +
                    "and (:lineas is null or :lineas = '' or CAST(l.id as varchar) in (SELECT value FROM STRING_SPLIT(:lineas, ','))) " +
                    "AND (:tipo IS NULL OR archivo_extension = :tipo) " +
                    "AND (:idEspecialidad IS NULL OR e.id = :idEspecialidad) " +
                    "AND (:idProducto IS NULL OR p.id = :idProducto) " +
                    "AND (:idCiclo IS NULL OR c.id = :idCiclo) " +
                    "order by mp.created_date desc "
            , nativeQuery = true)
    List<MaterialPromocionalProjection> findMaterialPromocionalsByCriteria(String nombre, String lineas, String tipo, Long idEspecialidad, Long idProducto, Long idCiclo);

    @Query("SELECT m From material_promocional m where m.nombre=:nombre and archivoExtension=:extension and inactivo=0 and estadoEliminado=0")
    List<MaterialPromocional> findAllByNombreExtension(String nombre, String extension);
}
