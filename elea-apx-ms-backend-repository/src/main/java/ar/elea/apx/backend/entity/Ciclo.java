package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Felipe Jimenez
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ciclo implements Serializable {

    private static final long serialVersionUID = 7840560927215601076L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date inicio;

    private Date fin;

    private String nombre;

}
