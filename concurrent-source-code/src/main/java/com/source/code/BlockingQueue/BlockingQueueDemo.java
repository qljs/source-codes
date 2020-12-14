package com.source.code.BlockingQueue;

import org.omg.CORBA.TIMEOUT;

import java.util.ArrayList;
import java.util.concurrent.*;

public class BlockingQueueDemo {
    static BlockingQueue<Message> queue = new ArrayBlockingQueue<Message>(5);
    static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws Exception {
        product();
  //      customer();
    }

    /**
     * 模拟生产者
     */
    public static void product() {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                int messId = 1;
                while (true) {
                    Message message = new Message(messId, "第" + messId + "条消息！");
                    try {
                        Thread.currentThread().setName("生产者");
                        queue.put(message);
                        System.out.println("生产者" + Thread.currentThread()  + "开始生产消息" + message.toString());
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    messId++;
                }
            }
        });
    }

    /**
     * 模拟消费者
     */
    public static void customer() {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.currentThread().setName("消费者");

                        Message take = queue.take();
                        System.out.println("消费者准备消费消息" + take.toString());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
