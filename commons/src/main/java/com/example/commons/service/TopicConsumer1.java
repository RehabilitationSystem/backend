package com.example.commons.service;

import com.example.commons.config.ActiveMqConfig;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * Topic消息消费者
 */
@Service
public class TopicConsumer1 {

    /**
     * 接收订阅消息
     * @param msg
     */
    @JmsListener(destination = ActiveMqConfig.TOPIC_NAME, containerFactory = "topicListener")
    public void receiveTopicMsg(String msg) {
        System.out.println("收到的消息为：" + msg);
    }

}
