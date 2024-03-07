package com.example.treatment_system.entity;

import com.example.commons.service.TransTimeUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "treatment")
public class Treatment {  
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer treatmentId;  
  
    @Column(name = "patient_id")  
    private Integer patientId;  
  
    @Column(name = "treatment_type")  
    private String treatmentType;  
  
    @Column(name = "treatment_duration")  
    private Integer treatmentDuration;  
  
    @Column(name = "doctor_id")  
    private Integer doctorId;  
  
    @Column(name = "treatment_status")  
    private String treatmentStatus;
  
    @Column(name = "equipment_id")  
    private Integer equipmentId;  
  
    @Column(name = "start_time")  
    private LocalDateTime startTime;
  
    @Column(name = "end_time")  
    private LocalDateTime endTime;
  
    // Getters and Setters  
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
  
    public LocalDateTime getStartTime() {
        return startTime;  
    }  
  
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;  
    }  


    public LocalDateTime getEndTime() {
        return endTime;  
    }  
  
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;  
    }

    public Treatment(TreatmentForStamp t) {
        this.treatmentId = t.getTreatmentId();
        this.patientId = t.getPatientId();
        this.treatmentType = t.getTreatmentType();
        this.treatmentDuration = t.getTreatmentDuration();
        this.doctorId = t.getDoctorId();
        this.treatmentStatus = t.getTreatmentStatus();
        this.equipmentId = t.getEquipmentId();
    }


}
