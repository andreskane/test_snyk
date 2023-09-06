package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Set;

/**
 * @author Felipe Jimenez
 */
@Entity(name = "material_promocional")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialPromocional extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 8521128540312252124L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private MaterialPromocionalTipo tipo;

    private String nombre;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name="especialidad_id")
    private Especialidad especialidad;

    @ManyToOne
    @JoinColumn(name="producto_id")
    private Producto producto;

    @ManyToMany
    @JoinTable(name = "material_promocional_especialidades",
            joinColumns = @JoinColumn(name = "id_material_promocional"),
            inverseJoinColumns = @JoinColumn(name = "id_especialidades"))
    private Set<Especialidad> especialidades;

    @ManyToMany
    @JoinTable(name = "material_promocional_productos",
            joinColumns = @JoinColumn(name = "id_material_promocional"),
            inverseJoinColumns = @JoinColumn(name = "id_producto"))
    private Set<FamiliaProducto> productos;

    @Column(name="archivo_extension")
    private String archivoExtension;

    @ManyToMany
    @JoinTable(name = "material_promocional_linea_asociada",
            joinColumns = @JoinColumn(name = "id_material_promocional"),
            inverseJoinColumns = @JoinColumn(name = "id_linea"))
    private Set<Linea> lineasAsociadas;

    private Long codigo;

    private Boolean inactivo;

    @ManyToMany
    @JoinTable(name = "material_promocional_ciclos",
            joinColumns = @JoinColumn(name = "id_material_promocional"),
            inverseJoinColumns = @JoinColumn(name = "id_ciclo"))
    private Set<Ciclo> ciclo;

    private String tamanio;

    @Column(name="estado_eliminado")
    private Boolean estadoEliminado;

}
