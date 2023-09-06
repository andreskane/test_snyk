package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author Felipe Jimenez
 */
@AllArgsConstructor
@Getter
public enum EstadoDoctorEnum {

    BAJA_SOLICITADA("Baja Solicitada"),
    BAJA_APROBADA("Baja Aprobada"),
    BAJA_RECHAZADA("Baja Rechazada"),
    ACTIVO("Activo"),
    INACTIVO("Inactivo");

    private final String estadoDescription;

    public static EstadoDoctorEnum findByDescription(String estadoDescription){
        return Arrays.stream(EstadoDoctorEnum.values()).filter(e->estadoDescription.equals(e.getEstadoDescription())).findFirst().orElse(null);
    }
}
