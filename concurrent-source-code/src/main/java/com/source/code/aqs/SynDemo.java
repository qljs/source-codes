package com.source.code.aqs;

public class SynDemo {

    private final Object obj = new Object();

    public void demo(){
        StringBuffer sb = new StringBuffer();
        synchronized (obj) {
            sb.append("a");
        }
        synchronized (obj) {
            sb.append("b");
        }
        synchronized (obj) {
            sb.append("c");
        }
    }
}
