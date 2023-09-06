package ar.elea.apx.backend.view;

import ar.elea.apx.backend.entity.Agenda;
import ar.elea.apx.backend.entity.DatosVisita;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Guillermo Nasi
 */
@Data
@AllArgsConstructor
public class AgendaWithDatosVisitaView {
    private Agenda agenda;
    private DatosVisita datosVisita;
}
