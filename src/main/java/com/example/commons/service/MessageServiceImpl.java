package com.example.commons.service;

import com.example.commons.config.Constants;
import jakarta.annotation.Resource;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl{

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    public <T> void sendMessage(T message) {
        System.out.println("等待发送的信息为：" + message);
        jmsMessagingTemplate.convertAndSend(Constants.TOPIC_NAME, message);
    }

    public <T> T doMessage(Class<T> destClass) {
        T message = jmsMessagingTemplate.receiveAndConvert(Constants.TOPIC_NAME, destClass);
        System.out.println("已经接收到信息：" + message);
        return message;
    }
}

