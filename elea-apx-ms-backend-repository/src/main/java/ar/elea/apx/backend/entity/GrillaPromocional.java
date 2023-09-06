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
import java.io.Serializable;

/**
 * @author Guillermo Nasi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "grilla_promocional")
public class GrillaPromocional extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 8345528055993344727L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private byte[] archivo;

    @Column(name = "archivo_nombre")
    private String nombre;

    @Column(name = "content_type")
    private String contentType;
}
