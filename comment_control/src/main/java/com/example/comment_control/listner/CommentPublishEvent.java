package com.example.comment_control.listner;


import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件
 * @author shengwu ni
 * @date 2018/07/05
 */
public class CommentPublishEvent extends ApplicationEvent {

    public CommentPublishEvent(Object source) {
        super(source);
    }


    // 省去get、set方法
}
