package com.example.commons.service;

import com.example.commons.config.ActiveMqConfig;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * 消息消费者
 */
@Service
public class QueueConsumer {

    /**
     * 接收点对点消息
     * @param msg
     */
    @JmsListener(destination = ActiveMqConfig.QUEUE_NAME,containerFactory = "queueListener")
    public void receiveQueueMsg(String msg) {
        System.out.println("收到的消息为：" + msg);
    }
}
