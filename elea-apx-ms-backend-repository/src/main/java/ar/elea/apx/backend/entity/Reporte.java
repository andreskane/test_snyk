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
 * @author Christian Corrales
 */

@Entity(name = "reporte")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reporte implements Serializable {
    private static final long serialVersionUID = -563377579564416942L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String url;
    private Boolean inactivo;
    private Boolean filtro;
}
