package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Felipe Jimenez
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Evento implements Serializable {
    private static final long serialVersionUID = 4100208588218371015L;
    @Id
    private Long id;

    private String titulo;

    private String descripcion;

    private String lugar;

    private String imagen;

    private Timestamp fecha;
}