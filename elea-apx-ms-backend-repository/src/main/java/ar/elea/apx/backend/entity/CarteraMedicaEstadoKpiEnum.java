package ar.elea.apx.backend.entity;

import lombok.NoArgsConstructor;

import java.util.stream.Stream;

/**
 * @author Christian Corrales
 */
@NoArgsConstructor
public enum CarteraMedicaEstadoKpiEnum {
    ALTA,
    BAJA,
    ACTIVACION;

    public static Stream<CarteraMedicaEstadoKpiEnum> stream() {
        return Stream.of(CarteraMedicaEstadoKpiEnum.values());
    }
}