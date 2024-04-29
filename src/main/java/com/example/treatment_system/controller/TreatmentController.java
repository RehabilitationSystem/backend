package com.example.treatment_system.controller;

import com.example.commons.annotation.UnInterception;
import com.example.commons.config.Constants;
import com.example.commons.config.JsonResult;
import com.example.commons.exceptiondeal.BusinessErrorException;
import com.example.commons.service.TransTimeUtils;
import com.example.treatment_system.entity.ReservationTreatment;
import com.example.treatment_system.entity.Treatment;
import com.example.treatment_system.entity.TreatmentForStamp;
import com.example.treatment_system.entity.TreatmentRecord;
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
        //新增RT表记录
        treatmentService.insertRT(t.getReservationId(),treatments.get(0).getTreatmentId());

        for (Treatment entity : treatments) {
            TreatmentForStamp stamp = new TreatmentForStamp(entity);
            stamp.setStartTime(TransTimeUtils.transLocalDateTimeToTimeStamp(entity.getStartTime()));
            stamp.setEndTime(TransTimeUtils.transLocalDateTimeToTimeStamp(entity.getEndTime()));
            treatmentForStamps.add(stamp);
        }
        System.out.println();
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

    @GetMapping("getRt/{id}")
    @Transactional(rollbackFor = Exception.class)
    @UnInterception
    public JsonResult getRT(@PathVariable int id) {
        List<ReservationTreatment> rtById = treatmentService.findRTById(id);
        return new JsonResult<>(rtById,Constants.SUCCESS_CODE,"获取RT记录成功");
    }

    @GetMapping("getTreRecord/{id}")
    @Transactional(rollbackFor = Exception.class)
    @UnInterception
    public JsonResult getTreRecordByTRId(@PathVariable int id) {
        List<TreatmentRecord> treatmentRecords = treatmentService.findById(id);
        if (treatmentRecords.isEmpty()) {
            return new JsonResult<>(treatmentRecords, "404", "根据ID获取治疗记录失败，找不到内容");
        }
        return new JsonResult<>(treatmentRecords, Constants.SUCCESS_CODE, "根据ID获取治疗记录成功");
    }

    @GetMapping("getAllTreRecord")
    @Transactional(rollbackFor = Exception.class)
    @UnInterception
    public JsonResult getTreRecords() {
        List<TreatmentRecord> treatmentRecords = treatmentService.getTreRecords();
        if (treatmentRecords.isEmpty()) {
            return new JsonResult<>(treatmentRecords, "404", "根据ID获取治疗记录失败，找不到内容");
        }
        return new JsonResult<>(treatmentRecords, Constants.SUCCESS_CODE, "根据ID获取治疗记录成功");
    }


    @GetMapping("getTreRecord2/{id}")
    @Transactional(rollbackFor = Exception.class)
    @UnInterception
    public JsonResult getTreRecordByTId(@PathVariable int id) {
        List<TreatmentRecord> treatmentRecords = treatmentService.findTreRecordByTid(id);
        if (treatmentRecords.isEmpty()) {
            return new JsonResult<>(treatmentRecords, "404", "根据ID获取治疗记录失败，找不到内容");
        }
        return new JsonResult<>(treatmentRecords, Constants.SUCCESS_CODE, "根据ID获取治疗记录成功");
    }




    @PostMapping("/insertTreRecord")
    @Transactional(rollbackFor = Exception.class)
    @UnInterception
    public JsonResult insertTreRecord(@RequestBody TreatmentRecord treatmentRecord) {
        List<TreatmentRecord> insertedRecords = treatmentService.insertTreRecord(treatmentRecord);
        if (insertedRecords.isEmpty()) {
            return new JsonResult<>(insertedRecords, "500", "插入治疗记录失败");
        }
        return new JsonResult<>(insertedRecords, Constants.SUCCESS_CODE, "插入治疗记录成功");
    }


    @DeleteMapping("/{id}")
    @Transactional(rollbackFor = Exception.class)
    @UnInterception
    public JsonResult deleteTreRecord(@PathVariable int id) {
        try {
            treatmentService.deleteTreRecord(id);
            return new JsonResult<>(null, Constants.SUCCESS_CODE, "删除治疗记录成功");
        } catch (BusinessErrorException e) {
            return new JsonResult<>(null, "500", e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Transactional(rollbackFor = Exception.class)
    @UnInterception
    public JsonResult updateTreRecord(@PathVariable int id, @RequestBody TreatmentRecord treatmentRecord) {
        try {
            treatmentService.updateTreRecord(treatmentRecord);
            return new JsonResult<>(null, Constants.SUCCESS_CODE, "更新治疗记录成功");
        } catch (BusinessErrorException e) {
            return new JsonResult<>(null, "500", e.getMessage());
        }
    }


}
