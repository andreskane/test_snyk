package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.CodigoPostal;
import ar.elea.apx.backend.projection.CodigoPostalProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Guillermo Nasi
 */
@Repository
public interface CodigoPostalRepository extends JpaRepository<CodigoPostal, Long>, JpaSpecificationExecutor<CodigoPostal> {

    @Query(value = "insert into apm_cpa (id_apm, id_cpa) (select :idApm, id from codigo_postal where cpa like concat('%', :cp, '%'))",
            nativeQuery = true)
    @Modifying
    void assignCPsToApm(String cp, Long idApm);

    @Query(value = "select distinct substring(cp.cpa, 2, 4) as codigoPostal " +
            "from codigo_postal cp " +
            "         left join apm_cpa ac on cp.id = ac.id_cpa " +
            "where ac.id_apm = :idApm",
            nativeQuery = true)
    List<String> getApmCPs(Long idApm);

    @Modifying
    @Query(value = "delete from apm_cpa where id_apm = :idApm and id_cpa in (select id from codigo_postal where cpa like concat('%', :cp, '%'))",
            nativeQuery = true)
    void unassignCPFromApm(Long idApm, String cp);

    @Query(value =
            "select * from codigo_postal " +
                    "left join apm_cpa ac on codigo_postal.id = ac.id_cpa " +
                    "where ac.id_apm = :apmId"
            , nativeQuery = true)
    List<CodigoPostal> findAllForApm(Long apmId);

    @Query("select cpa from CodigoPostal cpa where " +
            "cpa.provincia.id = :provinciaId and cpa.ciudad.id = :ciudadId and cpa.calle.id = :calleId " +
            "and cpa.desde <= :numero and cpa.hasta >= :numero and cpa.parImpar like :parImpar")
    Optional<CodigoPostal> findOneForDireccion(Long provinciaId, Long ciudadId, Long calleId, Long numero, String parImpar);

    @Query(value =  "select * from codigo_postal where id_calle = :calleId", nativeQuery = true)
    List<CodigoPostal> findHeightsForStreet(Long calleId);

    @Query(value = "select distinct substring(codigo_postal.cpa, 2, 4) as codigoPostal, " +
            "                p.id                               as provinciaId, " +
            "                p.nombre                           as provinciaNombre, " +
            "                l.id                               as localidadId, " +
            "                l.nombre                           as localidadNombre, " +
            "                c.id                               as ciudadId, " +
            "                c.nombre                           as ciudadNombre " +
            "from codigo_postal " +
            "         left join provincia p on codigo_postal.id_provincia = p.id " +
            "         left join localidad l on codigo_postal.id_localidad = l.id " +
            "         left join ciudad c on codigo_postal.id_ciudad = c.id " +
            "where codigo_postal.id_provincia = :provinciaId " +
            " and (:localidadId is null or codigo_postal.id_localidad = :localidadId) " +
            " and (:ciudadId is null or codigo_postal.id_ciudad = :ciudadId) " +
            " and (:cp is null or codigo_postal.cpa like concat('%', :cp, '%')) " +
            "order by codigoPostal",
            countQuery = "select distinct count(codigo_postal.cpa)" +
                    "from codigo_postal " +
                    "         left join provincia p on codigo_postal.id_provincia = p.id " +
                    "         left join localidad l on codigo_postal.id_localidad = l.id " +
                    "         left join ciudad c on codigo_postal.id_ciudad = c.id " +
                    "where codigo_postal.id_provincia = :provinciaId " +
                    " and (:localidadId is null or codigo_postal.id_localidad = :localidadId) " +
                    " and (:ciudadId is null or codigo_postal.id_ciudad = :ciudadId) " +
                    " and (:cp is null or codigo_postal.cpa like concat('%', :cp, '%'))",
            nativeQuery = true)
    Page<CodigoPostalProjection> findCodigosPostales(Long provinciaId, Long localidadId, Long ciudadId, String cp, Pageable pageable);

    CodigoPostal findFirstCodigoPostalByCpaIgnoreCaseAndParImparIgnoreCaseAndProvincia_IdAndLocalidad_IdAndCiudad_IdAndCalle_IdAndDesdeAndHasta(String cpa, String ParImpar, Long idProvincia, Long idLocalidad, Long idCiudad, Long idCalle, Long desde, Long hasta);
    CodigoPostal findFirstCodigoPostalByCpaIgnoreCaseAndProvincia_IdAndLocalidad_IdAndCiudad_IdAndCalle_Id(String cpaOrCp, Long idProvincia, Long idLocalidad, Long idCiudad, Long idCalle);

    @Procedure(name = "CodigoPostal.buscarDireccion")
    Long getCodigoPostalProcedure(@Param("id_pais") Long idPais,
                                  @Param("provincia") String provincia,
                                  @Param("localidad") String localidad,
                                  @Param("ciudad") String ciudad,
                                  @Param("calle") String calle,
                                  @Param("cpa") String cpa,
                                  @Param("tipo_calle") Long tipoCalle,
                                  @Param("numero") Long numero,
                                  @Param("detalle") String detalle,
                                  @Param("piso") String piso,
                                  @Param("departamento") String departamento,
                                  @Param("otros") String otros);
}
