package ar.elea.apx.backend.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Guillermo Nasi
 */
@Data
public class DatosVisitaId implements Serializable {
    private static final long serialVersionUID = 3068607269041425797L;

    private Apm apm;
    private Doctor doctor;
}
