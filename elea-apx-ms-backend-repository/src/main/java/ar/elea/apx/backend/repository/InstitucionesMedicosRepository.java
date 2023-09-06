package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.InstitucionesMedicos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstitucionesMedicosRepository extends JpaRepository<InstitucionesMedicos, Long> {
    List<InstitucionesMedicos> findByIdMedicoAndActiveTrue(int idMedico);

    @Modifying
    @Query(value = "BEGIN " +
                        "IF NOT EXISTS (SELECT * FROM instituciones_medicos " +
                                                "WHERE id_medico = :idMedico " +
                                                    "AND id_institucion = :idInstitucion) " +
                        "BEGIN " +
                            "INSERT INTO instituciones_medicos (id_medico, id_institucion) " +
                            "VALUES (:idMedico,:idInstitucion) " +
                        "END " +
                    "END", nativeQuery = true)
    int saveInstitucionMedico(int idMedico, int idInstitucion);

    @Modifying
    @Query(value = "delete from instituciones_medicos where id_medico = :idDoctor and active = '1' ", nativeQuery = true)
    void deleteInstitucionesDoctor(Long idDoctor);

    @Modifying
    @Query(value = "DELETE FROM instituciones_medicos WHERE id_medico = :idDoctor AND CAST(id_institucion AS VARCHAR) IN (:idInstituciones) AND active = '1' ", nativeQuery = true)
    void deleteIdInstitucionesDoctor(Long idDoctor, String idInstituciones);
}
