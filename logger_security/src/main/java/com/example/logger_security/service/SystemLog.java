package com.example.logger_security.service;

import java.io.Serializable;
import java.util.Date;

public class SystemLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private String account; // SessionId
    private String channel; // 渠道
    private String name; // 功能名称
    private String action; // 响应类.方法
    private String url; // URI
    private String params; // 参数
    private String ip; // 请求IP
    private Date logTime; // 操作时间
    private String errMsg; // 异常信息

    // Getter 和 Setter 方法


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
