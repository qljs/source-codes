package com.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ConsumerDemo {

//    @RabbitListener(queues = {"topicQueue"})
//    @RabbitHandler
//    public void receMsg(Message message, Channel channel) throws IOException {
//        System.out.println("消费者接收消息");
//        System.out.println(message);
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
//        throw new RuntimeException();
//
//    }
}
