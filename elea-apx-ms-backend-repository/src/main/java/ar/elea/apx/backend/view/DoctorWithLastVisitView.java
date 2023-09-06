package ar.elea.apx.backend.view;

import ar.elea.apx.backend.entity.Doctor;
import ar.elea.apx.backend.projection.ApmProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author Guillermo Nasi
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class DoctorWithLastVisitView {
    private Doctor doctor;
    private Date lastVisit;
    private List<ApmProjection> apms;

    public DoctorWithLastVisitView(Doctor doctor, Date lastVisit) {
        this.doctor = doctor;
        this.lastVisit = lastVisit == null ? null : (Date)lastVisit.clone();
    }

    public DoctorWithLastVisitView(Doctor doctor) {
        this.doctor = doctor;
    }
}
