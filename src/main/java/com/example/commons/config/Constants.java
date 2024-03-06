package com.example.commons.config;

import org.springframework.beans.factory.annotation.Value;

public final class Constants {

    // redis存储的次键名称
    public static final String COUNTER_KEY = "counter";

    public static final String NUMBER_KEY = "numbers";

    public static final String STATUS_KEY = "status";

    public static final String SESSION_KEY = "sessionid";

    public static final String SUCCESS_CODE = "200";

//    实体类的列名
     public static final String USERNAME="username";

    public static final String PHONE="phone";

    public static final String AGE="age";

    public static final String GENDER = "gender";
//    登录相关的常量
    public static final String PREFIX_USER="user";

    public static final String PREFIX_COUNTER="counter";

    public static final Integer WRONG_PASSWORD = 5;

    public static final Integer LOGIN_TIMES = 30;

//用户身份相关常量
    public static final Byte INITIAL_AGE = 0;

    public static final  String INITIAL_INTRODUCE = "该用户暂时还没有设置简介哦！";

    public static final Integer INITIAL_ROLE = 1;

//    密码正则的策略(可以进行密码策略的改进)
    public static final String PASSWORD_REGEXP = "";

    //存储头像的地址
    public static String AVATAR_UPLOAD_DIR="C:/Users/folkman/Desktop/images/avatar";
    //存储新闻头像
    public static String NEWS_UPLOAD_DIR="C:/Users/folkman/Desktop/images/news";

    public final static String QUEUE_NAME="user";

    public final static String TOPIC_NAME="user";

    //默认头像文件名
    public final static String DEFAULT_AVATAR="1b35e476-d9fc-4976-b5d7-13860f068fb4.png";

    // 构造方法私有化，确保类不会被实例化

    //计数器同步锁的主键前缀
    public final static String COUNTER_LOCK_KEY="session";

    private Constants() {
    }
}
