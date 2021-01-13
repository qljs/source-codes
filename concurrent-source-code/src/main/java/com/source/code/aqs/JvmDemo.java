package com.source.code.aqs;

public class JvmDemo {

    private static final Object lock1 = new Object();

    private static final Object lock2 = new Object();


    public static void main(String[] args) {
        new Thread(()->{
            synchronized (lock1){
                System.out.println(Thread.currentThread().getName() + "get lock1");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2){
                    System.out.println(Thread.currentThread().getName() + "get lock2");
                }
            }

        }).start();

        new Thread(()->{
            synchronized (lock2){
                System.out.println(Thread.currentThread().getName() + "get lock2");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1){
                    System.out.println(Thread.currentThread().getName() + "get lock1");
                }
            }

        }).start();
    }
}
