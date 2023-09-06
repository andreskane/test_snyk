package ar.elea.apx.backend.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Christian Corrales
 */

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class FeedBackView {
     private Integer puntaje;
     private Integer pantalla;
     private Integer conteo;
}

