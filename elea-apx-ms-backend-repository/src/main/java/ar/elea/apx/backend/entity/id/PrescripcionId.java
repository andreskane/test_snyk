package ar.elea.apx.backend.entity.id;

import lombok.Data;

import java.io.Serializable;


/**
 * @author Felipe Jimenez
 */
@Data
public class PrescripcionId implements Serializable {

    private static final long serialVersionUID = -198961018496728028L;

    private String periodo;

    private String medico;

    private String mercado;

    private String region;

    private String producto;
}
