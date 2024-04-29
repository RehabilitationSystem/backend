package com.example.treatment_system.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "treatmentRecords") // 请根据实际情况替换schema名称
public class TreatmentRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "treatmentRecords_id", nullable = false, insertable = true, updatable = true)
    private int treatmentRecordsId;

    @Column(name = "treatment_id", nullable = false, insertable = true, updatable = true)
    private int treatmentId;

    @Column(name = "admissionSummary", length = 255, nullable = true, insertable = true, updatable = true)
    private String admissionSummary;

    @Column(name = "treatmentDetail", length = 255, nullable = true, insertable = true, updatable = true)
    private String treatmentDetail;

    @Column(name = "dischargeSummary", length = 255, nullable = true, insertable = true, updatable = true)
    private String dischargeSummary;

    @Column(name = "assessmentResult", length = 255, nullable = true, insertable = true, updatable = true)
    private String assessmentResult;

    @Column(name = "doctorSignature", length = 255, nullable = true, insertable = true, updatable = true)
    private String doctorSignature;

    // 构造函数、getter和setter方法
    public TreatmentRecord() {
    }

    public TreatmentRecord(int treatmentId, String admissionSummary, String treatmentDetail, String dischargeSummary, String assessmentResult, String doctorSignature) {
        this.treatmentId = treatmentId;
        this.admissionSummary = admissionSummary;
        this.treatmentDetail = treatmentDetail;
        this.dischargeSummary = dischargeSummary;
        this.assessmentResult = assessmentResult;
        this.doctorSignature = doctorSignature;
    }

    public int getTreatmentRecordsId() {
        return treatmentRecordsId;
    }

    public void setTreatmentRecordsId(int treatmentRecordsId) {
        this.treatmentRecordsId = treatmentRecordsId;
    }

    public int getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getAdmissionSummary() {
        return admissionSummary;
    }

    public void setAdmissionSummary(String admissionSummary) {
        this.admissionSummary = admissionSummary;
    }

    public String getTreatmentDetail() {
        return treatmentDetail;
    }

    public void setTreatmentDetail(String treatmentDetail) {
        this.treatmentDetail = treatmentDetail;
    }

    public String getDischargeSummary() {
        return dischargeSummary;
    }

    public void setDischargeSummary(String dischargeSummary) {
        this.dischargeSummary = dischargeSummary;
    }

    public String getAssessmentResult() {
        return assessmentResult;
    }

    public void setAssessmentResult(String assessmentResult) {
        this.assessmentResult = assessmentResult;
    }

    public String getDoctorSignature() {
        return doctorSignature;
    }

    public void setDoctorSignature(String doctorSignature) {
        this.doctorSignature = doctorSignature;
    }
}
