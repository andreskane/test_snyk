package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Felipe Jimenez
 */
@Entity(name = "SFN_audit_customer")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrescripcionMedico implements Serializable {

    private static final long serialVersionUID = -4153698430311147286L;
    @Id
    @Column(name="cdg_medico")
    private String codMedico;

    private String matricula;

    private String nombre;

    @Column(name="cdg_especialidad1")
    private String especialidad1;

    @Column(name="cdg_especialidad2")
    private String especialidad2;

    @Column(name="cdg_region")
    private String region;

    private String domicilio;

    private String localidad;

    @Column(name="cdg_postal")
    private String codigoPostal;

    @Column(name="cdg_lider")
    private String lider;

}
