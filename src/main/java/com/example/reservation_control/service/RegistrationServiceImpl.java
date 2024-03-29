package com.example.reservation_control.service;

import com.example.commons.exceptiondeal.BusinessErrorException;
import com.example.commons.exceptiondeal.BusinessMsgEnum;
import com.example.reservation_control.dao.RegistrationMapper;
import com.example.reservation_control.entity.Registration;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService{

    @Resource
    private RegistrationMapper registrationMapper;

    @Override
    public List<Registration> insertRegistration(Registration registration) {
        Integer result = registrationMapper.insertRegistration(registration);
        if(result>0){
            List<Registration> insertedReserves = new ArrayList<>();
            insertedReserves.add(registration);
            return insertedReserves;
        }
        return Collections.emptyList();
    }

    @Override
    public List<Registration> getAllRegistration() {
        return registrationMapper.getAllRegistration();
    }

    @Override
    public Integer deleteRegistration(Integer registration_id) {
        if(registrationMapper.deleteRegistration(registration_id)==0){
            throw new BusinessErrorException(BusinessMsgEnum.UNEXPECTED_EXCEPTION);
        }
        return null;
    }

    @Override
    public Integer changeStatus(Integer registration_id, String status) {
        if(registrationMapper.changeStatus(registration_id,status)==0){
            throw new BusinessErrorException(BusinessMsgEnum.DATA_UPDATE_EXCEPTION);
        }
        return null;
    }
}
