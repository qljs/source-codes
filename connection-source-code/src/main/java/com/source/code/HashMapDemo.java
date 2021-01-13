package com.source.code;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class HashMapDemo {

    private static HashMap<Integer, Integer> map = new HashMap<>();
    private static AtomicInteger integer = new AtomicInteger();

    public static void main(String[] args) {
        map.put(1,2);
    }

    static class HashMapTest extends Thread{

        @Override
        public void run() {
            while (integer.get() < 1000000) {
                map.put(integer.get(),integer.get());
                integer.incrementAndGet();
            }
        }
    }
}
