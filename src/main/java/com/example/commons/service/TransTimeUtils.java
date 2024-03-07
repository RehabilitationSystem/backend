package com.example.commons.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TransTimeUtils {
   public static LocalDateTime transTimeStamp(Long timestamp){


       Instant instant = Instant.ofEpochMilli(timestamp);

       // 将Instant对象转换为LocalDateTime对象
       // 你需要指定一个时区（ZoneId），因为LocalDateTime不包含时区信息
       LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
       return localDateTime;
   }

    // 新增的方法，将LocalDateTime转换为时间戳
    public static Long transLocalDateTimeToTimeStamp(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
