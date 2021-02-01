package com.rabbitmq.producer;

import com.rabbitmq.callback.ConfirmCallBack;
import com.rabbitmq.callback.ReturnCallBack;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ProducerTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void initRabbitTemplate() {
        // 设置生产者消息确认
        rabbitTemplate.setConfirmCallback(new ConfirmCallBack());
        rabbitTemplate.setReturnsCallback(new ReturnCallBack());
    }

    public void sendMsg(String msg, MessageProperties properties){
        Message message = new Message(msg.getBytes(), properties);
        CorrelationData data = new CorrelationData();
        rabbitTemplate.sendAndReceive("my_topicExchange","topic",message,data);
        System.out.println("发送结束：" + data);
        throw new RuntimeException();
    }
}

