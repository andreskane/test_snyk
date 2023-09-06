package ar.elea.apx.backend.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "sociedad_cientifica")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SociedadCientifica implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nombre")
    private String nombre;

    private String siglas;

    private Boolean inactivo;
}
