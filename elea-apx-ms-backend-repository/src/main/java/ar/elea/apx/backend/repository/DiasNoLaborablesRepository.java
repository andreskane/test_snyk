package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.DiaNoLaboral;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface DiasNoLaborablesRepository extends JpaRepository<DiaNoLaboral, Long> {

    List<DiaNoLaboral> findDiasNoLaboralesByFecha(LocalDate fecha);

    @Query(value =
            "select * " +
            "from dias_no_laborables " +
            "where :anio is null or YEAR(fecha) = :anio", nativeQuery = true)
    Page<DiaNoLaboral> findDiasNoLaboralesByYear(Pageable pageable, String anio);


    List<DiaNoLaboral> findDiasNoLaborablesByFechaAfterAndFechaBefore(LocalDate fechaAfter, LocalDate fechaBEfore);

}

