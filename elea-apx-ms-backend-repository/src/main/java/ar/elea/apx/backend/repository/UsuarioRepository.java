package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.APxRol;
import ar.elea.apx.backend.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Guillermo Nasi
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("select u from Usuario u where u.rol = ar.elea.apx.backend.entity.APxRol.GERENTE_REGIONAL and u.inactivo = 0")
    List<Usuario> getAllGerentesRegionales();

    Page<Usuario> findByRolAndInactivoIsFalse(APxRol rol, Pageable pageable);

    @Query(value = "SELECT u FROM Usuario u " +
            "WHERE CONCAT(u.nombre, ' ', u.apellido) LIKE ('%' + :nombreCompleto + '%')" +
            "AND u.rol = :rol AND u.inactivo = false")
    Page<Usuario> getAllByRolAndInactivoIsFalse_And_NombreOrApellido_LikeNombreCompleto(APxRol rol, String nombreCompleto, Pageable pageable);

    @Query("select u from Usuario u where u.rol = ar.elea.apx.backend.entity.APxRol.GERENTE_REGIONAL and u.id = :id")
    Usuario getGerenteRegionalById(Long id);

    @Query("select u from Usuario u where u.rol = ar.elea.apx.backend.entity.APxRol.ADMINISTRADOR")
    List<Usuario> getAllAdministradores();

    @Query("select u from Usuario u where u.rol = ar.elea.apx.backend.entity.APxRol.ADMINISTRADOR and u.id = :id")
    Usuario getAdministradorById(Long id);

    Usuario getByEmail(String email);

    @Modifying
    @Query("update Usuario u set u.inactivo = true where u.id = :id")
    void markAsInactive(Long id);


    @Query(value =
            "select u " +
            "from Usuario u " +
            "where " +
            ":nombre is null or " +
            "(u.nombre like '%' + :nombre + '%' or " +
            "u.apellido like :nombre + '%') order by u.nombre, u.apellido asc"
    )
    List<Usuario> findByNombreOrApellido(String nombre);

    Usuario findByLegajo(Long legajo);

    @Query(value = "SELECT COUNT(*) AS totalApms FROM apm where apm.gerente_regional_id =:gerenteId AND apm.inactivo = 0; ", nativeQuery = true)
    Integer getTotalApmsByGerenteId(Long gerenteId);

    @Query("select u from Usuario u where u.rol = ar.elea.apx.backend.entity.APxRol.GERENTE_REGIONAL and u.inactivo = 0")
    Page<Usuario> getAllGerentesRegionalesPage(Pageable page);

    @Query(value = "SELECT U.* FROM USUARIO U WHERE U.INACTIVO = 0 AND U.ROL='GERENTE_REGIONAL' " +
            "AND (:nombre is null OR :nombre = '' OR CONCAT(upper(U.NOMBRE), upper(U.APELLIDO) COLLATE Latin1_general_CI_AI) like CONCAT('%',UPPER(:nombre),'%'))",
            countQuery = "SELECT count(*) FROM USUARIO U WHERE U.INACTIVO = 0 AND U.ROL='GERENTE_REGIONAL' " +
                    "AND (:nombre is null OR :nombre = '' OR CONCAT(upper(U.NOMBRE), upper(U.APELLIDO) COLLATE Latin1_general_CI_AI) like CONCAT('%',UPPER(:nombre),'%'))",
    nativeQuery = true)
    Page<Usuario> getAllGerentesRegionalesPageByNombre(Pageable page, String nombre);

    /**
     * Find an Apm by legajo in Apm and Gerente
     *
     * @param legajo to find
     * @return APM
     */
    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.legajo = :legajo AND (:id is null or u.id <> :id)")
    Integer existsByLegajo(Long legajo, Long id);


    /**
     * Find an Apm by legajo in Apm and Gerente
     *
     * @param email to find
     * @return APM
     */
    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.email = lower(:email) AND (:id is null OR u.id <> :id)")
    Integer existsByEmail(String email, Long id);

}
