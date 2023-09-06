package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Christian Corrales
 */

@Entity(name = "stock_productos")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockProductos implements Serializable {

    private static final long serialVersionUID = 2123902574487417392L;

    @Id
    @Column(name = "id_producto", nullable = false)
    private Long idProducto;

    private Date fecha;

    @Column(name = "stock_virtual_disponible")
    private Integer stockVirtualDisponible;

    @Column(name = "stock_total")
    private Integer stockTotal;

    @Column(name = "stock_presupuestado")
    private Integer stockPresupuestado;

    @Column(name = "dias_stock")
    private Integer diasStock;

    @Column(name = "stock_no_disponible")
    private Integer stockNoDisponible;

    @Column(name = "stock_disponible")
    private Integer stockDisponible;

    @Column(name = "otro_stock_disponible")
    private Integer otroStockDisponible;

}
