package com.example.treatment_system.service;

import com.example.treatment_system.dao.TreatmentMapper;
import com.example.treatment_system.entity.Treatment;
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
}
