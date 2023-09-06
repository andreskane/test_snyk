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

/**
 * @author Felipe Jimenez
 */
@Entity(name = "motivo_tipo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MotivoTipo implements Serializable {
    private static final long serialVersionUID = 8993258529368951329L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
}
