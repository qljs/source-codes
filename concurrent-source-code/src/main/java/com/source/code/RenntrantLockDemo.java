package com.source.code;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RenntrantLockDemo {

    Lock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        RenntrantLockDemo demo = new RenntrantLockDemo();
        new Thread(()->{
            demo.lockMethod();
        },"线程一").start();

        new Thread(()->{
            demo.lockMethod();
        },"线程二").start();

    }

    public void lockMethod(){
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "已经获取到锁！");
        lock.unlock();
        System.out.println(Thread.currentThread().getName() + "已经释放锁！");
    }
}
