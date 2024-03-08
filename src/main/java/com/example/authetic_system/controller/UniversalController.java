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
import org.springframework.transaction.annotation.Transactional;
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
        if(userRegister.getRole()==1) {
            Doctor doctor = new Doctor(userRegister.getAccount(),userRegister.getPassword(),userRegister.getRole());
            universalService.docRegister(doctor);
        }

        else {
            Patient patient = new Patient(userRegister.getAccount(),userRegister.getPassword(),userRegister.getRole());
            universalService.paRegister(patient);
        }

        return new JsonResult<>(Constants.SUCCESS_CODE,"注册成功！");
    }
//根据医生id查询医生信息
    @GetMapping("/1.0/getADoc/{doctorId}")
    @UnInterception
    public JsonResult getADoctor(@PathVariable  int doctorId) {
        ArrayList<Doctor> doctors = new ArrayList<>();
        doctors.add(universalService.getDoctorByDoctorId(doctorId));
        return new JsonResult<>(doctors,Constants.SUCCESS_CODE,"获取医生信息成功！");
    }

    //根据患者id查询患者信息
    @GetMapping("/1.0/getAPat/{patientId}")
    @UnInterception
    public JsonResult getAPatient(@PathVariable  int patientId) {
        ArrayList<Patient> patients = new ArrayList<>();
        patients.add(universalService.getPaByPaId(patientId));
        return new JsonResult<>(patients,Constants.SUCCESS_CODE,"获取患者信息成功！");
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
        return new JsonResult(patient,"402" , "login失败！");

    }

    //根据患者id获取预约的所以医生的医生信息
    @GetMapping("/1.0/getDocsByPatId/{patient_id}")
    @Transactional(rollbackFor = Exception.class)
    @UnInterception
    public JsonResult getDocsByPatientId(@PathVariable Integer patient_id) {
        List<Doctor> doctors = universalService.getDocsByPatientId(patient_id);
        if (doctors != null) {
            return new JsonResult<>(doctors,Constants.SUCCESS_CODE,"根据患者id获取预约的所以医生的医生信息成功！");
        } else {
            return new JsonResult<>(doctors,"404","根据患者id获取预约的所以医生的医生信息失败，没有内容！");
        }
    }

}
