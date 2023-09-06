package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Diego Luis Hernandez
 */
@Entity(name = "muestras_apm")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MuestrasApm implements Serializable {
    private static final long serialVersionUID = -5295332402524233629L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String claseMovimiento;
    private String codigoProducto;
    private String centro;
    private String almacen;
    private String cantidad;
    private String codigoApm;
    private String reserva;
    private String fechaEnvio;
    private String producto;
    private Date vencimiento;
    private Date createDtm;

}
