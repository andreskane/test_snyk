package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "region_provincia")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegionProvincia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sfn_region")
    private String sfnRegion;

    @ManyToOne
    @JoinColumn(name = "id_provincia")
    private Provincia provincia;
}
