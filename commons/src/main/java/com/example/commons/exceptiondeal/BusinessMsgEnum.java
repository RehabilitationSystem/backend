package com.example.commons.exceptiondeal;

/**
 * 业务异常提示信息枚举类
 * @author shengwu ni
 */
public enum BusinessMsgEnum {
    /** 参数异常 */
    PARMETER_EXCEPTION("102", "参数异常!"),
    /** 等待超时 */
    SERVICE_TIME_OUT("103", "服务调用超时！"),
    /** 参数过大 */
    PARMETER_BIG_EXCEPTION("102", "输入的图片数量不能超过50张!"),
    /** 500 : 一劳永逸的提示也可以在这定义 */
    UNEXPECTED_EXCEPTION("500", "系统发生异常，请联系管理员！"),
    DATA_INSERT_EXCEPTION("501", "数据插入错误！"),
    // 还可以定义更多的业务异常
    USER_NOT_EXISTED("400","该用户不存在！"),
    PASSWORD_WRONG_EXISTED("400","密码错误！"),
    USER_IS_EXISTED("400","用户已经存在！"),
    TOKEN_HAS_EXPIRED("401","token是伪造的或者token已过期！"),
    TOKEN_STOLEN("401","token是盗用的！"),
    TOKEN_SAME_COUNTER("401","token可能是被重放的！"),
    HAS_NOT_PERMISSIONS("403","对不起，您没有足够权限访问！"),
    TRY_MUCH_NUMBERS("400","密码输错已经达到上限，请十五分钟之后再试！"),
    USER_HAS_LOGIN("400","用户已经登录了！"),
    URI_SET_NULL("400","获取访问权限失败！"),
    TOKEN_NOT_USED("401","token令牌未被携带！");
    /**
     * 消息码
     */
    private String code;
    /**
     * 消息内容
     */
    private String msg;

    private BusinessMsgEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    // set get方法

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
