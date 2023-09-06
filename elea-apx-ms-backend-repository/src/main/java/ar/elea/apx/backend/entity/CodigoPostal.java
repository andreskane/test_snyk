package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Guillermo Nasi
 */

@NamedStoredProcedureQuery(name = "CodigoPostal.buscarDireccion", procedureName = "Buscar_Direccion", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "id_pais", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "provincia", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "localidad", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "ciudad", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "calle", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "cpa", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "tipo_calle", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "numero", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "detalle", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "piso", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "departamento", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "otros", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "id_dir", type = Long.class) })
@Entity
@Table(name = "codigo_postal")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CodigoPostal implements Serializable {
    private static final long serialVersionUID = 1838849804259356387L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpa;

    @Column(name = "par_impar")
    private String parImpar;

    @ManyToOne
    @JoinColumn(name = "id_provincia")
    private Provincia provincia;

    @ManyToOne
    @JoinColumn(name = "id_localidad")
    private Localidad localidad;

    @ManyToOne
    @JoinColumn(name = "id_ciudad")
    private Ciudad ciudad;

    @ManyToOne
    @JoinColumn(name = "id_calle")
    private Calle calle;

    private Long desde;

    private Long hasta;

}
