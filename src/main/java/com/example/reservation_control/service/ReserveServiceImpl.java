package com.example.reservation_control.service;

import com.example.commons.exceptiondeal.BusinessErrorException;
import com.example.commons.exceptiondeal.BusinessMsgEnum;
import com.example.reservation_control.dao.ReserveMapper;
import com.example.reservation_control.entity.Reserve;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ReserveServiceImpl implements ReserveService{

    @Resource
    private ReserveMapper reserveMapper;

    @Override
    public Integer delReserve(Integer reservation_id) {
        if(reserveMapper.deleteReserve(reservation_id)==0){
            throw new BusinessErrorException(BusinessMsgEnum.UNEXPECTED_EXCEPTION);
        }
        return null;
    }

    @Override
    public Integer chgStatus(Integer reservation_id, Integer status) {
        if(reserveMapper.changeStatus(reservation_id,status)==0){
            throw new BusinessErrorException(BusinessMsgEnum.DATA_UPDATE_EXCEPTION);
        }
        return null;
    }

    @Override
    public Integer chgCompleted(Integer reservation_id, Integer completed) {
        if(reserveMapper.changeCompleted(reservation_id,completed)==0){
            throw new BusinessErrorException(BusinessMsgEnum.DATA_UPDATE_EXCEPTION);
        }
        return null;
    }

    @Override
    public List<Reserve> getByResID(Integer reservation_id) {
        return reserveMapper.getByReserveID(reservation_id);
    }

    @Override
    public List<Reserve> getByUserID(Integer goal_id) {
        return reserveMapper.getsByUserID(goal_id);
    }

    @Override
    public List<Reserve> insertRes(Reserve reserve) {
        Integer result = reserveMapper.insertReserve(reserve);
        if(result>0){
            List<Reserve> insertedReserves = new ArrayList<>();
            insertedReserves.add(reserve);
            return insertedReserves;
        }
        return Collections.emptyList();
    }

    @Override
    public List<Reserve> getsAllReserve() {
        return reserveMapper.getsAllReserve();
    }


}
