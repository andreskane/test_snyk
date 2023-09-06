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
@Entity(name = "institucion_tipo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstitucionTipo implements Serializable {
    private static final long serialVersionUID = -2170141640646264532L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
