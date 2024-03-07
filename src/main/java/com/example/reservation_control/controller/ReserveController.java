package com.example.reservation_control.controller;

import com.example.commons.annotation.UnInterception;
import com.example.commons.config.Constants;
import com.example.commons.config.JsonResult;
import com.example.commons.exceptiondeal.BusinessErrorException;
import com.example.commons.exceptiondeal.BusinessMsgEnum;
import com.example.commons.service.TransTimeUtils;
import com.example.reservation_control.entity.Reserve;
import com.example.reservation_control.entity.ReserveForTimeStamp;
import com.example.reservation_control.service.ReserveService;
import com.example.treatment_system.entity.Treatment;
import com.example.treatment_system.entity.TreatmentForStamp;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reserve")
public class ReserveController {
    @Resource
    private ReserveService reserveService;


    @PostMapping("/insert")
    @Transactional(rollbackFor = Exception.class)
    @UnInterception
    public JsonResult insertReserve (@RequestBody ReserveForTimeStamp r){
        String msg;
        Reserve reserve = new Reserve(r.getReservationId(),r.getPatientId(),r.getDoctorId(),TransTimeUtils.transTimeStamp(r.getTreatmentTime()),r.getReservationContext(),r.getAppointmentStatus(),r.getCost(),r.getEvaluation());
        List<Reserve> reserves = reserveService.insertRes(reserve);
        List<ReserveForTimeStamp> reserveForTimeStamps = new ArrayList<>();
        if(reserves.size()>0){
            msg = "预约记录插入成功！";
        }else {
            msg = "预约记录插入失败！";
        }
        for (Reserve entity : reserves) {
            ReserveForTimeStamp stamp = new ReserveForTimeStamp(entity);
            stamp.setTreatmentTime(TransTimeUtils.transLocalDateTimeToTimeStamp(entity.getTreatmentTime()));
            reserveForTimeStamps.add(stamp);
        }
        return new JsonResult(reserveForTimeStamps,Constants.SUCCESS_CODE,msg);
    }

    @DeleteMapping("/{reserveId}")
    @Transactional(rollbackFor = Exception.class)
    @UnInterception
    public JsonResult delReserve (@PathVariable Integer reserveId){
        try {
            reserveService.delReserve(reserveId);
        } catch (BusinessErrorException e) {
            return new JsonResult("404","删除失败，可能是删除的预约id找不到记录");
        }
        return new JsonResult(Constants.SUCCESS_CODE,"删除成功");
    }

    // 根据用户ID获取单挑预订记录
    @GetMapping("/by-reservation-id/{reservationId}")
    @Transactional(rollbackFor = Exception.class)
    @UnInterception
    public JsonResult getByResID(@PathVariable Integer reservationId) {
        List<Reserve> reserves = reserveService.getByResID(reservationId);
        List<ReserveForTimeStamp> reserveForTimeStamps = new ArrayList<>();
        if (reserves != null && !reserves.isEmpty()) {
            for (Reserve entity : reserves) {
                ReserveForTimeStamp stamp = new ReserveForTimeStamp(entity);
                stamp.setTreatmentTime(TransTimeUtils.transLocalDateTimeToTimeStamp(entity.getTreatmentTime()));
                reserveForTimeStamps.add(stamp);
            }
            return new JsonResult<>(reserves,Constants.SUCCESS_CODE,"根据预约id获取信息成功");
        } else {
            return new JsonResult<>("404","根据预约id找不到对应的信息");
        }
    }

    // 根据用户ID获取预订列表
    @GetMapping("/by-user-id/{goalId}")
    @Transactional(rollbackFor = Exception.class)
    @UnInterception
    public JsonResult getByUserID(@PathVariable Integer goalId) {
        List<Reserve> reserves = reserveService.getByUserID(goalId);
        List<ReserveForTimeStamp> reserveForTimeStamps = new ArrayList<>();
        if (reserves != null && !reserves.isEmpty()) {
            for (Reserve entity : reserves) {
                ReserveForTimeStamp stamp = new ReserveForTimeStamp(entity);
                stamp.setTreatmentTime(TransTimeUtils.transLocalDateTimeToTimeStamp(entity.getTreatmentTime()));
                reserveForTimeStamps.add(stamp);
            }
            return new JsonResult<>(reserves,Constants.SUCCESS_CODE,"根据用户ID获取预订列表成功");
        } else {
            // 如果没有找到任何预订，返回404 Not Found
            return new JsonResult<>(reserves,"404","根据用户ID获取预订列表失败,查没有内容");
        }
    }

    //更改预订状态
    @PutMapping("")
    @Transactional(rollbackFor = Exception.class)
    @UnInterception
    public JsonResult chgStatus(@RequestBody Reserve reserve) {
        try {
            // 调用服务层方法更改状态
            reserveService.chgStatus(reserve.getReservationId(),reserve.getAppointmentStatus());
            return new JsonResult<>(Constants.SUCCESS_CODE,"根据用户ID获取预订列表成功");
        } catch (BusinessErrorException e) {
            // 捕获业务异常并返回相应的错误信息
            return new JsonResult("500",e.getMessage());
        }
    }


}
