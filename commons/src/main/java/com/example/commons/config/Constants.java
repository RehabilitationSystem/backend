package com.example.commons.config;

public final class Constants {

    // redis存储的次键名称
    public static final String COUNTER_KEY = "counter";

    public static final String NUMBER_KEY = "numbers";

    public static final String STATUS_KEY = "status";

    public static final String SESSION_KEY = "sessionid";

    public static final String SUCCESS_CODE = "200";

//    登录相关的常量
    public static final String PREFIX_USER="user";

    public static final Integer WRONG_PASSWORD = 5;

    public static final Integer LOGIN_TIMES = 30;

//    密码正则的策略(可以进行密码策略的改进)
    public static final String PASSWORD_REGEXP = "";

    // 构造方法私有化，确保类不会被实例化
    private Constants() {
    }
}
