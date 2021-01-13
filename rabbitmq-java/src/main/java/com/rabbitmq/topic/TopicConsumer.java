package com.rabbitmq.topic;

import com.rabbitmq.client.*;
import com.rabbitmq.util.RabbitMQUitl;

import java.io.IOException;

public class TopicConsumer {

    public static void main(String[] args) throws Exception{
        Connection connection = RabbitMQUitl.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("topic1", false, false, false,null);
        channel.queueBind("topic1", "topic_exchanges","topic.*");
       while (true) {
           channel.basicConsume("topic1", true, new DefaultConsumer(channel){
               @Override
               public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                   System.out.println(new String(body));
               }
           });
       }
    }
}
