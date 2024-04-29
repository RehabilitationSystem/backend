package com.example.treatment_system.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class TreatmentForStamp {

    private Integer treatmentId;

    private Integer patientId;

    private Integer reservationId;

    private String treatmentType;

    private Integer treatmentDuration;

    private Integer doctorId;

    private String treatmentStatus;

    private Integer equipmentId;

    private Long startTime;

    private Long endTime;

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(Integer treatmentId) {
        this.treatmentId = treatmentId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getTreatmentType() {
        return treatmentType;
    }

    public void setTreatmentType(String treatmentType) {
        this.treatmentType = treatmentType;
    }

    public Integer getTreatmentDuration() {
        return treatmentDuration;
    }

    public void setTreatmentDuration(Integer treatmentDuration) {
        this.treatmentDuration = treatmentDuration;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getTreatmentStatus() {
        return treatmentStatus;
    }

    public void setTreatmentStatus(String treatmentStatus) {
        this.treatmentStatus = treatmentStatus;
    }

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public TreatmentForStamp(Treatment t) {
        this.treatmentId = t.getTreatmentId();
        this.patientId = t.getPatientId();
        this.treatmentType = t.getTreatmentType();
        this.treatmentDuration = t.getTreatmentDuration();
        this.doctorId = t.getDoctorId();
        this.treatmentStatus = t.getTreatmentStatus();
        this.equipmentId = t.getEquipmentId();
    }
}
