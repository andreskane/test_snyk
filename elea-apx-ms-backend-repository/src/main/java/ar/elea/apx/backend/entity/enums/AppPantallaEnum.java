package ar.elea.apx.backend.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum AppPantallaEnum {

    VISITAS(0,"Módulo visitas"),
    PLANIFICACION(1,"Módulo Planificación"),
    TIEMPO_FUERA(2,"Módulo Tiempo fuera"),
    CATALOGO(3,"Módulo Catalogo"),
    CARTERA_MEDICOS(4,"Módulo Cartera de Médicos"),
    AÑADIR_MEDICO(5,"Añadir Médico"),
    GEODATA(6, "Geodata"),
    MUESTRAS(7, "Muestras");


    private Integer id;
    private String name;

    public static AppPantallaEnum getById(Integer id) {
        for(AppPantallaEnum e : values()) {
            if(e.id.equals(id)) return e;
        }
        return null;
    }
}
