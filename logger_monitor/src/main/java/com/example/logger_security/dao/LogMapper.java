package com.example.logger_security.dao;

import com.example.logger_security.service.SystemLog;
import org.apache.ibatis.annotations.*;

@Mapper
public interface LogMapper {
    @Insert("INSERT INTO system_log (account, channel, name, action, url, params, ip, log_time, err_msg) " +
            "VALUES (#{account}, #{channel}, #{name}, #{action}, #{url}, #{params}, #{ip}, #{logTime}, #{errMsg})")
    int insertSystemLog(SystemLog systemLog);
}
