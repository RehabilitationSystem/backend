package com.example.authetic_system.service;

import com.example.authetic_system.dao.UniversalMapper;
import com.example.authetic_system.entity.Doctor;
import com.example.authetic_system.entity.Equipment;
import com.example.authetic_system.entity.Patient;
import com.example.commons.exceptiondeal.BusinessErrorException;
import com.example.commons.exceptiondeal.BusinessMsgEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UniversalServiceImpl implements UniversalService{

    @Resource
    private UniversalMapper universalMapper;
    @Override
    public Doctor docRegister(Doctor doctor){
        Doctor doctor1 = universalMapper.getDoctorByDoctorAccount(doctor.getAccount());
        if(doctor1==null){
            if(universalMapper.insertDoctor(doctor)==0){
                throw new BusinessErrorException(BusinessMsgEnum.DATA_INSERT_EXCEPTION);
            }
            return doctor;
        }
        throw new BusinessErrorException(BusinessMsgEnum.USER_IS_EXISTED);
    }

    @Override
    public Patient paRegister(Patient patient) {
        Patient patientByPatientAccount = universalMapper.getPatientByPatientAccount(patient.getAccount());
        if(patientByPatientAccount==null){
            if(universalMapper.insertPatient(patient)==0){
                throw new BusinessErrorException(BusinessMsgEnum.DATA_INSERT_EXCEPTION);
            }
            return patient;
        }
        throw new BusinessErrorException(BusinessMsgEnum.USER_IS_EXISTED);
    }

    @Override
    public Integer changeEquStatus(Equipment equipment) {
        if(universalMapper.updateEquipmentStatus(equipment)==0){
            throw new BusinessErrorException(BusinessMsgEnum.DATA_UPDATE_EXCEPTION);
        }
        return null;
    }

    @Override
    public Doctor docLogin(String account, String pwd) {
        Doctor doctor = universalMapper.getDoctorByDoctorAccount(account);
        if(doctor==null){return null;}
        if(pwd.equals(doctor.getPassword())){
            return doctor;
        }
        return null;
    }

    @Override
    public Patient patLogin(String account, String pwd) {
        Patient patient = universalMapper.getPatientByPatientAccount(account);
        if(patient==null){return null;}
        if(pwd.equals(patient.getPassword())){
            return patient;
        }
        return null;
    }

    @Override
    public Doctor getDoctorByDoctorId(int doctorId) {
        return universalMapper.getDoctorByDoctorId(doctorId);
    }

    @Override
    public Patient getPaByPaId(int Id) {
        return universalMapper.getPaByPaId(Id);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return universalMapper.getAllDoctors();
    }

    @Override
    public Equipment getEquipmentByEquipmentId(Integer equipment_Id) {
        return universalMapper.getEquipmentByEquipmentId(equipment_Id);
    }

    @Override
    public List<Equipment> getAllEquipment() {
        return universalMapper.getAllEquipment();
    }

    @Override
    public List<Patient> getAllPatients() {
        return universalMapper.getAllPatients();
    }

    @Override
    public List<Doctor> getDocsByPatientId(Integer patient_id) {
        return universalMapper.getDocsByPatientId(patient_id);
    }

    @Override
    public Patient updatePatient(Patient patient) {
        if(universalMapper.updatePatient(patient)==0){
            return null;
        }
        return universalMapper.getPaByPaId(patient.getId());

    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        if(universalMapper.updateDoctor(doctor)==0){
            return null;
        }
        return universalMapper.getDoctorByDoctorId(doctor.getId());
    }


}
