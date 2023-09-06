package ar.elea.apx.backend.entity;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @author Guillermo Nasi
 */
@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "created_date")
    @Cascade(CascadeType.SAVE_UPDATE)
    private Date createdDate;

    @Column(name = "modified_date")
    private Date modified;
}
