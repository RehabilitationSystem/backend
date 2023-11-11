package com.example.login_authetic.listner;


import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件
 * @author shengwu ni
 * @date 2018/07/05
 */
public class UserPublishEvent extends ApplicationEvent {

    public UserPublishEvent(Object source) {
        super(source);
    }


    // 省去get、set方法
}
