package com.example.authetic_system.controller;

import com.example.authetic_system.entity.UserRegister;
import com.example.commons.annotation.UnInterception;
import com.example.commons.config.Constants;
import com.example.commons.config.JsonResult;
import com.example.authetic_system.entity.Doctor;
import com.example.authetic_system.entity.Equipment;
import com.example.authetic_system.entity.Patient;
import com.example.authetic_system.service.UniversalService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/universal")
public class UniversalController {

    @Resource
    private UniversalService universalService;
//注册
    @PostMapping("/1.0/register")
    @UnInterception
    public JsonResult Register (@RequestBody UserRegister userRegister){
        if(userRegister.getRole()==0) {
            Doctor doctor = new Doctor(userRegister.getAccount(),userRegister.getPassword());
            universalService.docRegister(doctor);
        }

        else {
            Patient patient = new Patient(userRegister.getAccount(),userRegister.getPassword());
            universalService.paRegister(patient);
        }

        return new JsonResult<>(Constants.SUCCESS_CODE,"注册成功！");
    }
//根据医生id查询医生信息
    @GetMapping("/1.0/getADoc/{doctorId}")
    @UnInterception
    public Doctor getADoctor(@PathVariable  int doctorId) {
        return universalService.getDoctorByDoctorId(doctorId);
    }
//查询医生列表
    @GetMapping("/1.0/getAllDoc")
    @UnInterception
    public JsonResult getAllDoctors() {
        List<Doctor> allDoctors = universalService.getAllDoctors();
        return new JsonResult<>(allDoctors,Constants.SUCCESS_CODE,"success");
    }
//根据仪器id查询信息
    @GetMapping("/1.0/getRquById/{equipment_Id}")
    @UnInterception
    public JsonResult getEquipment(@PathVariable Integer equipment_Id) {
        Equipment equipment = universalService.getEquipmentByEquipmentId(equipment_Id);
        List<Equipment> objects = new ArrayList<>();
        objects.add(equipment);
        return new JsonResult<>(Collections.singletonList(equipment),Constants.SUCCESS_CODE,"success");
    }
    //查询列表设备信息
    @GetMapping("/1.0/getAllEqu")
    @UnInterception
    public JsonResult getAllEquipment() {
        List<Equipment> allEquipment = universalService.getAllEquipment();
        return new JsonResult(allEquipment,Constants.SUCCESS_CODE,"success");
    }

    @GetMapping("/1.0/getAllPa")
    @UnInterception
    public JsonResult getAllPatients() {
        List<Patient> allPatients = universalService.getAllPatients();
        return new JsonResult(allPatients,Constants.SUCCESS_CODE,"success");
    }

//根据设备id修改设备状态
    @PutMapping("/1.0/chgEqu")
    @UnInterception
    public JsonResult changeEquStatus(@RequestBody Equipment equipment) {
        universalService.changeEquStatus(equipment);
        return new JsonResult(Constants.SUCCESS_CODE,"修改设备状态成功");
    }
//
    @PostMapping("/1.0/login")
    @UnInterception
    public JsonResult Login(@RequestBody UserRegister userRegister) {
        Doctor doctor = universalService.docLogin(userRegister.getAccount(), userRegister.getPassword());
        Patient patient = universalService.patLogin(userRegister.getAccount(), userRegister.getPassword());
        if (doctor != null) {
            return new JsonResult(doctor, Constants.SUCCESS_CODE, "login成功！");
        }
        return new JsonResult(patient, Constants.SUCCESS_CODE, "login成功！");

    }



}
