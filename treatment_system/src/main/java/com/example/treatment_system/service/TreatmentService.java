package com.example.treatment_system.service;

import com.example.treatment_system.entity.Treatment;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TreatmentService {

    List<Treatment> insertTre(Treatment treatment);

    List<Treatment> getTreByID(Integer treatmentId);

    List<Treatment> getTreList();
}
