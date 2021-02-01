package com.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DeadLetterConsumer {

//    @RabbitListener(queues = "deadQueue")
//    @RabbitHandler
//    public void deadLetterListener(Message message, Channel channel){
//        System.out.println("死信队列：" + message);
//        try {
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
