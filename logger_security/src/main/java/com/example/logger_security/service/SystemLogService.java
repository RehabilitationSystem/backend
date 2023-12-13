package com.example.logger_security.service;

import com.example.commons.exceptiondeal.BusinessErrorException;
import com.example.commons.exceptiondeal.BusinessMsgEnum;
import com.example.logger_security.dao.LogMapper;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class SystemLogService {
    @Resource
    LogMapper logMapper;

    @SneakyThrows
    public void insert(SystemLog systemLog){
        if(logMapper.insertSystemLog(systemLog)==0){
            throw new BusinessErrorException(BusinessMsgEnum.DATA_INSERT_EXCEPTION);
        }

    }
}
