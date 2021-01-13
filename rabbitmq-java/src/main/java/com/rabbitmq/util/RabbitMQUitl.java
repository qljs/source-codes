package com.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQUitl {

    public static Connection getConnection() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("172.16.33.52");
        factory.setPort(5672);
        factory.setVirtualHost("my_vhost");
        factory.setUsername("mq");
        factory.setPassword("mq");
        factory.setConnectionTimeout(10000);
        factory.setChannelRpcTimeout(10000);

        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
