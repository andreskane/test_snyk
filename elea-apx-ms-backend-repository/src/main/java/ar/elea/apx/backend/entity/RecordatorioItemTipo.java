package ar.elea.apx.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Felipe Jimenez
 */
@Entity(name = "recordatorio_item_tipo")
@Data
@NoArgsConstructor
public class RecordatorioItemTipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
}
