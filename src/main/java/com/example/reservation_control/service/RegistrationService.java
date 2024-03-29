package com.example.reservation_control.service;

import com.example.reservation_control.entity.Registration;

import java.util.List;

public interface RegistrationService {
    List<Registration> insertRegistration(Registration registration);

    List<Registration> getAllRegistration();

    Integer deleteRegistration(Integer registration_id);

    Integer changeStatus(Integer registration_id,String status);
}
