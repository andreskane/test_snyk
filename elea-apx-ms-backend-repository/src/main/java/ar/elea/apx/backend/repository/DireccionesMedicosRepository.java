package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.DireccionesMedicos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DireccionesMedicosRepository extends JpaRepository<DireccionesMedicos, Long> {

    List<DireccionesMedicos> findByIdMedicoAndActiveTrue(int idMedico);

    @Modifying
    @Query(value = "BEGIN " +
                        "IF NOT EXISTS (SELECT * FROM direcciones_medicos " +
                                                "WHERE id_medico = :idMedico " +
                                                    "AND id_direccion = :idDireccion) " +
                        "BEGIN " +
                            "INSERT INTO direcciones_medicos (id_medico, id_direccion) " +
                            "VALUES (:idMedico,:idDireccion) " +
                        "END " +
                    "END", nativeQuery = true)
    int saveDireccionMedico(int idMedico,int idDireccion);


    @Modifying
    @Query(value = "delete from direcciones_medicos where id_medico = :idDoctor and active = '1' ", nativeQuery = true)
    void deleteDireccionesDoctor(Long idDoctor);

    @Modifying
    @Query(value = "DELETE FROM direcciones_medicos WHERE id_medico = :idDoctor AND CAST(id_direccion AS VARCHAR) IN (:idDirecciones) AND active = '1' ", nativeQuery = true)
    void deleteIdDireccionesDoctor(Long idDoctor, String idDirecciones);

    @Modifying
    @Query(value = "UPDATE direccion SET piso=:piso, departamento = :departamento, otro = :otro WHERE id = :idDireccion", nativeQuery = true)
    int updateDireccionComplemento(int idDireccion, String piso, String departamento, String otro);
}