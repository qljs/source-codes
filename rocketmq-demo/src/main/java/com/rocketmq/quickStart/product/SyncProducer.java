package com.rocketmq.quickStart.product;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class SyncProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("pro_grooup_demo");
        producer.setNamesrvAddr("172.16.33.52:9876;172.16.33.217:9876");
        producer.start();
        for (int i = 0; i < 10; i++) {
            String msg = "this is " + i + " message";
            Message message = new Message("TopicTest", "TagTest", msg.getBytes());
            SendResult send = producer.send(message);
            System.out.println("发送结果：" + send);
        }
        producer.shutdown();
    }
}
