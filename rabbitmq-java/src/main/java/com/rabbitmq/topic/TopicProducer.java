package com.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.util.RabbitMQUitl;

public class TopicProducer {

    public static void main(String[] args) throws Exception{
        Connection connection = RabbitMQUitl.getConnection();
        Channel channel = connection.createChannel();
        String message = "topic.message";
        String message2 = "topic.message.context";
        for (int i = 0; i < 1; i++) {
            channel.basicPublish("topic_exchanges", "topic.message",null,message2.getBytes());
        }
        channel.close();
   //     connection.close();
    }
}
