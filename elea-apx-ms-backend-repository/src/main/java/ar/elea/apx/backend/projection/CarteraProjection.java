package ar.elea.apx.backend.projection;


import ar.elea.apx.backend.entity.Apm;
import ar.elea.apx.backend.entity.DatosVisita;
import ar.elea.apx.backend.entity.Doctor;

/**
 * @author Christian Corrales
 */
public interface CarteraProjection {
    DatosVisita getDatosVisita();
    Apm getApm();
    Doctor getDoctor();
}
