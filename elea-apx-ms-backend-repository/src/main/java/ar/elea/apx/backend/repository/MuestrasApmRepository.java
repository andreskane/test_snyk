package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.MuestrasApm;
import ar.elea.apx.backend.projection.MuestrasApmProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuestrasApmRepository extends JpaRepository<MuestrasApm, Long> {

    @Query(value=	"select rece.codigo_producto as codigoProducto, " +
            "rece.nombre_producto as nombreProducto, " +
            "apmues.codigo_muestra as codigoPresentaciones, " +
            "apmues.producto_muestra as presentaciones, " +
            "v.codigoLinea as codigoLinea, " +
            "v.linea as linea, " +
            "apmr.id_apm as codigoApm, " +
            "inv.cantidad ,apmues.lote,apmues.vecimiento , apmues.vecimiento as fechaVencimiento " +
            "from apm_inventario_muestra inv, " +
            "apm_muestras_aprobacion apmues, " +
            "apm_caja_recepcion_productos rece, " +
            "apm_caja_recepcion apmr," +
            "catalogo_productos_muestras_view v " +
            "where " +
            "apmr.id = rece.id_caja_recepcion and " +
            "rece.id = apmues.id_recepcion_productos and " +
            "inv.id_muestras_aprobacion = apmues.id and " +
            "apmues.codigo_muestra = v.codigoPresentaciones and " +
            "inv.estado = 'ACTUAL' and " +
            "inv.cantidad > 0 and " +
            "apmr.id_apm = :idApm "
            , nativeQuery = true )
    List<MuestrasApmProjection> getMuestrasApm(Long idApm);

    @Query(value="select * from ( select " +
            "v.*," +
            "ap.id as codigoApm, " +
            "ma.Almacen as lote, " +
            "CONVERT(date, ma.Fecha_vencimiento) as fechaVencimiento,  " +
            "ma.Cantidad as cantidadEnviada, " +
            "ma.Cantidad as cantidadRecibida, " +
            "cast(CONVERT(smalldatetime, ma.Fecha_envio) as datetime) as fechaEnvio " +
            "from catalogo_productos_muestras_view v " +
            "INNER JOIN muestras_apm ma on REPLACE(LTRIM(REPLACE(ma.Codigo_producto,'0',' ')),' ','0') = v.codigoPresentaciones " +
            "INNER JOIN apm ap on CONCAT( primerApellido , ' ' , primerNombre, ' ' , segundoNombre  ) like CONCAT('%',ma.APM,'%') " +
            "WHERE " +
            "ap.id = :idApm AND " +
            "ma.producto like '%MM' AND " +
            "NOT EXISTS (" +
            "select * from " +
            "apm_caja_recepcion apc, " +
            "apm_caja_recepcion_productos apcr, " +
            "apm_muestras_aprobacion amap " +
            "where apc.id = apcr.id_caja_recepcion and " +
            "apcr.id = amap.id_recepcion_productos and " +
            "apc.id_apm = ap.id AND " +
            "apc.fecha_envio = ma.Fecha_envio and " +
            "amap.codigo_muestra = v.codigoPresentaciones) ) z " +
            "order by z.fechaEnvio asc,z.nombreProducto, z.presentaciones asc  ", nativeQuery = true)
    List<MuestrasApmProjection> getMuestrasApmRecepcion(Long idApm);

}
