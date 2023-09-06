package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.Feedback;
import ar.elea.apx.backend.view.FeedBackView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {


    //@Query(value = "select pantalla, puntaje, COUNT(puntaje) as conteo from feedback GROUP BY pantalla, puntaje ORDER BY pantalla", nativeQuery = true)

    @Query(name = "ComentariosAgrupados",
            countName = "ComentariosAgrupadosCount", nativeQuery = true)
    List<FeedBackView> getFeebackAverage(Integer idPantalla);

    @Query("select count(id) from feedback")
    Integer getFeedbacksCount();

    @Query(value = "select id idApmNot " +
                    "from (" +
                    "SELECT apm.id, MAX(f.fecha) ultima_fecha " +
                    "FROM feedback f right join apm " +
                    "on f.idApm = apm.id and apm.inactivo = 0 " +
                    "group by apm.id) tabla " +
                    "where DATEDIFF(day, ultima_fecha , SYSDATETIME()) > :diasEsperaFeedback" +
                    "   or ultima_fecha is NULL"
            , nativeQuery = true)
    List<Long> findNotifications(Long diasEsperaFeedback);
}
