package com.example.authetic_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "patient")
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private int patientId;

    @Column(name = "medical_information")
    private String medicalInformation;

    @Column(name = "pname")
    private String pname;

    @Column(name = "pgender")
    private String pgender;

    @Column(name = "paccount")
    private String paccount;

    @Column(name = "page")
    private Integer page;

    @Column(name = "ppassword")
    private String ppassword;

    @Column(name = "prole")
    private Integer prole;

    public Patient(String paccount, String ppassword) {
        this.paccount = paccount;
        this.ppassword = ppassword;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getMedicalInformation() {
        return medicalInformation;
    }

    public void setMedicalInformation(String medicalInformation) {
        this.medicalInformation = medicalInformation;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPgender() {
        return pgender;
    }

    public void setPgender(String pgender) {
        this.pgender = pgender;
    }

    public String getPaccount() {
        return paccount;
    }

    public void setPaccount(String paccount) {
        this.paccount = paccount;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getPpassword() {
        return ppassword;
    }

    public void setPpassword(String ppassword) {
        this.ppassword = ppassword;
    }

    public Integer getProle() {
        return prole;
    }

    public void setProle(Integer prole) {
        this.prole = prole;
    }


}
