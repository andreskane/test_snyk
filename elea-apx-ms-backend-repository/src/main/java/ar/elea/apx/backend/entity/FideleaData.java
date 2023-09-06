package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Juan Cruz Rompani
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "fidelea")
public class FideleaData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String tipoMatricula;
    @Column
    private String nroMatricula;
    @Column(name= "apellido")
    private String apellido;
    @Column(name="nombre")
    private String nombre;
    @Column
    private Timestamp fecUltimoConsumo;
    @Column
    private Integer consumos;

    @Override
    public String toString() {
        return "FideleaData{" +
                "id=" + id +
                ", tipoMatricula='" + tipoMatricula + '\'' +
                ", nroMatricula='" + nroMatricula + '\'' +
                ", medicoApellido='" + apellido + '\'' +
                ", medicoNombre='" + nombre + '\'' +
                ", fecUltimoConsumo=" + fecUltimoConsumo +
                ", consumos=" + consumos +
                '}';
    }
}
