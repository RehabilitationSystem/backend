package com.example.reservation_control.service;

import com.example.reservation_control.entity.Reserve;


import java.util.List;

public interface ReserveService {
    Integer delReserve(Integer reservation_id);

    Integer chgStatus(Integer reservation_id,Integer status);

    Integer chgCompleted(Integer reservation_id,Integer completed);

    List<Reserve> getByResID(Integer reservation_id);

    List<Reserve> getByUserID(Integer goal_id);

    List<Reserve> insertRes(Reserve reserve );

    List<Reserve> getsAllReserve();

}
