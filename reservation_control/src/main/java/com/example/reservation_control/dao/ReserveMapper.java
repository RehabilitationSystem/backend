package com.example.reservation_control.dao;

import com.example.reservation_control.entity.Reserve;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReserveMapper {
        @Delete("DELETE FROM `reserve` WHERE `reservation_id` = #{reservation_id};")
        Integer deleteReserve(Integer reservation_id);

        @Update("UPDATE `reserve` SET appointment_status = #{status} WHERE reservation_id = #{reservation_id};")
        Integer changeStatus(Integer reservation_id,Integer status);

        @Select("Select * from reserve where reservation_id = #{reservation_id}")
        List<Reserve> getByReserveID(Integer reservation_id);

        @Select("Select * from reserve where patient_id = #{goal_id} or doctor_id = #{goal_id}")
        List<Reserve> getsByUserID(Integer goal_id);

        @Insert("INSERT INTO `reserve` (`patient_id`, `doctor_id`, `treatment_time`, `reservation_context`, `appointment_status`, `cost`, `evaluation`) " +
                "VALUES (#{patientId}, #{doctorId}, #{treatmentTime}, #{reservationContext}, #{appointmentStatus}, #{cost}, #{evaluation})")
        @Options(useGeneratedKeys = true, keyProperty = "reservationId", keyColumn = "reservation_id")
        Integer insertReserve(Reserve reserve);

}

