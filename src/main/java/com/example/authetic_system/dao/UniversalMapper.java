package com.example.authetic_system.dao;

import com.example.authetic_system.entity.Doctor;
import com.example.authetic_system.entity.Equipment;
import com.example.authetic_system.entity.Patient;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UniversalMapper {
    @Insert("INSERT INTO doctor(major_field, department, department_address, dname, dage, dgender, daccount, dpassword, role) " + "VALUES(#{majorField}, #{department}, #{departmentAddress}, #{dname}, #{dage}, #{dgender}, #{daccount}, #{dpassword}, #{role})")
    Integer insertDoctor(Doctor doctor);

    @Insert("INSERT INTO Patient(medical_information, pname, pgender, paccount, page, ppassword, role) " + "VALUES(#{medical_information}, #{department}, #{pname}, #{pgender}, #{paccount}, #{page}, #{ppassword}, #{role}, ")
    Integer insertPatient(Patient patient);

    @Update("UPDATE `equipment` SET equipment_status = #{equipmentStatus} WHERE equipment_id = #{equipmentId};")
    Integer updateEquipmentStatus(Equipment equipment);


    @Select("SELECT * FROM doctor WHERE doctor_id = #{doctorId}")
    Doctor getDoctorByDoctorId(int doctorId);

    @Select("SELECT * FROM doctor WHERE daccount = #{account}")
    Doctor getDoctorByDoctorAccount(String account);

    @Select("SELECT * FROM patient WHERE paccount = #{account}")
    Patient getPatientByPatientAccount(String account);

    @Select("SELECT * FROM doctor")
    List<Doctor> getAllDoctors();

    @Select("SELECT * FROM equipment WHERE equipment_id = #{equipmentId}")
    Equipment getEquipmentByEquipmentId(Integer equipment_Id);

    @Select("SELECT * FROM equipment")
    List<Equipment> getAllEquipment();

    @Select("select * from Patient where  patient_Id = #{patient_Id}")
    @Results({

    })
    List<Patient> getAllPatients();

    @Select("WITH RankedDoctors AS (\n" +
            "    SELECT d.*,\n" +
            "           ROW_NUMBER() OVER (PARTITION BY d.doctor_id ORDER BY d.doctor_id) AS RowNum\n" +
            "    FROM patient p\n" +
            "    INNER JOIN reserve r ON p.patient_id = r.patient_id\n" +
            "    INNER JOIN doctor d ON r.doctor_id = d.doctor_id\n" +
            "    WHERE p.patient_id = #{patient_id}\n" +
            ")\n" +
            "SELECT * FROM RankedDoctors WHERE RowNum = 1;\n")
    List <Doctor> getDocsByPatientId(Integer patient_id);

}
