package com.source.code.executors;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDemo {

    public static void main(String[] args) {
        ThreadPoolExecutorDemo executor = ThreadPoolExecutorDemo.newThreadPoolExecutorDemo();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("test");
            }
        });
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("test");
            }
        });


//        TulingThreadPoolExecutor tulingThreadPoolExecutor = new TulingThreadPoolExecutor();
//        tulingThreadPoolExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("hahhh");
//            }
//        });
    }
}
