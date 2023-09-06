package ar.elea.apx.backend.entity;

import ar.elea.apx.backend.entity.id.PrescripcionId;
import ar.elea.apx.backend.view.PrescripcionesComparativa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import java.io.Serializable;

/**
 * @author Felipe Jimenez
 */
@NamedNativeQuery(
        name = "prescripciones_medico",
        query = "select p.cdg_mercado            as codMercado," +
                "            p.cdg_raiz               as codRaiz," +
                "            mercado.descripcion    as mercado," +
                "            product.descripcion    as producto," +
                "            p.cdg_periodo          as periodo," +
                "            c.categoria            as categoria," +
                "            p.cant_px              as cantidad," +
                "            p.part_producto_medico as porcentaje," +
                "            product.cdg_laboratorio as codigoLaboratorio," +
                "            laboratory.descripcion as nombreLaboratorio" +
                "    from SFN_audit_customer_prescription p" +
                "             left join SFN_audit_class mercado on p.cdg_mercado = mercado.cdg_mercado" +
                "             left join SFN_audit_product product on p.cdg_raiz = product.cdg_raiz" +
                "             left join SFN_audit_laboratory laboratory on laboratory.cdg_laboratorio = product.cdg_laboratorio" +
                "             left join SFN_audit_category c on (p.cdg_periodo = c.cdg_periodo and p.cdg_mercado = c.cdg_mercado and" +
                "                                                 p.cdg_medico = c.cdg_medico and p.cdg_region = c.cdg_region)" +
                "    where p.cdg_medico like :cdg_medico" +
                "      and p.cdg_region like :cdg_region" +
                "      and p.cdg_periodo in (:cdg_periodo1, :cdg_periodo2)",
        resultSetMapping = "prescripciones_medico_mapping")
@SqlResultSetMapping(
        name = "prescripciones_medico_mapping",
        classes = @ConstructorResult(
                targetClass = PrescripcionesComparativa.class,
                columns = {
                        @ColumnResult(name = "codMercado", type = String.class),
                        @ColumnResult(name = "codRaiz", type = String.class),
                        @ColumnResult(name = "mercado", type = String.class),
                        @ColumnResult(name = "producto", type = String.class),
                        @ColumnResult(name = "periodo", type = String.class),
                        @ColumnResult(name = "categoria", type = String.class),
                        @ColumnResult(name = "cantidad", type = String.class),
                        @ColumnResult(name = "porcentaje", type = String.class),
                        @ColumnResult(name = "codigoLaboratorio", type = String.class),
                        @ColumnResult(name = "nombreLaboratorio", type = String.class)
                }
        )
)
@Entity(name = "SFN_audit_customer_prescription")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(PrescripcionId.class)
public class Prescripcion implements Serializable {

    private static final long serialVersionUID = 3447442255343340227L;
    @Id
    @Column(name="cdg_periodo")
    private String periodo;

    @Id
    @Column(name="cdg_medico")
    private String medico;

    @Id
    @Column(name="cdg_mercado")
    private String mercado;

    @Id
    @Column(name="cdg_region")
    private String region;

    @Id
    @Column(name="cdg_raiz")
    private String producto;

    @Column(name="cant_px")
    private Long cantidad;

    @Column(name="part_producto_medico")
    private Double porcentaje;
}
