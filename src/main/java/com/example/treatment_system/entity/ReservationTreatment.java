package com.example.treatment_system.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reservation_treatment")
public class ReservationTreatment {

    @Id
    @Column(name = "reservation_id", nullable = false, insertable = true, updatable = true)
    private int reservationId;

    @Column(name = "treatment_id", nullable = true, insertable = true, updatable = true)
    private Integer treatmentId;

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(Integer treatmentId) {
        this.treatmentId = treatmentId;
    }
}
