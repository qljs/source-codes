package com.rocketmq.quickStart.product;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class AsyncProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("ays_producer_group");
        producer.setNamesrvAddr("172.16.33.52:9876;172.16.33.217:9876");

        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);

        for (int i = 0; i < 10; i++) {
            String msg = "hello word";
            Message message = new Message("TopicTest",msg.getBytes());
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("发送成功");
                }

                @Override
                public void onException(Throwable throwable) {
                    System.out.println("发送失败");
                }
            });
        }

        Thread.sleep(5000);
        producer.shutdown();
    }
}
