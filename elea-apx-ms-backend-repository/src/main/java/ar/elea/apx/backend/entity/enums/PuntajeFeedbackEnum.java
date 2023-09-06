package ar.elea.apx.backend.entity.enums;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum PuntajeFeedbackEnum {

    POCO_UTIL(0,"Poco Util"),
    REGULAR(1, "Regular"),
    BUENO(2, "Bueno"),
    MUY_BUENO(3, "Muy Bueno"),
    EXCELENTE(4, "Excelente");

    private Integer id;
    private String name;

    public static PuntajeFeedbackEnum getById(Integer id) {
        for(PuntajeFeedbackEnum e : values()) {
            if(e.id.equals(id)) return e;
        }
        return null;
    }
}
