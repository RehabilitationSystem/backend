package com.example.authetic_system.service;

import com.example.commons.exceptiondeal.BusinessErrorException;
import com.example.commons.exceptiondeal.BusinessMsgEnum;
import com.example.authetic_system.dao.UniversalMapper;
import com.example.authetic_system.entity.Doctor;
import com.example.authetic_system.entity.Equipment;
import com.example.authetic_system.entity.Patient;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversalServiceImpl implements UniversalService{

    @Resource
    private UniversalMapper universalMapper;
    @Override
    public Doctor docRegister(Doctor doctor){
        if(universalMapper.insertDoctor(doctor)==0){
            throw new BusinessErrorException(BusinessMsgEnum.DATA_INSERT_EXCEPTION);
        }
        return doctor;
    }

    @Override
    public Patient paRegister(Patient patient) {
        if(universalMapper.insertPatient(patient)==0){
            throw new BusinessErrorException(BusinessMsgEnum.DATA_INSERT_EXCEPTION);
        }
        return patient;
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
        if(pwd.equals(doctor.getDpassword())){
            return doctor;
        }
        return null;
    }

    @Override
    public Patient patLogin(String account, String pwd) {
        Patient patient = universalMapper.getPatientByPatientAccount(account);
        if(patient==null){return null;}
        if(pwd.equals(patient.getPpassword())){
            return patient;
        }
        return null;
    }

    @Override
    public Doctor getDoctorByDoctorId(int doctorId) {
        return universalMapper.getDoctorByDoctorId(doctorId);
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


}
