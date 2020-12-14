package com.source.code.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {

    public static void main(String[] args) {
        AtomicReference<User> reference = new AtomicReference<User>();
        User user1 = new User("张三",10);
        User user2 = new User("李四",20);
        // 设置一个新值
        reference.set(user1);
        // 若传入值等于预期值，则更新为新值
        reference.compareAndSet(user1,user2);
        // 获取当前值
        User user = reference.get();
        System.out.println(user.toString());

    }
}
