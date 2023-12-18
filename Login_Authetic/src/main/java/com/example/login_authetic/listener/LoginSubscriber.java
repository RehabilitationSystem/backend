package com.example.login_authetic.listener;

import com.example.commons.config.Constants;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class LoginSubscriber {

    @JmsListener(destination = Constants.TOPIC_NAME, containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
        // 处理接收到的消息
    }
}