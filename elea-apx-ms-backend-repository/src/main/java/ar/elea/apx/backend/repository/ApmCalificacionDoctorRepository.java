package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.ApmCalificacionDoctor;
import ar.elea.apx.backend.projection.DoctorFechaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ApmCalificacionDoctorRepository extends JpaRepository<ApmCalificacionDoctor, Long> {

    @Query(value = "select * from apm_calificacion_doctor a where a.proxima_calificacion >= GETDATE() and " +
            "a.id_apm = :idApm and id_doctor = :idDoctor "
            , nativeQuery = true)
    Optional<ApmCalificacionDoctor> findCalificacionesDoctorApm(Long idApm, Long idDoctor);

    @Query(value = "select * from apm_calificacion_doctor a where a.proxima_calificacion >= GETDATE() and " +
            "a.id_doctor = :idDoctor "
            , nativeQuery = true)
    List<ApmCalificacionDoctor> findCalificacionesDoctorApmById(Long idDoctor);


    @Query(value = "select max(a.proxima_calificacion) as fechaCalificacion from apm_calificacion_doctor a where a.id_apm = :idApm and id_doctor = :idDoctor "
            , nativeQuery = true)
    Optional<LocalDate> findCalificacionesDoctorApmByIdUltimaFecha(Long idApm, Long idDoctor);

    @Query(value = "select * from apm_calificacion_doctor a where a.proxima_calificacion >= GETDATE() and " +
            "a.id_apm = :idApm and id_doctor in :doctors "
            , nativeQuery = true)
    List<ApmCalificacionDoctor> findCalificacionesDoctorApm(Long idApm, List<Long> doctors);

    @Query(value = "select a.id_doctor as idDoctor, max(a.proxima_calificacion) as fechaCalificacion from apm_calificacion_doctor a " +
            "where a.id_apm = :idApm and id_doctor in :doctors " +
            "group by id_doctor"
            , nativeQuery = true)
    List<DoctorFechaProjection> findCalificacionesDoctorFecha(Long idApm, List<Long> doctors);

}
