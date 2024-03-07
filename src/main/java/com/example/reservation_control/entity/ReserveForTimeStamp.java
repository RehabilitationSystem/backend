package com.example.reservation_control.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
public class ReserveForTimeStamp {


    private Integer reservationId;

    private Integer patientId;

    private Integer doctorId;

    private Long treatmentTime;

    private String reservationContext;

    private Integer appointmentStatus;

    private BigDecimal cost;

    private String evaluation;

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Long getTreatmentTime() {
        return treatmentTime;
    }

    public void setTreatmentTime(Long treatmentTime) {
        this.treatmentTime = treatmentTime;
    }

    public String getReservationContext() {
        return reservationContext;
    }

    public void setReservationContext(String reservationContext) {
        this.reservationContext = reservationContext;
    }

    public Integer getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(Integer appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public ReserveForTimeStamp(Reserve r) {
        this.reservationId = r.getReservationId();
        this.patientId = r.getPatientId();
        this.doctorId = r.getDoctorId();
        this.reservationContext = r.getReservationContext();
        this.appointmentStatus = r.getAppointmentStatus();
        this.cost = r.getCost();
        this.evaluation = r.getEvaluation();
    }
}
