package com.example.reservation_control.dao;

import com.example.reservation_control.entity.Registration;
import com.example.reservation_control.entity.Reserve;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RegistrationMapper {
    @Insert("INSERT INTO registration (patient_id, reservation_id, registration_time, status) VALUES (#{patientId}, #{reservationId}, #{registrationTime}, #{status});")
    Integer insertRegistration(Registration registration);

    @Select("select * from registration")
    List<Registration> getAllRegistration();


    @Delete("DELETE FROM registration WHERE registration_id = #{registration_id};")
    Integer deleteRegistration(Integer registration_id);

    @Update("UPDATE registration SET status = #{status} WHERE registration_id = #{registration_id}")
    Integer changeStatus(Integer registration_id,String status);


}

