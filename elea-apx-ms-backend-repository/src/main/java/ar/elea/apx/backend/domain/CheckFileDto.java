package ar.elea.apx.backend.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Christian Corrales
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckFileDto {
    private boolean file;
    @JsonProperty("filename")
    private String fileName;
}
