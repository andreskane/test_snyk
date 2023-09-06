package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.SociedadCientifica;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SociedadCientificaRepository extends JpaRepository<SociedadCientifica, Long> {

    List<SociedadCientifica> findAllByInactivoIsFalse(Sort nombre);
    List<SociedadCientifica> findByNombreIgnoreCase(String nombre);
    List<SociedadCientifica> findByNombreIgnoreCaseOrSiglasIgnoreCase(String nombre, String siglas);

    @Query(value = "select count(*) from sociedad_cientifica sc where LOWER(nombre) = LOWER(:nombre) and LOWER(siglas) = LOWER(:siglas) and (:id is null or id <> :id);"
            , nativeQuery = true)
    Integer findByNombreIgnoreCaseOrSiglasIgnoreCaseAndIdIsNot(String nombre, String siglas, Long id);

    @Modifying
    @Query("update sociedad_cientifica set inactivo = true where id = :id")
    void markInactive(Long id);
}
