package com.example.authetic_system.service;

import com.example.authetic_system.entity.Doctor;
import com.example.authetic_system.entity.Equipment;
import com.example.authetic_system.entity.Patient;

import java.util.List;

public interface UniversalService {
    public Doctor docRegister(Doctor doctor);

    public Patient paRegister(Patient patient);
    Integer changeEquStatus(Equipment equipment);

    Doctor docLogin(String account,String pwd);
    Patient patLogin(String account,String pwd);
    Doctor getDoctorByDoctorId(int doctorId);

    Patient getPaByPaId(int Id);

    List<Doctor> getAllDoctors();

    Equipment getEquipmentByEquipmentId(Integer equipment_Id);

    List<Equipment> getAllEquipment();

    List<Patient> getAllPatients();

    List<Doctor> getDocsByPatientId(Integer patient_id);


    Patient updatePatient(Patient patient);

    Doctor updateDoctor(Doctor doctor);
}
