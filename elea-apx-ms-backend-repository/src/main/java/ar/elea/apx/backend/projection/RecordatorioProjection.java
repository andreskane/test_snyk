package ar.elea.apx.backend.projection;

import java.util.Date;

/**
 * @author Guillermo Nasi
 */
public interface RecordatorioProjection {

    Long getId();

    Long getAgendaId();

    Date getFechaCompletado();

    //Date getFechaRecordatorio();

    Long getDoctorId();

    Long getInstitucionId();

    Long getAgendaCompletadoId();

    String getDescripcion();

    Long getMaterialPromocionalId();

    Boolean getInactivo();
}
