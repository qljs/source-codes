package com.source.code;

public class VolatileDemo {

    private static boolean flag = false;

    public static void main(String[] args) {

        Thread t1 = new Thread(()->{
            VolatileDemo.flag = true;
            System.out.println("flag的值已被线程:" + Thread.currentThread() + "修改");
        }, "线程2");

        Thread t2 = new Thread(()->{
            while (!VolatileDemo.flag){

            }
            System.out.println("线程："+ Thread.currentThread() + "结束循环！");
        },"线程1");

        // 保证线程2先执行
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.start();

    }
}
