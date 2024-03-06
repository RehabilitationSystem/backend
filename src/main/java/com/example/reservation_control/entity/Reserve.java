package com.example.reservation_control.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;  
  
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reserve")
public class Reserve {  
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Integer reservationId;  
  
    @Column(name = "patient_id")  
    private Integer patientId;  
  
    @Column(name = "doctor_id")  
    private Integer doctorId;  
  
    @Column(name = "treatment_time")  
    private LocalDateTime treatmentTime;  
  
    @Column(name = "reservation_context")  
    private String reservationContext;  
  
    @Column(name = "appointment_status")  
    private Integer appointmentStatus;  
  
    @Column(name = "cost")  
    private BigDecimal cost;
  
    @Column(name = "evaluation")  
    private String evaluation;  
  
    // Getters and Setters  
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
  
    public LocalDateTime getTreatmentTime() {  
        return treatmentTime;  
    }  
  
    public void setTreatmentTime(LocalDateTime treatmentTime) {  
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
}