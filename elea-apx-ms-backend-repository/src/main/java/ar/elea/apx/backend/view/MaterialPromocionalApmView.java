package ar.elea.apx.backend.view;

import ar.elea.apx.backend.entity.MaterialPromocional;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Felipe Jimenez
 */
@Data
@AllArgsConstructor
public class MaterialPromocionalApmView {
    private MaterialPromocional materialPromocional;
    private Boolean tiene;
    private Long id;
}
