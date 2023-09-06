package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Guillermo Nasi
 */
@Data
@Entity
@Table(name = "usuario")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -909392999171479605L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    private String email;

    private Long legajo;

    @Enumerated(EnumType.STRING)
    private APxRol rol;

    private Boolean inactivo;

    @Column(name = "alias_geodata")
    private String aliasGeodata;
}
