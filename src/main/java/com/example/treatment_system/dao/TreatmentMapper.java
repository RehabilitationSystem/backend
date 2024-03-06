package com.example.treatment_system.dao;

import com.example.treatment_system.entity.Treatment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TreatmentMapper {

    @Insert("INSERT INTO treatment (patient_id, treatment_type, treatment_duration, doctor_id, treatment_status, equipment_id, start_time, end_time) " +
            "VALUES (#{treatment.patientId}, #{treatment.treatmentType}, #{treatment.treatmentDuration}, #{treatment.doctorId}, #{treatment.treatmentStatus}, #{treatment.equipmentId}, #{treatment.startTime}, #{treatment.endTime})")
    @Options(useGeneratedKeys = true, keyProperty = "treatment.treatmentId", keyColumn = "treatment_id")
    Integer insertTreatment(@Param("treatment") Treatment treatment);

    @Select("select * from treatment where treatment_id=#{treatment}")
    List<Treatment> getTreatmentByID(Integer treatmentId);

    @Select("select * from treatment")
    @Results({
            @Result(property = "username", column = "username"),
    })
    List<Treatment> getTreatmentList();

}
