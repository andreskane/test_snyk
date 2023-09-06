package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.Institucion;
import ar.elea.apx.backend.projection.ReporteInstitucionesExcelProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Felipe Jimenez
 */
public interface InstitucionRepository extends JpaRepository<Institucion, Long> {
    Boolean existsByNombre(String nombre);

    List<Institucion> findAllByNombreStartingWith(String nombre);

    @Query(value = "SELECT i.* " +
            "FROM Institucion i  " +
            "WHERE " +
            " i.nombre COLLATE Latin1_general_CI_AI Like %:nombre% COLLATE Latin1_general_CI_AI" +
            " AND id_direccion IS NOT NULL and " +
            "i.inactivo = '0' ",
    nativeQuery = true)
    List<Institucion> findAllByNombreIgnoreCaseAndAccentStartingWith(String nombre);

    @Query(value =
            "select i.* from Institucion i " +
                    "left join apm_institucion ai on i.id = ai.id_institucion " +
                    "where ai.id_apm = :id and i.inactivo = '0'",
            nativeQuery = true)
    List<Institucion> findInstitucionesApm(Long id);

    @Modifying
    @Query(value = "insert into apm_institucion values (:idApm, :idInstitucion)", nativeQuery = true)
    void asociarApmInstitucion(Long idInstitucion, Long idApm);

    @Modifying
    @Query(value = "delete from apm_institucion where id_institucion = :idInstitucion and id_apm = :idApm", nativeQuery = true)
    void desasociarApmInstitucion(Long idInstitucion, Long idApm);


    @Query(value = "SELECT i " +
            "FROM Institucion i " +
            "WHERE " +
            " (coalesce(i.nombre,'') like lower(concat('%',:nombre,'%'))) AND " +
            " (:idProvincia is null or i.direccion.codigoPostal.provincia.id = :idProvincia) AND " +
            " (:idLocalidad is null or i.direccion.codigoPostal.localidad.id = :idLocalidad) AND " +
            " (:idCiudad is null or i.direccion.codigoPostal.ciudad.id = :idCiudad) AND " +
            "i.inactivo = '0'")
    List<Institucion> findInstitucionNombreIdLocalidadIdProvinciaIdCiudad(String nombre, Long idLocalidad, Long idProvincia, Long idCiudad);

    @Query(value = "select i from Institucion i where " +
            "(coalesce(i.nombre,'') like lower(concat('%',:nombre,'%'))) AND " +
            "(coalesce(i.direccion.codigoPostal.cpa,'') like lower(concat('%',:cp,'%'))) and " +
            "(:idProvincia is null or i.direccion.codigoPostal.provincia.id = :idProvincia) AND " +
            "(:idLocalidad is null or i.direccion.codigoPostal.localidad.id = :idLocalidad) AND " +
            "(:idCiudad is null or i.direccion.codigoPostal.ciudad.id = :idCiudad) AND " +
            "(:numero is null or i.direccion.numero = :numero OR i.direccion.codigoPostal.calle.nombre like lower(concat('%',CAST(:numero as string),'%'))) AND " +
            "(:calle is null or :calle like '' or i.direccion.codigoPostal.calle.nombre like lower(concat('%',:calle,'%'))) AND " +
            "i.inactivo = '0' ",
    countQuery = "select count(i) from Institucion i where " +
            "(coalesce(i.nombre,'') like lower(concat('%',:nombre,'%'))) AND " +
            "(coalesce(i.direccion.codigoPostal.cpa,'') like lower(concat('%',:cp,'%'))) and " +
            "(:idProvincia is null or i.direccion.codigoPostal.provincia.id = :idProvincia) AND " +
            "(:idLocalidad is null or i.direccion.codigoPostal.localidad.id = :idLocalidad) AND " +
            "(:idCiudad is null or i.direccion.codigoPostal.ciudad.id = :idCiudad) AND " +
            "(:numero is null or i.direccion.numero = :numero OR i.direccion.codigoPostal.calle.nombre like lower(concat('%',CAST(:numero as string),'%'))) AND " +
            "(:calle is null or :calle like '' or i.direccion.codigoPostal.calle.nombre like lower(concat('%',:calle,'%'))) AND " +
            "i.inactivo = '0' ")
    Page<Institucion> findInstitucionCpLocalidadProvinciaCiudad(String nombre, String cp, Long idLocalidad, Long idProvincia, Long idCiudad, Long numero, String calle, Pageable page);

    @Modifying
    @Query(value = "update institucion set inactivo='1' where id = :idInstitucion ", nativeQuery = true)
    void eliminarInstitucion(Long idInstitucion);

    @Query(value = "EXEC Reporte_Instituciones", nativeQuery = true)
    List<ReporteInstitucionesExcelProjection> getReporteInstituciones();
}
