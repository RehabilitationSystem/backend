package com.example.treatment_system.controller;

import com.example.commons.annotation.UnInterception;
import com.example.commons.config.Constants;
import com.example.commons.config.JsonResult;
import com.example.commons.service.TransTimeUtils;
import com.example.treatment_system.entity.Treatment;
import com.example.treatment_system.entity.TreatmentForStamp;
import com.example.treatment_system.service.TreatmentService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/treatment")
public class TreatmentController {

    @Resource
    private TreatmentService treatmentService;

    // 插入治疗记录
    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    @UnInterception
    public JsonResult insertTre(@RequestBody TreatmentForStamp t) {
        Treatment treatment = new Treatment(t.getTreatmentId(),t.getPatientId(),t.getTreatmentType(),t.getTreatmentDuration(),t.getDoctorId(),t.getTreatmentStatus(),t.getEquipmentId(), TransTimeUtils.transTimeStamp(t.getStartTime()),TransTimeUtils.transTimeStamp(t.getEndime()));
        List<Treatment> treatments = treatmentService.insertTre(treatment);
        List<TreatmentForStamp> treatmentForStamps = new ArrayList<>();
        if (treatments.isEmpty()) {
            return new JsonResult<>(treatments, "500","插入治疗记录失败");
        }
        for (Treatment entity : treatments) {
            TreatmentForStamp stamp = new TreatmentForStamp(entity);
            stamp.setStartTime(TransTimeUtils.transLocalDateTimeToTimeStamp(entity.getStartTime()));
            stamp.setEndTime(TransTimeUtils.transLocalDateTimeToTimeStamp(entity.getEndTime()));
            treatmentForStamps.add(stamp);
        }
        return new JsonResult<>(treatmentForStamps, Constants.SUCCESS_CODE,"插入治疗记录成功");
    }

    // 根据ID获取治疗记录
    @GetMapping("/{id}")
    @Transactional(rollbackFor = Exception.class)
    @UnInterception
    public JsonResult getTreByID(@PathVariable Integer id) {
        List<Treatment> treatments = treatmentService.getTreByID(id);
        List<TreatmentForStamp> treatmentForStamps = new ArrayList<>();
        if (treatments.isEmpty()) {
            return new JsonResult<>(treatments, "404","根据ID获取治疗记录失败，找不到内容");
        }
        for (Treatment entity : treatments) {
            TreatmentForStamp stamp = new TreatmentForStamp(entity);
            stamp.setStartTime(TransTimeUtils.transLocalDateTimeToTimeStamp(entity.getStartTime()));
            stamp.setEndTime(TransTimeUtils.transLocalDateTimeToTimeStamp(entity.getEndTime()));
            treatmentForStamps.add(stamp);
        }
        return new JsonResult<>(treatmentForStamps, Constants.SUCCESS_CODE,"根据ID获取治疗记录成功");
    }

    // 获取所有治疗记录
    @GetMapping
    @Transactional(rollbackFor = Exception.class)
    @UnInterception
    public JsonResult getTreList() {
        List<Treatment> treatments = treatmentService.getTreList();
        List<TreatmentForStamp> treatmentForStamps = new ArrayList<>();
        for (Treatment entity : treatments) {
            TreatmentForStamp stamp = new TreatmentForStamp(entity);
            stamp.setStartTime(TransTimeUtils.transLocalDateTimeToTimeStamp(entity.getStartTime()));
            stamp.setEndTime(TransTimeUtils.transLocalDateTimeToTimeStamp(entity.getEndTime()));
            treatmentForStamps.add(stamp);
        }
        return new JsonResult<>(treatmentForStamps,Constants.SUCCESS_CODE,"获取所有治疗记录成功");
    }
}
