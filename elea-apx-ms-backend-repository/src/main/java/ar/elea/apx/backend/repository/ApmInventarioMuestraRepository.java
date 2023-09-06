package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.ApmInventarioMuestra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ApmInventarioMuestraRepository extends JpaRepository<ApmInventarioMuestra, Long> {

    @Query(value =
            "select inv.* from " +
                    "apm_inventario_muestra inv, " +
                    "apm_muestras_aprobacion apmues, " +
                    "apm_caja_recepcion_productos rcp, " +
                    "apm_caja_recepcion rece " +
                    "where " +
                    "rece.id = rcp.id_caja_recepcion and " +
                    "rcp.id  = apmues.id_recepcion_productos and " +
                    "inv.id_muestras_aprobacion = apmues.id and " +
                    "inv.estado = 'ACTUAL' and " +
                    "rece.id_apm = :idApm and " +
                    "apmues.producto_muestra = :productoMuestra and " +
                    "rece.fecha_envio = :fechaEnvio ", nativeQuery = true)
    ApmInventarioMuestra findInventarioMuestraExiste(long idApm, String productoMuestra, LocalDate fechaEnvio);


    @Query(value =
            "select inv.* from " +
                    "apm_inventario_muestra inv, " +
                    "apm_muestras_aprobacion apmues, " +
                    "apm_caja_recepcion_productos rcp, " +
                    "apm_caja_recepcion rece " +
                    "where " +
                    "rece.id = rcp.id_caja_recepcion and " +
                    "rcp.id  = apmues.id_recepcion_productos and " +
                    "inv.id_muestras_aprobacion = apmues.id and " +
                    "inv.estado = 'ACTUAL' and " +
                    "rece.id_apm = :idApm and " +
                    "apmues.producto_muestra = :productoMuestra and " +
                    "(:lote is null or apmues.lote = :lote) ", nativeQuery = true)
    ApmInventarioMuestra findInventarioMuestraExisteCantidad(long idApm, String productoMuestra, String lote);

}