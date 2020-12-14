package com.source.code.atomic;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeUtil {

    public static Unsafe getUnsafe(){
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            // theUnsafe：private static final Unsafe theUnsafe;
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
