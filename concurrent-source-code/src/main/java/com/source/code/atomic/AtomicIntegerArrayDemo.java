package com.source.code.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayDemo {

    public static void main(String[] args) {
        int[] arr = {0,1,2,3};
        AtomicIntegerArray array = new AtomicIntegerArray(arr);
        array.compareAndSet(0,0,10);
        System.out.println(array.get(0));
        System.out.println(array.addAndGet(1, 11));
        System.out.println(array.getAndSet(2,12));
        System.out.println(array.get(2));
    }
}
