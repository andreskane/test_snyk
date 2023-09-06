package ar.elea.apx.backend.entity;

import lombok.NoArgsConstructor;

import java.util.stream.Stream;

/**
 * @author Guillermo Nasi
 */
@NoArgsConstructor
public enum CarteraMedicaEstadoEnum {
    ALTA_SOLICITADA,
    ALTA_APROBADA,
    ALTA_RECHAZADA,
    BAJA_SOLICITADA,
    BAJA_APROBADA,
    BAJA_RECHAZADA,
    ACTIVACION_SOLICITADA,
    ACTIVACION_APROBADA,
    ACTIVACION_RECHAZADA;

    public static Stream<CarteraMedicaEstadoEnum> stream() {
        return Stream.of(CarteraMedicaEstadoEnum.values());
    }
}