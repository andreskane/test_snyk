package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.Apm;
import ar.elea.apx.backend.entity.GeodataInfo;
import ar.elea.apx.backend.projection.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Felipe Jimenez
 */
@Repository
public interface ApmRepository extends JpaRepository<Apm, Long>, JpaSpecificationExecutor<Apm> {

    /**
     * Find an APM by email
     *
     * @param userId
     * @return APM
     */
    Apm findApmByEmail(String userId);

    List<Apm> findByGerenteRegional_Id(Long id);

    /**
     * Find apm by it's Azure Active Directory ObjectID
     *
     * @param adOid
     * @return
     */
    Apm findByAdOid(String adOid);

    @Query("select a.profileImage from Apm a where a.id = :id")
    byte[] getProfileImage(Long id);

    @Query("update Apm a set a.inactivo = true where a.id = :id")
    void logicalDelete(Long id);

    @Query("select a.id from Apm a where a.gerenteRegional.id = :id")
    List<Long> getIdForGerenteRegionalId(Long id);

    @Modifying
    @Query("update Apm a set a.diasRegistroVisita = :dias")
    void updateDiasRegistroVisitaApm(Integer dias);

    @Query(value = "select a.id as id, a.primerNombre as nombre, a.primerApellido as apellido " +
            "from apm a " +
            "left join cartera_medica cm on a.id = cm.apm_id " +
            "where cm.doctor_id = :doctorId " +
            "AND cm.inactivo = 0 " +
            "AND a.inactivo = 0 ",
            nativeQuery = true)
    List<ApmProjection> getDoctorsApmProjection(Long doctorId);
    
    @Query(value = "select a.id as id, a.primerNombre as nombre, a.primerApellido as apellido, cm.doctor_id doctorId " +
            "from apm a " +
            "left join cartera_medica cm on a.id = cm.apm_id " +
            "where cm.doctor_id in (:doctorIds) " +
            "AND cm.inactivo = 0 " +
            "AND cm.apm_id != :apmId " +
            "AND a.inactivo = 0 ",
            nativeQuery = true)
    List<ApmProjection> getDoctorsApmProjection(List<Long> doctorIds, Long apmId);

    Optional<Apm> findByEmailStartingWith(String email);

    @Query(value = "SELECT DISTINCT a.* FROM apm a " +
            "WHERE a.inactivo = 0 " +
            "and not EXISTS (SELECT * FROM agenda ag where ag.apm_id=a.id " +
            "and agenda_tipo_id =2 " +
            "and ( CAST(inicio as date ) = CAST(:primerDia as date) " +
            "or CAST(inicio as date) = CAST(:segundoDia as date)))",
            nativeQuery = true)
    List<Apm> findApmSinProgramacion(LocalDate primerDia, LocalDate segundoDia);


    /**
     * GeodataInfo Apm projection
     * @param apmId the apmId
     * @return a GeodataInfo object
     */
    @Query(value = "select a.apellido as apmApellido, a.nombre as apmNombre, u.apellido as gerenteApellido, a.aliasGeodata as aliasGeodata, u.aliasGeodata as aliasGeodataGte " +
            "from Apm a " +
            "left join a.gerenteRegional u " +
            "where a.id = :apmId")
    GeodataInfo geodataInfo(Long apmId);

    @Query(value = "SELECT a.id, a.primerNombre nombre, a.primerApellido apellido, a.legajo " +
            "FROM apm a " +
            "JOIN usuario u ON u.id = a.gerente_regional_id " +
            "WHERE a.inactivo =0 " +
            "AND u.inactivo =0 " +
            "AND (:gerenteId IS NULL OR a.gerente_regional_id = :gerenteId) " +
            "AND (:nombreCompleto IS NULL OR :nombreCompleto = '' OR CONCAT(upper(a.primerNombre), upper(a.segundoNombre), upper(a.primerApellido), upper(a.segundoApellido)) COLLATE Latin1_general_CI_AI like concat('%',upper(:nombreCompleto),'%')) " +
            "AND (:gerente IS NULL OR :gerente = '' OR CONCAT(u.nombre, u.apellido) COLLATE Latin1_general_CI_AI like concat('%',:gerente,'%'))",
            countQuery = "SELECT count(a.id) " +
                    "FROM apm a " +
                    "JOIN usuario u ON u.id = a.gerente_regional_id " +
                    "WHERE a.inactivo =0 " +
                    "AND u.inactivo =0 " +
                    "AND (:gerenteId IS NULL OR a.gerente_regional_id = :gerenteId) " +
                    "AND (:nombreCompleto IS NULL OR :nombreCompleto = '' OR CONCAT(upper(a.primerNombre), upper(a.segundoNombre), upper(a.primerApellido), upper(a.segundoApellido)) COLLATE Latin1_general_CI_AI like concat('%',upper(:nombreCompleto),'%')) " +
                    "AND (:gerente IS NULL OR :gerente = '' OR CONCAT(u.nombre, u.apellido) COLLATE Latin1_general_CI_AI like concat('%',:gerente,'%'))"
            , nativeQuery = true)
    Page<ApmProjection> getApmsByGerente(Long gerenteId, String nombreCompleto, Pageable pageable, String gerente);

    @Query(value = "SELECT  apm.gerente_regional_id,  " +
            "        apm.id AS apmId,  " +
            "        apm.primerApellido + ' ' + COALESCE( apm.segundoApellido, ' ') AS apellidos,  " +
            "        apm.primerNombre + ' ' + COALESCE( apm.segundoNombre, ' ') AS nombres,  " +
            "        SUM (  " +
            "         CASE  " +
            "          WHEN calendario.fecha = agenda.fecha THEN  " +
            "           agenda.visitas  " +
            "          ELSE  " +
            "           0 " +
            "         END  " +
            "        ) OVER (  " +
            "         PARTITION BY  " +
            "          apm.id " +
            "        ) totalVisitasMes,  " +
            "  SUM (  " +
            "   CASE  " +
            "    WHEN  calendario.fecha = agenda.fecha  " +
            "    AND  agenda.visitas > 0  " +
            "    AND  (   " +
            "                calendario.festivo != 'S' " +
            "                 AND calendario.nombre_dia_semana NOT IN ( " +
            "                  'Sábado    ',  " +
            "                  'Domingo   ' " +
            "                 )  " +
            " ) " +
            "    THEN  " +
            "     1 " +
            "    ELSE  " +
            "     0 " +
            "    END  " +
            "  ) OVER (  " +
            "   PARTITION BY  " +
            "    apm.id " +
            "  ) diasVisitasMes,   " +
            "        FORMAT (          " +
            "   CASE  " +
            "    WHEN  " +
            "     SUM (  " +
            " CASE  " +
            "  WHEN  calendario.fecha = agenda.fecha  " +
            "  AND  agenda.visitas > 0  " +
            "  AND  (   " +
            "                 calendario.festivo != 'S' " +
            "                 AND calendario.nombre_dia_semana NOT IN ( " +
            "                      'Sábado    ',  " +
            "                      'Domingo   ' " +
            "                  )  " +
            "  ) " +
            "  THEN  " +
            "   1 " +
            "  ELSE  " +
            "   0 " +
            " END  " +
            "     ) OVER (  " +
            " PARTITION BY  " +
            "  apm.id " +
            "     )  = 0  " +
            "    THEN  " +
            "     CAST( 0 AS decimal( 6, 2 ) )  " +
            "    ELSE  " +
            "     CAST(  " +
            " SUM (  " +
            "  CASE  " +
            "   WHEN calendario.fecha = agenda.fecha THEN  " +
            "    agenda.visitas  " +
            "   ELSE  " +
            "    0 " +
            "  END  " +
            " ) OVER (  " +
            "  PARTITION BY  " +
            "   apm.id " +
            " )  " +
            " AS decimal( 6, 2 )  " +
            "     ) / " +
            "     CAST(  " +
            " SUM (  " +
            "  CASE  " +
            "   WHEN  calendario.fecha = agenda.fecha  " +
            "   AND  agenda.visitas > 0 /* Se hizo al menos una visita */ " +
            "   AND  (  /* El día es hábil */ " +
            "                  calendario.festivo != 'S' " +
            "                  AND calendario.nombre_dia_semana NOT IN ( " +
            "                       'Sábado    ',  " +
            "                       'Domingo   ' " +
            "                   )  " +
            "   ) " +
            "   THEN  " +
            "    1 " +
            "   ELSE  " +
            "    0 " +
            "  END  " +
            " ) OVER (  " +
            "  PARTITION BY  " +
            "   apm.id " +
            " )  " +
            " AS decimal( 6, 2 )  " +
            "     )  " +
            "   END,  " +
            "   '0.00'  " +
            "  ) promedio,  " +
            "        calendario.dia dia, " +
            "        SUM (  " +
            "         CASE  " +
            "          WHEN calendario.fecha = agenda.fecha THEN  " +
            "           agenda.visitas  " +
            "          ELSE  " +
            "           0 " +
            "         END  " +
            "        ) cantidad, " +
            "        SUM (  " +
            "            CASE  " +
            "                WHEN calendario.festivo = 'S' " +
            "                OR calendario.nombre_dia_semana IN ( " +
            "                    'Sábado    ',  " +
            "                    'Domingo   ' " +
            "                ) THEN  " +
            "                    0 " +
            "                ELSE  " +
            "                    1 " +
            "            END  " +
            "        ) OVER (  " +
            "         PARTITION BY  " +
            "          apm.id " +
            "        ) diasHabilesMes, apm.gerente_regional_id as gerenteRegionalId " +
            "FROM    dbo.apm  " +
            "CROSS JOIN    dbo.calendario  " +
            "LEFT JOIN    (  " +
            "   SELECT  apm_id, " +
            "     DAY( agenda.inicio ) AS dia,  " +
            "     MONTH( agenda.inicio ) AS mes,  " +
            "     YEAR( agenda.inicio ) AS ano,  " +
            "     agenda.inicio fecha, " +
            "     COUNT(*) visitas  " +
            "   FROM  dbo.agenda  " +
            "   WHERE   agenda.agenda_tipo_id = 0 " +
            "   AND  agenda.visita_exitosa = 1 " +
            "   GROUP BY  " +
            "     apm_id,  " +
            "     DAY( agenda.inicio ), " +
            "     MONTH( agenda.inicio ),  " +
            "     YEAR( agenda.inicio ),  " +
            "     agenda.inicio  " +
            "  ) agenda  " +
            "ON      agenda.apm_id = apm.id  " +
            "AND     calendario.dia = agenda.dia " +
            "AND     calendario.mes = agenda.mes " +
            "AND     calendario.ano = agenda.ano " +
            "WHERE   (:idGerente is null or apm.gerente_regional_id = :idGerente)  " +
            "AND     calendario.mes = :mes  " +
            "AND     calendario.ano = :ano  " +
            "AND     apm.inactivo = 0  " +
            "AND  (:nombre IS NULL OR apm.primerNombre = :nombre)  " +
            "AND     CONCAT(apm.primerNombre,' ',apm.primerApellido) COLLATE Latin1_general_CI_AI like concat('%',:nombreCompleto,'%')  " +
            "GROUP BY  " +
            "        apm.gerente_regional_id,  " +
            "  apm.id,  " +
            "        apm.primerApellido + ' ' + COALESCE( apm.segundoApellido, ' '),  " +
            "        apm.primerNombre + ' ' + COALESCE( apm.segundoNombre, ' '),  " +
            "        calendario.dia, " +
            "        calendario.fecha, " +
            "        agenda.fecha, " +
            "  agenda.visitas, " +
            "      calendario.festivo, " +
            "      calendario.nombre_dia_semana  " +
            "ORDER BY  " +
            "        apm.primerApellido + ' ' + COALESCE( apm.segundoApellido, ' '),  " +
            "        apm.primerNombre + ' ' + COALESCE( apm.segundoNombre, ' '), " +
            "  CASE  " +
            "   WHEN  " +
            "    SUM (  " +
            "     CASE  " +
            " WHEN calendario.fecha = agenda.fecha THEN  " +
            "  1 " +
            " ELSE  " +
            "  0 " +
            "     END  " +
            "    ) OVER (  " +
            "     PARTITION BY  " +
            "            apm.id " +
            "          ) = 0  " +
            "   THEN  " +
            "    0 " +
            "   ELSE  " +
            "    SUM (  " +
            "     CASE  " +
            " WHEN calendario.fecha = agenda.fecha THEN  " +
            "  agenda.visitas  " +
            " ELSE  " +
            "  0 " +
            "     END  " +
            "    ) OVER (  " +
            "     PARTITION BY  " +
            " apm.id " +
            "    ) / " +
            "    SUM (  " +
            "     CASE  " +
            " WHEN calendario.fecha = agenda.fecha THEN  " +
            "  1 " +
            " ELSE  " +
            "  0 " +
            "     END  " +
            "    ) OVER (  " +
            "     PARTITION BY  " +
            "            apm.id " +
            "          )  " +
            "  END DESC  ",
            nativeQuery = true)
            List<ConexionesKpiApmPromediosProjection>  findConexionesApmPromedioMes(Long idGerente, int mes, int ano, String nombre, String nombreCompleto);

    @Query(value = "SELECT  apm.gerente_regional_id, " +
            "        apm.id AS apmId, " +
            "        apm.primerApellido + ' ' + COALESCE( apm.segundoApellido, ' ') AS apellidos, " +
            "        apm.primerNombre + ' ' + COALESCE( apm.segundoNombre, ' ') AS nombres, " +
            "        SUM ( " +
            "            CASE " +
            "                WHEN calendario.fecha = conexion.fecha  " +
            "                AND     conexion.conexiones > 0" +
            "                THEN " +
            "                    1" +
            "                ELSE " +
            "                    0" +
            "            END " +
            "        ) OVER ( " +
            "            PARTITION BY " +
            "                apm.id" +
            "        ) totalConexionesMes, " +
            "        SUM ( " +
            "            CASE " +
            "                WHEN calendario.fecha <= CAST( GETDATE() AS date ) " +
            "                THEN " +
            "                    1" +
            "                ELSE " +
            "                    0" +
            "            END " +
            "        ) OVER ( " +
            "            PARTITION BY " +
            "                apm.id" +
            "        ) diasMes, " +
            "        calendario.dia dia," +
            "        SUM( " +
            "            CASE " +
            "                WHEN    calendario.fecha = conexion.fecha " +
            "                AND     conexion.conexiones > 0" +
            "                THEN " +
            "                    1" +
            "                ELSE " +
            "                    0" +
            "            END " +
            "        ) conexion, " +
            "        SUM(" +
            "            CASE " +
            "                WHEN    calendario.fecha = conexion.fecha " +
            "                AND     conexion.conexiones > 0 " +
            "                THEN " +
            "                    1" +
            "                ELSE " +
            "                    0" +
            "            END " +
            "        ) conexionesDia " +
            "FROM    apm " +
            "CROSS JOIN    calendario " +
            "LEFT JOIN    (  " +
            "            SELECT  id_apm," +
            "                    DAY( conexion.fecha ) AS dia, " +
            "                    MONTH( conexion.fecha ) AS mes, " +
            "                    YEAR( conexion.fecha ) AS ano, " +
            "                    CAST( conexion.fecha AS date ) fecha, " +
            "                    COUNT(*) conexiones  " +
            "            FROM    conexion_apm conexion" +
            "            GROUP BY " +
            "                    id_apm, " +
            "                    DAY( conexion.fecha )," +
            "                    MONTH( conexion.fecha ), " +
            "                    YEAR( conexion.fecha )," +
            "                    CAST( conexion.fecha AS date )" +
            "        ) conexion " +
            "ON      conexion.id_apm = apm.id " +
            "AND     calendario.dia = conexion.dia " +
            "AND     calendario.mes = conexion.mes " +
            "AND     calendario.ano = conexion.ano " +
            "WHERE   apm.gerente_regional_id = :idGerente " +
            "AND     calendario.mes = :mes " +
            "AND     calendario.ano = :ano " +
            "AND     apm.inactivo = 0" +
            "AND     (:nombre IS NULL OR :nombre = '' OR apm.primerNombre = :nombre) " +
            "AND     (:nombreCompleto IS NULL OR :nombreCompleto = '' OR CONCAT(apm.primerNombre,' ',apm.primerApellido) COLLATE Latin1_general_CI_AI like concat('%',:nombreCompleto,'%')) " +
            "GROUP BY " +
            "        apm.gerente_regional_id, " +
            "        apm.id, " +
            "        apm.primerApellido + ' ' + COALESCE( apm.segundoApellido, ' '), " +
            "        apm.primerNombre + ' ' + COALESCE( apm.segundoNombre, ' '), " +
            "        calendario.dia," +
            "        calendario.fecha," +
            "        conexion.fecha," +
            "        conexion.conexiones," +
            "        calendario.festivo," +
            "        calendario.nombre_dia_semana " +
            "ORDER BY " +
            "        apm.primerApellido + ' ' + COALESCE( apm.segundoApellido, ' '), " +
            "        apm.primerNombre + ' ' + COALESCE( apm.segundoNombre, ' '), " +
            "        SUM ( " +
            "            CASE " +
            "                WHEN calendario.fecha = conexion.fecha THEN " +
            "                    conexion.conexiones " +
            "                ELSE " +
            "                    0" +
            "            END " +
            "        ) OVER ( " +
            "            PARTITION BY " +
            "                apm.id " +
            "        ) DESC ",
            nativeQuery = true)
    List<ConexionesKpiApmPromediosProjection>  findConexionesApmVisitasMes(long idGerente, int mes, int ano, String nombre, String nombreCompleto);


    /**
     * Find an Apm by legajo in Apm and Gerente
     *
     * @param legajo to find
     * @return APM
     */
    @Query("SELECT COUNT(a) FROM Apm a WHERE a.legajo = :legajo AND (:idApm is null or a.id <> :idApm)")
    Integer existsByLegajo(Long legajo, Long idApm);

    /**
     * Find email in Apms and return true
     *
     * @param email
     * @return APM
     */
    @Query("SELECT COUNT(a) FROM Apm a WHERE a.email = lower(:email) AND (:idApm is null OR a.id <> :idApm )")
    Integer existsByEmail(String email, Long idApm);

    @Query(value = "SELECT a.id, " +
                            "CONCAT(TRIM(a.primerNombre), ' ', TRIM(a.segundoNombre))     as nombre, " +
                            "CONCAT(TRIM(a.primerApellido), ' ', TRIM(a.segundoApellido)) as apellido " +
                    "FROM apm a " +
                    "WHERE a.inactivo = 0 AND (a.deleted = 0 or a.deleted is null) " +
                        "AND (:nombre IS NULL OR " +
                            "REPLACE(CONCAT(a.primerNombre, a.segundoNombre, a.primerApellido, a.segundoApellido), ' ', '') " +
                        "LIKE CONCAT('%',:nombre, '%')) " +
                        "AND (:gerenteId IS NULL OR a.gerente_regional_id = :gerenteId) " +
                        "AND a.id NOT IN ( " +
                            "SELECT DISTINCT cm.apm_id " +
                            "FROM cartera_medica cm " +
                            "WHERE cm.inactivo = 0 " +
                            "AND (cm.doctor_id = :idDoctor) " +
                        ") " +
                    "ORDER BY apellido", nativeQuery = true)
    List<ApmProjection> getAllApmsResumidos(Long gerenteId, String nombre, Long idDoctor);

    @Query(value = "SELECT count(*) as total, u.id as gerenteId " +
            "FROM apm a " +
            "JOIN usuario u ON u.id = a.gerente_regional_id " +
            "WHERE a.inactivo =0 " +
            "AND u.inactivo =0 " +
            "and (:gerenteId is null or CAST(a.gerente_regional_id as varchar) in (SELECT value FROM STRING_SPLIT(convert(varchar(max), :gerenteId), ','))) " +
            "group by u.id"
            , nativeQuery = true)
    List<TotalesPorGerenteProjection> getTotalApmsByGerente(String gerenteId);

    @Query(value = "EXEC Reporte_Apm", nativeQuery = true)
    List<ReporteApmExcelProjection> getReporteApm();

    @Query(value = "SELECT DISTINCT a.id, a.primerNombre nombre, a.primerApellido apellido, a.legajo " +
            "FROM apm a " +
            "JOIN usuario u ON u.id = a.gerente_regional_id " +
            "LEFT JOIN cartera_medica cm ON cm.apm_id = a.id " +
            "LEFT JOIN doctor d ON d.id=cm.doctor_id " +
            "LEFT JOIN cartera_medica_estado cme ON cme.id=cm.cartera_medica_estado_id " +
            "WHERE a.inactivo = 0 " +
            "AND u.inactivo = 0 " +
            "AND d.inactivo = 0 " +
            "AND cm.inactivo = 0 " +
            "AND (cme.estado = 'ALTA_APROBADA' OR cme.estado = 'ACTIVACION_APROBADA' OR cme.estado = 'BAJA_RECHAZADA' OR cme.estado IS NULL " +
            "OR (cme.estado = 'BAJA_APROBADA' AND (cme.fecha_confirmacion BETWEEN  (SELECT c.inicio from ciclo c WHERE c.id = :cicloId) " +
            "AND (SELECT c.fin from ciclo c WHERE c.id = :cicloId)))) " +
            "AND (:gerenteId IS NULL OR a.gerente_regional_id = :gerenteId) " +
            "AND (:nombreCompleto IS NULL OR :nombreCompleto = '' OR CONCAT(upper(a.primerNombre), upper(a.segundoNombre), upper(a.primerApellido), " +
            "upper(a.segundoApellido)) COLLATE Latin1_general_CI_AI like concat('%',upper(:nombreCompleto),'%')) " +
            "AND (:gerente IS NULL OR :gerente = '' OR CONCAT(u.nombre, u.apellido) COLLATE Latin1_general_CI_AI like concat('%',:gerente,'%'))",
    nativeQuery = true)
    Page<ApmProjection> getApmsByGerenteDocActivo(Long gerenteId, String nombreCompleto, Pageable pageable, String gerente, Long cicloId);

}