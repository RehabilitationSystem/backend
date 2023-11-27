package com.example.commons.service;


import jakarta.annotation.Resource;
import jakarta.jms.Destination;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MsgProducer {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * 发送消息
     * @param destination 消息发送的目的地
     * @param msg 具体的消息内容
     */
    public void sendMessage(Destination destination, String msg) {
        jmsMessagingTemplate.convertAndSend(destination, msg);
    }
}

