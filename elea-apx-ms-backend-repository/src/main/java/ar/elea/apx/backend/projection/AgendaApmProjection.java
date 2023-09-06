package ar.elea.apx.backend.projection;

import java.util.Date;

/**
 * @author Guillermo Nasi
 */
public interface AgendaApmProjection {
    Long getId();

    Long getApmId();

    String getApmNombre();

    Date getDesde();

    Date getHasta();

    String getAcompanante();

    String getDireccion();

    String getObservaciones();

    String getTurno();

    Long getAgendaTipoId();

    String getAgendaTipoNombre();

    Boolean getMotivoFechaFuturo();

    Boolean getMotivoInactivo();

    String getVisitaTipo();

    String getPerfil();

    Boolean getInactivo();

    Boolean getRequiereAprobacion();

    Boolean getAprobado();

    Float getPorcentajeTurnoManana();

    Float getPorcentajeTurnoTarde();

    Boolean getVisitaExitosa();

    Integer getPlataforma();

    Long getDoctorId();

    String getDoctorNombre();

    String getDoctorApellido();

    Date getDoctorFechaNacimiento();

    String getDoctorEmail();

    Boolean getDoctorDoctorApp();

    Long getDirVisitaId();

    Long getCpDirVisitaId();

    String getCpDirVisitaCPA();

    Long getPrDirVisitaId();

    String getPrDirVisitaNombre();

    Long getPaisDirVisitaId();

    String getPaisDirVisitaNombre();

    Long getLcldDirVisitaId();

    String getLcldDirVisitaNombre();

    Long getCiudDirVisitaId();

    String getCiudDirVisitaNombre();

    Long getCalleDirVisitaId();

    String getCalleDirVisitaNombre();

    Long getNumeroDirVisita();

    String getDetalleDirVisita();

    Long getMotivoId();

    String getMotivoNombre();

    Long getMotivoTipoId();

    Long getInstitucionId();

    String getInstitucionNombre();

    String getInstitucionRazonSocial();

    Long getDirInstitucionId();

    Long getCpDirInstitucionId();

    String getCpDirInstitucionCPA();

    Long getPrDirInstitucionId();

    String getPrDirInstitucionNombre();

    Long getPaisDirInstitucionId();

    String getPaisDirInstitucionNombre();

    Long getLcldDirInstitucionId();

    String getLcldDirInstitucionNombre();

    Long getCiudDirInstitucionId();

    String getCiudDirInstitucionNombre();

    Long getCalleDirInstitucionId();

    String getCalleDirInstitucionNombre();

    Long getNumeroDirInstitucion();

    String getDetalleDirInstitucion();

    Integer getProductos();

    Integer getMuestras();

    String getLugar();
}
