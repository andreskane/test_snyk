package ar.elea.apx.backend.repository;


import ar.elea.apx.backend.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

/**
 * @author Felipe Jimenez
 */
public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findAllByFechaBetween(Date inicio, Date fin);
}
