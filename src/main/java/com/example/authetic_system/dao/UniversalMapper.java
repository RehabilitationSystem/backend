package com.example.authetic_system.dao;

import com.example.authetic_system.entity.Doctor;
import com.example.authetic_system.entity.Equipment;
import com.example.authetic_system.entity.Patient;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UniversalMapper {
    @Insert("INSERT INTO doctor(major_field, department, department_address, name, age, gender, account, password, role) " + "VALUES(#{majorField}, #{department}, #{departmentAddress}, #{name}, #{age}, #{gender}, #{account}, #{password}, #{role})")
    Integer insertDoctor(Doctor doctor);

    @Insert("INSERT INTO Patient(medical_information, name, gender, account, age, password, role) " + "VALUES(#{medical_information}, #{department}, #{name}, #{gender}, #{account}, #{age}, #{password}, #{role}, ")
    Integer insertPatient(Patient patient);

    @Update("UPDATE `equipment` SET equipment_status = #{equipmentStatus} WHERE id = #{id};")
    Integer updateEquipmentStatus(Equipment equipment);


    @Select("SELECT * FROM doctor WHERE id = #{doctorId}")
    Doctor getDoctorByDoctorId(int doctorId);

    @Select("SELECT * FROM doctor WHERE account = #{account}")
    Doctor getDoctorByDoctorAccount(String account);

    @Select("SELECT * FROM patient WHERE account = #{account}")
    Patient getPatientByPatientAccount(String account);

    @Select("SELECT * FROM doctor")
    List<Doctor> getAllDoctors();

    @Select("SELECT * FROM equipment WHERE id = #{equipmentId}")
    Equipment getEquipmentByEquipmentId(Integer equipment_Id);

    @Select("SELECT * FROM equipment")
    List<Equipment> getAllEquipment();

    @Select("select * from Patient where  patient_Id = #{patient_Id}")
    @Results({

    })
    List<Patient> getAllPatients();

    @Select("SELECT d.*\n" +
            "FROM patient p\n" +
            "INNER JOIN reserve r ON p.id = r.patient_id\n" +
            "INNER JOIN doctor d ON r.doctor_id = d.id\n" +
            "WHERE p.id = #{patient_id}\n" +
            "LIMIT 1;")
    List <Doctor> getDocsByPatientId(Integer patient_id);

}
