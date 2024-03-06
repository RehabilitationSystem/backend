package com.example.authetic_system.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private int doctorId;

    @Column(name = "major_field")
    private String majorField;

    @Column(name = "department")
    private String department;

    @Column(name = "department_address")
    private String departmentAddress;

    @Column(name = "dname")
    private String dname;

    @Column(name = "dage")
    private Integer dage;

    @Column(name = "dgender")
    private String dgender;

    @Column(name = "daccount")
    private String daccount;

    @Column(name = "dpassword")
    private String dpassword;

    @Column(name = "role")
    private Integer role;

    public Doctor(String daccount, String dpassword) {
        this.daccount = daccount;
        this.dpassword = dpassword;
    }

    public Doctor() {

    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getMajorField() {
        return majorField;
    }

    public void setMajorField(String majorField) {
        this.majorField = majorField;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartmentAddress() {
        return departmentAddress;
    }

    public void setDepartmentAddress(String departmentAddress) {
        this.departmentAddress = departmentAddress;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public Integer getDage() {
        return dage;
    }

    public void setDage(Integer dage) {
        this.dage = dage;
    }

    public String getDgender() {
        return dgender;
    }

    public void setDgender(String dgender) {
        this.dgender = dgender;
    }

    public String getDaccount() {
        return daccount;
    }

    public void setDaccount(String daccount) {
        this.daccount = daccount;
    }

    public String getDpassword() {
        return dpassword;
    }

    public void setDpassword(String dpassword) {
        this.dpassword = dpassword;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer drole) {
        this.role = drole;
    }
}
