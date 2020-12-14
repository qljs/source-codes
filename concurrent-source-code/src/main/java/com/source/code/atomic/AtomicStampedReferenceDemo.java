package com.source.code.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceDemo {

    public static void main(String[] args) {
        User user1 = new User("张三", 10);
        AtomicStampedReference reference = new AtomicStampedReference(user1,0);
        int oldStamp = reference.getStamp();

        Thread thread = new Thread(() -> {
            User otherUser = new User("李四", 20);
            reference.compareAndSet(user1, otherUser, 0, 1);
            System.out.println("子线程修改：" + reference.getReference());
            reference.compareAndSet(otherUser, user1, 1, 2);
            System.out.println("子线程还原：" + reference.getReference());
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        User user2 = new User("李四", 20);
        boolean result = reference.compareAndSet(user1, user2, oldStamp, oldStamp + 1);
        if(result){
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
    }
}
