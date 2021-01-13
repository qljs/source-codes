package com.rabbitmq.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.util.RabbitMQUitl;

import java.io.IOException;
import java.util.Properties;

public class DirectProducer {

    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMQUitl.getConnection();
        Channel channel = connection.createChannel();


        for (int i = 0; i < 10; i++) {
            String message = "direct_exchanges send message" + i;
            channel.basicPublish("direct_exchanges","directKey", null, message.getBytes());
        }
        channel.close();
        connection.close();

    }
}
