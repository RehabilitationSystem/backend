package com.example.reservation_control.controller;

import com.example.commons.annotation.UnInterception;
import com.example.commons.config.Constants;
import com.example.commons.config.JsonResult;
import com.example.commons.exceptiondeal.BusinessErrorException;
import com.example.commons.service.TransTimeUtils;
import com.example.reservation_control.entity.Registration;
import com.example.reservation_control.entity.RegistrationForTimeStamp;
import com.example.reservation_control.entity.Reserve;
import com.example.reservation_control.entity.ReserveForTimeStamp;
import com.example.reservation_control.service.RegistrationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    @Resource
    private RegistrationService registrationService;

    @PostMapping("")
    @Transactional(rollbackFor = Exception.class)
    @UnInterception
    public JsonResult insertRegistration (@RequestBody RegistrationForTimeStamp r){
        String msg;
        Registration registration = new Registration(r.getRegistrationId(),r.getPatientId(),r.getReservationId(),TransTimeUtils.transTimeStamp(r.getRegistrationTime()),r.getStatus());
        List<Registration> registrations = registrationService.insertRegistration(registration);
        List<RegistrationForTimeStamp> reserveForTimeStamps = new ArrayList<>();
        if(registrations.size()>0){
            msg = "预约记录插入成功！";
        }else {
            msg = "预约记录插入失败！";
        }
        for (Registration entity : registrations) {
            RegistrationForTimeStamp stamp = new RegistrationForTimeStamp(entity);
            stamp.setRegistrationTime(TransTimeUtils.transLocalDateTimeToTimeStamp(entity.getRegistrationTime()));
            reserveForTimeStamps.add(stamp);
        }
        return new JsonResult(reserveForTimeStamps, Constants.SUCCESS_CODE,msg);
    }


    @GetMapping("")
    @Transactional(rollbackFor = Exception.class)
    @UnInterception
    public JsonResult getAllRegistration() {
        List<Registration> allRegistration = registrationService.getAllRegistration();
        List<RegistrationForTimeStamp> registrationForTimeStamps = new ArrayList<>();
        for(Registration registration:allRegistration){
            RegistrationForTimeStamp aStamp = new RegistrationForTimeStamp(registration);
            aStamp.setRegistrationTime(TransTimeUtils.transLocalDateTimeToTimeStamp(registration.getRegistrationTime()));
            registrationForTimeStamps.add(aStamp);
        }
        return new JsonResult<>(registrationForTimeStamps,Constants.SUCCESS_CODE,"成功");
    }

    //更改状态
    @PutMapping("")
    @Transactional(rollbackFor = Exception.class)
    @UnInterception
    public JsonResult chgStatus(@RequestBody Registration registration) {
        try {
            // 调用服务层方法更改状态
            registrationService.changeStatus(registration.getRegistrationId(),registration.getStatus());
            return new JsonResult<>(Constants.SUCCESS_CODE,"成功");
        } catch (BusinessErrorException e) {
            // 捕获业务异常并返回相应的错误信息
            return new JsonResult("500",e.getMessage());
        }
    }


    @DeleteMapping("/{registrationId}")
    @Transactional(rollbackFor = Exception.class)
    @UnInterception
    public JsonResult delReserve (@PathVariable Integer registrationId){
        try {
            registrationService.deleteRegistration(registrationId);
        } catch (BusinessErrorException e) {
            return new JsonResult("404","删除失败");
        }
        return new JsonResult(Constants.SUCCESS_CODE,"删除成功");
    }
}
