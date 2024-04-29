package com.example.treatment_system.service;

import com.example.commons.exceptiondeal.BusinessErrorException;
import com.example.commons.exceptiondeal.BusinessMsgEnum;
import com.example.reservation_control.entity.Reserve;
import com.example.treatment_system.dao.TreatmentMapper;
import com.example.treatment_system.entity.ReservationTreatment;
import com.example.treatment_system.entity.Treatment;
import com.example.treatment_system.entity.TreatmentRecord;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TreatmentServiceImpl implements TreatmentService{

    @Resource
    private TreatmentMapper treatmentMapper;

    @Override
    public List<Treatment> insertTre(Treatment treatment) {
        Integer result = treatmentMapper.insertTreatment(treatment);
        if(result>0){
            List<Treatment> insertedTreatment = new ArrayList<>();
            insertedTreatment.add(treatment);
            return insertedTreatment;
        }
        return Collections.emptyList();
    }

    @Override
    public List<Treatment> getTreByID(Integer treatmentId) {
        return treatmentMapper.getTreatmentByID(treatmentId);
    }

    @Override
    public List<Treatment> getTreList() {
        return treatmentMapper.getTreatmentList();
    }

    @Override
    public Integer insertRT(int reservationId, Integer treatmentId) {
        return treatmentMapper.insertRT(reservationId,treatmentId);
    }

    @Override
    public List<ReservationTreatment> findRTById(int id) {
        return treatmentMapper.findRTById(id);
    }

    @Override
    public List<TreatmentRecord> findById(int id) {
        return treatmentMapper.findTreRecord(id);
    }

    @Override
    public List<TreatmentRecord> insertTreRecord(TreatmentRecord treatmentRecord) {
        Integer result = treatmentMapper.insertTreRecord(treatmentRecord);
        if(result>0){
            List<TreatmentRecord> insert = new ArrayList<>();
            insert.add(treatmentRecord);
            return insert;
        }
        return Collections.emptyList();
    }

    @Override
    public Integer deleteTreRecord(int id) {
        if(treatmentMapper.deleteTreRecord(id)==0){
            throw new BusinessErrorException(BusinessMsgEnum.UNEXPECTED_EXCEPTION);
        }
        return null;
    }

    @Override
    public Integer updateTreRecord(TreatmentRecord treatmentRecord) {
        if(treatmentMapper.updateTreRecord(treatmentRecord)==0){
            throw new BusinessErrorException(BusinessMsgEnum.DATA_UPDATE_EXCEPTION);
        }
        return null;
    }

    @Override
    public List<TreatmentRecord> findTreRecordByTid(int id) {
        return treatmentMapper.findTreRecordByTid(id);
    }

    @Override
    public List<TreatmentRecord> getTreRecords() {
        return treatmentMapper.getTreRecords();
    }


}
