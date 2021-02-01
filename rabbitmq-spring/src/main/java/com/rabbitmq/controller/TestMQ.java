package com.rabbitmq.controller;

import com.rabbitmq.producer.ProducerTest;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mq")
public class TestMQ {

    @Autowired
    private ProducerTest producerTest;

    @RequestMapping("/send")
    public String send(@RequestParam("id")String id){
        String msg = "spring boot send msg";
        MessageProperties properties = new MessageProperties();
        properties.setMessageId("messageId222" + id);
        producerTest.sendMsg(msg,properties);
        return "success";
    }
}
