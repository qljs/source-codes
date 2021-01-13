package com.source.code.executors;

import com.sun.javaws.IconUtil;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPoolExecutorDemo {

    // 状态
    private AtomicInteger state = new AtomicInteger(0);

    // 工作线程
    private AtomicInteger workCount = new AtomicInteger(0);

    // 非核心线程存活时间
    private volatile long liveTime;

    // 同步队列
    private BlockingQueue<Runnable> queue;

    private volatile int corePoolSize;

    private volatile int maxPoolSize;

    private final int RUNNING = 0;

    private final int SHUTDOWN = 1;

    private static final ReentrantLock mainLock = new ReentrantLock();


    public static ThreadPoolExecutorDemo newThreadPoolExecutorDemo() {
        return newThreadPoolExecutorDemo(1, 5, 0l);
    }

    public static ThreadPoolExecutorDemo newThreadPoolExecutorDemo(int corePoolSize, int maxPoolSize, long liveTime) {

        return new ThreadPoolExecutorDemo(corePoolSize, maxPoolSize, liveTime, new LinkedBlockingDeque<Runnable>());
    }

    private ThreadPoolExecutorDemo(int corePoolSize, int maxPoolSize, long liveTime, BlockingQueue<Runnable> queue) {
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.liveTime = liveTime;
        this.queue = queue;
    }


    public void execute(Runnable task) {
        if (task == null)
            throw new NullPointerException();

        if (state.get() > RUNNING) {
            throw new RuntimeException("线程池已停止");
        }

        if (workCount.get() < corePoolSize) {
            if (addWorker(task, true)) {
                return;
            }
        }

        if (isRunning() && queue.offer(task)) {
            addWorker(null, false);
            return;
        } else if (addWorker(null, false)) {
            throw new RuntimeException("拒绝执行");
        }


    }


    private boolean addWorker(Runnable task, boolean core) {
        for (; ; ) {
            // 线程池停止且队列为空，拒绝执行
            if (!isRunning() && queue.isEmpty()) {
                return false;
            }

            // 检查数量
            if (workCount.get() >= (core ? corePoolSize : maxPoolSize)) {
                return false;
            }
            int w = workCount.get();
            if (workCount.compareAndSet(w, w + 1)) {
                break;
            }
        }

        Worker w = new Worker(Thread.currentThread(), task);
        ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        Thread thread = w.thread;
        mainLock.unlock();
        thread.start();

        return true;
    }

    private boolean isRunning() {
        return state.get() == 0;
    }


    private final class Worker extends ReentrantLock implements Runnable {

        final Thread thread;

        Runnable firstTask;

        private Worker(Thread thread, Runnable firstTask) {
            this.thread = new Thread(this);
            this.firstTask = firstTask;
        }


        @Override
        public void run() {
            runWorker(this);

        }

    }


    private void runWorker(Worker worker) {
        Runnable task = worker.firstTask;
        worker.firstTask = null;
        Thread thread = worker.thread;

        while (task != null || (task = getTask()) != null) {
            worker.lock();
            task.run();
            task = null;
        }
    }



    private Runnable getTask() {

        boolean timeOut = workCount.get() > corePoolSize;

        try {
            return timeOut ? queue.poll(liveTime, TimeUnit.SECONDS) : queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
