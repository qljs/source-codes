package com.source.code.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterDemo {

    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<User> updater = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");
        User user = new User("张三", 18);
        updater.compareAndSet(user,18,30);
        System.out.println(updater.get(user));
        updater.incrementAndGet(user);
        System.out.println(updater.get(user));

    }
}
