package ar.elea.apx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Felipe Jimenez
 */
@Entity(name = "estado_doctor")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstadoDoctor implements Serializable {

    private static final long serialVersionUID = 3946620323974234073L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name="motivo_estado_id")
    private Motivo motivo;

    private String comentarios;

    @Enumerated(EnumType.STRING)
    @Column(name="estado")
    private EstadoDoctorEnum estado;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "created_date")
    @Cascade(CascadeType.SAVE_UPDATE)
    private Date createdDate;

    @Column(name = "modified_date")
    private Date modified;
}
