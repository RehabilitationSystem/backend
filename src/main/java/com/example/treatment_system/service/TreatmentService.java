package com.example.treatment_system.service;

import com.example.treatment_system.entity.ReservationTreatment;
import com.example.treatment_system.entity.Treatment;
import com.example.treatment_system.entity.TreatmentRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TreatmentService {

    List<Treatment> insertTre(Treatment treatment);

    List<Treatment> getTreByID(Integer treatmentId);

    List<Treatment> getTreList();

    Integer insertRT( int reservationId, Integer treatmentId);

    List<ReservationTreatment> findRTById(int id);

    List<TreatmentRecord> findById(int id);

    List<TreatmentRecord> insertTreRecord(TreatmentRecord treatmentRecord);

    Integer deleteTreRecord(int id);

    Integer updateTreRecord(TreatmentRecord treatmentRecord);

    List<TreatmentRecord> findTreRecordByTid(int id);

    List<TreatmentRecord> getTreRecords();
}
