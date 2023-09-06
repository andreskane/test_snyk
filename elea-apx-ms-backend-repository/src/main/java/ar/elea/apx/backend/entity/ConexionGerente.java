package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data
@Entity
@Table(name = "conexion_gerente")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConexionGerente implements Serializable {
    private static final long serialVersionUID = -2435615768529867422L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_gerente")
    private Long idGerente;

    private Date fecha;
}
