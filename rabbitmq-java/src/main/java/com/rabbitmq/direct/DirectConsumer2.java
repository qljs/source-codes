package com.rabbitmq.direct;

import com.rabbitmq.client.*;
import com.rabbitmq.util.RabbitMQUitl;

import java.io.IOException;

public class DirectConsumer2 {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMQUitl.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("directQueue",true, false,true, null);
        channel.queueBind("directQueue", "direct_exchanges","directKey");
        while (true) {
            channel.basicConsume("directQueue", false, new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println(new String(body));

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        channel.basicAck(envelope.getDeliveryTag(), false);
                    }

                }
            });
        }

    }
}
