package ar.elea.apx.backend.repository;


import java.util.List;

import ar.elea.apx.backend.projection.EspecialidadSubEspecialidadProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ar.elea.apx.backend.entity.EspeciaSubespecialidad;

/**
 * @author Alex Cucho
 */
public interface SubEspecialidadRepository extends JpaRepository<EspeciaSubespecialidad, Long> {  

    @Query(value = 
    "SELECT se.* FROM especiaSubespecialidad se " +
    "INNER JOIN especialidad e ON se.id_especialidad = e.id " +
    "Where se.id_especialidad = :idEspecialidad and " +
            " e.inactivo = '0' and se.inactivo = '0'"
    , nativeQuery = true)
    List<EspeciaSubespecialidad> getSubEspecialidades(Long idEspecialidad);

    @Query(value =
            "SELECT se.id as idSubEspecialidad,se.nombre as nombreSubEspecialidad,e.id as idEspecialidad, e.nombre as nombreEspecialidad FROM especiaSubespecialidad se " +
                    "RIGHT JOIN especialidad e ON se.id_especialidad = e.id and " +
                    "se.inactivo = '0' WHERE " +
                    "e.inactivo = '0' ", nativeQuery = true)
    List<EspecialidadSubEspecialidadProjection> getEspecialidadesSubEspecialidades();

    @Query(value =
            "update especiaSubespecialidad set inactivo = 1 where id_especialidad = :idEspecialidad ", nativeQuery = true)
    @Modifying
    void updateInactivoSubEspecialidadByIdEspecialidad(Long idEspecialidad);

 }

