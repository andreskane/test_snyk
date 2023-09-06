package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.FileUpload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {

    @Query(value = "SELECT fu.* FROM file_upload fu " +
            "LEFT JOIN apm a on fu.apm_id = a.id " +
            "LEFT JOIN doctor d on fu.doctor_id = d.id " +
            "WHERE (:idMedico is null or fu.doctor_id = :idMedico)" +
            "AND (:idApm is null or fu.apm_id = :idApm)" +
            "AND (:dateFrom is null or fu.date_upload >= :dateFrom)" +
            "AND (:dateTo is null or fu.date_upload <= :dateTo)", 
            countQuery = "select count(*) " +
                    "FROM " +
                    "file_upload fu " +
                    "LEFT JOIN apm a on " +
                    "fu.apm_id = a.id " +
                    "LEFT JOIN doctor d on " +
                    "fu.doctor_id = d.id " +
                    "WHERE (:idMedico is null or fu.doctor_id = :idMedico) " +
                    "AND (:idApm is null or fu.apm_id = :idApm) " +
                    "AND (:dateFrom is null or fu.date_upload >= :dateFrom) " +
                    "AND (:dateTo is null or fu.date_upload <= :dateTo)",
            nativeQuery = true)
    Page<FileUpload> getAllByIdDoctorAndOrIdApmMaybeDateBetween(Long idMedico, Long idApm, LocalDateTime dateFrom, LocalDateTime dateTo, Pageable pageable);
}