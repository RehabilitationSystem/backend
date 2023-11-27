package com.example.commons.service;

import jakarta.annotation.Resource;
import jakarta.jms.Destination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ActiveMQ controller
 */
@RestController
@RequestMapping("/activemq")
public class ActiveMqController {

    private static final Logger logger = LoggerFactory.getLogger(ActiveMqController.class);

    @Resource
    private MsgProducer producer;
    @Resource
    private Destination queue;
    @Resource
    private TopicConsumer1 topicConsumer1;
    @Resource
    private Destination topic;

    @GetMapping("/send/queue")
    public String sendQueueMessage() {

        logger.info("===开始发送点对点消息===");
        producer.sendMessage(queue, "Queue: hello activemq!");
        return "success";
    }

    @GetMapping("/send/topic")
    public String sendTopicMessage() {
        logger.info("===开始发送一对多消息===");
        producer.sendMessage(topic, "Topic: hello activemq!");
        return "success";
    }
}
