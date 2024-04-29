package com.example.treatment_system.dao;

import com.example.treatment_system.entity.ReservationTreatment;
import com.example.treatment_system.entity.Treatment;
import com.example.treatment_system.entity.TreatmentRecord;
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


    @Select("SELECT * FROM reservation_treatment WHERE reservation_id = #{id}")
    List<ReservationTreatment> findRTById(@Param("id") int id);

    @Insert("INSERT INTO reservation_treatment (reservation_id, treatment_id) VALUES (#{reservationId}, #{treatmentId})")
    int insertRT(@Param("reservationId") int reservationId, @Param("treatmentId") Integer treatmentId);


    // 根据ID查询记录
    @Select("SELECT * FROM treatmentRecords WHERE treatmentRecords_id = #{id}")
    List<TreatmentRecord> findTreRecord(@Param("id") int id);

    // 根据ID查询记录
    @Select("SELECT * FROM treatmentRecords WHERE treatment_id = #{id}")
    List<TreatmentRecord> findTreRecordByTid(@Param("id") int id);

    // 查询所有记录
    @Select("SELECT * FROM treatmentRecords")
    List<TreatmentRecord> getTreRecords();

    // 新增记录
    @Insert("INSERT INTO treatmentRecords (treatment_id, admissionSummary, treatmentDetail, dischargeSummary, assessmentResult, doctorSignature) VALUES (#{treatmentId}, #{admissionSummary}, #{treatmentDetail}, #{dischargeSummary}, #{assessmentResult}, #{doctorSignature})")
    @Options(useGeneratedKeys = true, keyProperty = "treatmentRecordsId", keyColumn = "treatmentRecords_id")
    int insertTreRecord(TreatmentRecord treatmentRecord);

    // 删除记录
    @Delete("DELETE FROM treatmentRecords WHERE treatmentRecords_id = #{id}")
    int deleteTreRecord(@Param("id") int id);

    // 修改记录
    @Update("UPDATE treatmentRecords SET treatment_id = #{treatmentId}, admissionSummary = #{admissionSummary}, treatmentDetail = #{treatmentDetail}, dischargeSummary = #{dischargeSummary}, assessmentResult = #{assessmentResult}, doctorSignature = #{doctorSignature} WHERE treatmentRecords_id = #{treatmentRecordsId}")
    int updateTreRecord(TreatmentRecord treatmentRecord);
}
