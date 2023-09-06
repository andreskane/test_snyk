package ar.elea.apx.backend.view;

import ar.elea.apx.backend.entity.TurnoEnum;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * @author Guillermo Nasi
 */
@Data
@NoArgsConstructor
@JsonPropertyOrder(alphabetic = true)
public class CarteraMedicaView {
    private Long id;
    private Long doctorId;
    private Long apmId;
    private String doctorApellido;
    private String doctorNombre;
    private Integer doctorApp;
    private String matriculaNacional;
    private String matriculaProvincial;
    private String especialidadDoctor;
    private String subespecialidad;
    private String provinciaDireccion;
    private String localidadDireccion;
    private String ciudadDireccion;
    private String calleDireccion;
    private String numeroDireccion;
    private String apmApellido;
    private String apmNombre;
    private String gerenteRegionalApellido;
    private String gerenteRegionalNombre;
    private Integer frecuencia;
    private TurnoEnum turno;
    private Date ultimaVisitaCartera;
    private Integer visitasCiclo;
    private String cpa;
//bug 1548
    private String pisoDireccion;
    private String departamentoDireccion;
    private String otroDireccion;
    private String entidadMatriculaProvincial;
    private String ISO_entidadMatriculaProvincial;
    public CarteraMedicaView(
            Long carteraId,
            Long doctorId,
            String doctorApellido,
            String doctorNombre,
            Integer doctorApp,
            String matriculaNacional,
            String matriculaProvincial,
            String especialidadDoctor,
            String subespecialidad,
            String provinciaDireccion,
            String localidadDireccion,
            String ciudadDireccion,
            String calleDireccion,
            String numeroDireccion,
            Long apmId,
            String apmApellido,
            String apmNombre,
            String gerenteRegionalApellido,
            String gerenteRegionalNombre,
            Integer frecuencia,
            Integer turno,
            Date ultimaVisitaCartera,
            Integer visitasCiclo,
            String cpa,
            String pisoDireccion,
            String departamentoDireccion,
            String otroDireccion,
            String entidadMatriculaProvincial,
            String ISO_entidadMatriculaProvincial) {
        this.id = carteraId;
        this.doctorId = doctorId;
        this.apmId = apmId;
        this.doctorApellido = doctorApellido;
        this.doctorNombre = doctorNombre;
        this.doctorApp = doctorApp;
        this.matriculaNacional = matriculaNacional;
        this.matriculaProvincial = matriculaProvincial;
        this.especialidadDoctor = especialidadDoctor;
        this.subespecialidad = subespecialidad;
        this.provinciaDireccion = provinciaDireccion;
        this.localidadDireccion = localidadDireccion;
        this.ciudadDireccion = ciudadDireccion;
        this.calleDireccion = calleDireccion;
        this.numeroDireccion = numeroDireccion;
        this.apmApellido = apmApellido;
        this.apmNombre = apmNombre;
        this.gerenteRegionalApellido = gerenteRegionalApellido;
        this.gerenteRegionalNombre = gerenteRegionalNombre;
        this.frecuencia = frecuencia;
        this.turno = turno == null ? null : TurnoEnum.values()[turno];
        this.ultimaVisitaCartera = ultimaVisitaCartera != null ? (Date) ultimaVisitaCartera.clone() : null;
        this.visitasCiclo = visitasCiclo;
        this.cpa = cpa;

        //bug 1548
        this.pisoDireccion=pisoDireccion;
        this.departamentoDireccion=departamentoDireccion;
        this.otroDireccion=otroDireccion;


        this.entidadMatriculaProvincial = entidadMatriculaProvincial;
        this.ISO_entidadMatriculaProvincial=ISO_entidadMatriculaProvincial;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}